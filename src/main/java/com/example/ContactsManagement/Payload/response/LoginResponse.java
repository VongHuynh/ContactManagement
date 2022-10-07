package com.example.ContactsManagement.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse implements Serializable {
    private String token;
    private final String type="Bearer";
//    private String refreshToken;
    private String username;
    private boolean success;
}
