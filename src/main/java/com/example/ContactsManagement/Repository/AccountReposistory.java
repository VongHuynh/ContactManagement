package com.example.ContactsManagement.Repository;

import com.example.ContactsManagement.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountReposistory extends JpaRepository<Account, Integer> {
    public Account findByEmail(String email);
    Account findByUserNameEquals(String username);
}
