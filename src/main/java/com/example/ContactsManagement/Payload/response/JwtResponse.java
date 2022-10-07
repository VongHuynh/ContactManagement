package com.example.ContactsManagement.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
    private List<String> roles;
    private boolean success;
    public JwtResponse( boolean success ,String accessToken, String refreshToken, String username,List<String> roles) {
        this.success = success;
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.roles = roles;
    }
}
