package com.example.ContactsManagement.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class logoutResponse implements Serializable {
    private boolean success;
}
