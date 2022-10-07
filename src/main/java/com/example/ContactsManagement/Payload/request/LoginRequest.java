package com.example.ContactsManagement.Payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
