package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.RestResultError;
import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Entity.CustomAccount;

import com.example.ContactsManagement.Repository.AccountReposistory;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.Payload.response.logoutResponse;
import com.example.ContactsManagement.utils.Convert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
@Primary
@Service
@CacheConfig(cacheNames = "customerCache")
public class AccountServiceImpl implements AccountService  {
    @Autowired
    AccountReposistory accountReposistory;
    @Autowired
    Convert convert;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsService userDetailsService;

    private static String username;
    @Override
    public Account findByUserName(String username) {
        return accountReposistory.findByUserNameEquals(username);
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> account = accountReposistory.findById(id);
        return modelMapper.map(account.get(), Account.class);
    }

    @Override
    @Cacheable(cacheNames = "customers")
    public List<AccountDTO> getAllAccounts() {
        List<Account> listAccounts = accountReposistory.findAll();
        List<AccountDTO> listAccountsDTO = listAccounts.stream().map(account -> convert.toDto(account, AccountDTO.class))
                .collect(Collectors.toList());
        return listAccountsDTO;
    }

    @Override
    public int totalItem() {
        return (int) accountReposistory.count();
    }

    @Override
    public List<AccountDTO> getAllAccountsPagination(Pageable pageable) {
        List<AccountDTO> listAccountsDTO = new ArrayList<>();
        List<Account> listAccounts = accountReposistory.findAll(pageable).getContent();
        for (Account account : listAccounts) {
            AccountDTO accountDTO = convert.toDto(account, AccountDTO.class);
            listAccountsDTO.add(accountDTO);
        }
        return listAccountsDTO;
    }

    @Override
    @CacheEvict(cacheNames = "customers", allEntries = true)
    public AccountDTO registerAccount(AccountDTO accountDTO) {
        Account newAccount = convert.toEntity(accountDTO, Account.class);
        newAccount.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        AccountDTO newAccountDTO = convert.toDto(accountReposistory.save(newAccount), AccountDTO.class);
        return newAccountDTO;
    }

    @Override
    @CacheEvict(cacheNames = "customers", allEntries = true)
    public AccountDTO editAccount(AccountDTO accountDTO) {
        return  convert.toDto(accountReposistory.save(convert.toEntity(accountDTO,Account.class)), AccountDTO.class);
    }

    @Override
    @Caching(evict = { @CacheEvict(cacheNames = "customer", key = "#id"),
            @CacheEvict(cacheNames = "customers", allEntries = true) })
    public void deleteAccount(Integer id) {
        accountReposistory.deleteById(id);
    }

    @Override
    public RestResultError loginAccount(AccountDTO account, BindingResult bindingResult) {
        RestResultError resultError = new RestResultError();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            resultError.setResult(90);
            resultError.setMessage("Wrong input value");
            resultError.setError(errors);
            return resultError;
        }
//        Get account in database
        Account accountDB = accountReposistory.findByUserNameEquals(account.getUserName());
//        account.setPassword(passwordEncoder.encode(accountDB.getPassword()));
//        accountDB = AcccountReposistory.save(accountDB);
//        Check user db with user client
        if (accountDB==null) {
            resultError.setResult(403);
            resultError.setMessage("Wrong Username!!");
        }else if (!passwordEncoder.matches( account.getPassword(), accountDB.getPassword())) {
            resultError.setResult(403);
            resultError.setMessage("Wrong password !!");
        }
        else {
            resultError.setResult(0);
            resultError.setMessage(accountDB.getUserName());
            this.username = accountDB.getUserName();
        }
        return resultError;
    }

    @Override
    public logoutResponse logoutAccount(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new logoutResponse(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        Account account = accountReposistory.findByUserNameEquals(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomAccount(account);
    }

}

