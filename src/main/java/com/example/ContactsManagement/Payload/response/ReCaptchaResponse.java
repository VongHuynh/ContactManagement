package com.example.ContactsManagement.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReCaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}
