package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.Entity.RefreshToken;
import com.example.ContactsManagement.Payload.exception.TokenRefreshException;
import com.example.ContactsManagement.Repository.AccountReposistory;
import com.example.ContactsManagement.Repository.RefreshTokenRepository;
import com.example.ContactsManagement.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    AccountReposistory accountReposistory;

    @Value("${aegona.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Integer idUser) {
        deleteByAccount(idUser);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(accountReposistory.findById(idUser).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signIn request");
        }
        return token;
    }

    @Override
    public int deleteByAccount(Integer idUser) {
        return refreshTokenRepository.deleteByAccount(accountReposistory.findById(idUser).get());
    }

    @Override
    public String updateRefreshToken(String token) {
        RefreshToken refreshToken = (RefreshToken) refreshTokenRepository.findByToken(token).orElse(null);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

}
