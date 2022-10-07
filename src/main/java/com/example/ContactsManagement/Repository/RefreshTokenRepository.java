package com.example.ContactsManagement.Repository;

import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Entity.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByAccount(Account account);
//    @Modifying
//    @Query(value = "delete from refreshtoken r where r.account_id = :id",nativeQuery = true)
//    void deleteTokenByAccountId(@Param("id") String account_id);
}
