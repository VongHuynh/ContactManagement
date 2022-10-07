package com.example.ContactsManagement.DTO.Output;

import com.example.ContactsManagement.DTO.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOutput {
    private int page;
    private int totalPages;
    private List<AccountDTO> listResults = new ArrayList<>();
}
