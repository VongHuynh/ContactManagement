package com.example.ContactsManagement.Controller;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.AccountOutput;
import com.example.ContactsManagement.DTO.Output.RestResultError;
import com.example.ContactsManagement.Entity.CustomAccount;
import com.example.ContactsManagement.Entity.RefreshToken;
import com.example.ContactsManagement.Payload.exception.TokenRefreshException;
import com.example.ContactsManagement.Payload.request.TokenRefreshRequest;
import com.example.ContactsManagement.Payload.response.JwtResponse;
import com.example.ContactsManagement.Payload.response.TokenRefreshResponse;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.Service.ExcelService;
import com.example.ContactsManagement.Service.RefreshTokenService;
import com.example.ContactsManagement.config.JwtTokenProvider;
import com.example.ContactsManagement.Payload.response.LoginResponse;
import com.example.ContactsManagement.Payload.response.logoutResponse;
import com.example.ContactsManagement.utils.ImportExcelHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("account")
public class AccountRestController {
    @Autowired
    AccountService accountService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    ExcelService fileService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AccountDTO accountDTO, BindingResult bindingResult) {
        // Xác thực từ username và password.
        RestResultError result = accountService.loginAccount(accountDTO, bindingResult);
        System.out.println(result.getMessage());
        if (result.getResult() == 0) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountDTO.getUserName(),
                        accountDTO.getPassword()
                )
        );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomAccount userDetails = (CustomAccount) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(accountDTO.getUserName());
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getAccount().getIdUser());
            //LoginResponse response = new LoginResponse(jwt, accountDTO.getUserName(), true);
            return  ResponseEntity.ok(new JwtResponse(true,jwt, refreshToken.getToken(),
                    userDetails.getUsername(), roles));
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken( @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccount)
                .map(user -> {
                    String token = jwtTokenProvider.generateToken(user.getUserName());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, refreshTokenService.updateRefreshToken(requestRefreshToken)));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }


    @GetMapping()
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/pageable") @PreAuthorize("hasAnyRole('ADMIN')")
    public AccountOutput getAllAccountsPageable(@RequestParam("page") int page,
                                                   @RequestParam("limit") int limit){
        AccountOutput output = new AccountOutput();
        output.setPage(page);
        output.setTotalPages((int) Math.ceil((double) (accountService.totalItem()/limit)));
        output.setListResults(accountService.getAllAccountsPagination(PageRequest.of(page-1, limit)));
        return output;
    }

    @PostMapping()@PermitAll
    public ResponseEntity registerAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.registerAccount(accountDTO));
    }

    @PutMapping()@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity editAccount(@RequestBody @Valid AccountDTO accountDTO, @NotNull BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok(accountService.editAccount(accountDTO));
    }

    @DeleteMapping("delete/{id}") @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
    }

    @GetMapping("logout")
    public logoutResponse logout(HttpServletRequest request, HttpServletResponse response){
       return accountService.logoutAccount(request, response);
    }
    @GetMapping("download")
    public ResponseEntity<Resource> getFile() {
        String filename = "account.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.loadListUser());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ImportExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("fail"+e.getMessage());
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
