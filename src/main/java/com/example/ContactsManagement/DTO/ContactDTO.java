package com.example.ContactsManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Integer id;
    private LocalDateTime date;

    @NotEmpty(message = "Full name is mandatory")
    private String fullName;

    @NotEmpty(message = "Email is mandatory")
    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number incorrectly formatted")
    private String phone;

    @NotEmpty(message = "Message is mandatory")
    private String message;
}
