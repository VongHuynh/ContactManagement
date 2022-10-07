package com.example.ContactsManagement.ServiceImpl;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Entity.Account;
import com.example.ContactsManagement.Repository.AccountReposistory;
import com.example.ContactsManagement.Service.AccountService;
import com.example.ContactsManagement.Service.ContactService;
import com.example.ContactsManagement.Service.ExcelService;

import com.example.ContactsManagement.utils.Convert;
import com.example.ContactsManagement.utils.ExportExcelHelper;
import com.example.ContactsManagement.utils.ImportExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    ContactService contactService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountReposistory accountReposistory;

    @Autowired
    Convert convert;

    @Override
    public ByteArrayInputStream loadListContact() {
        List<ContactDTO> listContacts = contactService.getAllContacts();
        ByteArrayInputStream in = ExportExcelHelper.contactsToExcel(listContacts);
        return in;
    }

    @Override
    public ByteArrayInputStream loadListUser() {
        List<AccountDTO> listAccounts = accountService.getAllAccounts();
        ByteArrayInputStream in = ExportExcelHelper.accountsToExcel(listAccounts);
        return in;
    }

    @Override
    public ByteArrayInputStream loadTwoList() {
        List<AccountDTO> listAccounts = accountService.getAllAccounts();
        List<ContactDTO> listContacts = contactService.getAllContacts();
        ByteArrayInputStream in = ExportExcelHelper.accountsAndUserToExcel(listAccounts, listContacts);
        return in;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<AccountDTO> accountDTOS = ImportExcelHelper.excelToAccountDTO(file.getInputStream());
            List<Account> accounts = new ArrayList<>();
            for (AccountDTO account : accountDTOS) {
                accounts.add(convert.toEntity(account, Account.class));
            }
            accountReposistory.saveAll(accounts);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
