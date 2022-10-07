package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.Output.RestResultError;
import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Payload.response.logoutResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface AccountService extends UserDetailsService {
    Account findByUserName(String username);
    Account findById(Integer id);
    List<AccountDTO> getAllAccounts();
    int totalItem();
    List<AccountDTO> getAllAccountsPagination(Pageable pageable);
    AccountDTO registerAccount(AccountDTO account);
    AccountDTO editAccount(AccountDTO account);
    void deleteAccount(Integer id);
    RestResultError loginAccount(AccountDTO account, BindingResult bindingResult);
    logoutResponse logoutAccount(HttpServletRequest request, HttpServletResponse response);

}
