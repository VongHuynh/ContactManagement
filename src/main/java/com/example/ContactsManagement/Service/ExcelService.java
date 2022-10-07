package com.example.ContactsManagement.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface ExcelService {
    ByteArrayInputStream loadListContact();
    ByteArrayInputStream loadListUser();
    ByteArrayInputStream loadTwoList();
    void save(MultipartFile file);
}
