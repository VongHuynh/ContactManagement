package com.example.ContactsManagement.DTO;

import com.example.ContactsManagement.Entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer idUser;
    @NotEmpty(message = "Full name is mandatory")
    private String userName;
    @NotEmpty(message = "Full name is mandatory")
    private String fullName;
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @Length(min = 6, max = 20, message = "Password is mandatory")
    private String password;
    List<Authority> authorities;

//    public AccountDTO(String userName, String fullName, String email, String password) {
//        this.userName = userName;
//        this.fullName = fullName;
//        this.email = email;
//        this.password = password;
//    }
}
