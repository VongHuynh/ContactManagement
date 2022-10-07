package com.example.ContactsManagement.Service;

import com.example.ContactsManagement.Entity.RefreshToken;


import java.util.Optional;

public interface RefreshTokenService {
     RefreshToken createRefreshToken(Integer userId);
     RefreshToken verifyExpiration(RefreshToken token);
     Optional<RefreshToken> findByToken(String token);
     int deleteByAccount(Integer idUser);
     String updateRefreshToken(String token);
}