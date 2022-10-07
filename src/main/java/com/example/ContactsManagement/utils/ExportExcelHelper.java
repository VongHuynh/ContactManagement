package com.example.ContactsManagement.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.ContactsManagement.DTO.AccountDTO;
import com.example.ContactsManagement.DTO.ContactDTO;
import com.example.ContactsManagement.Entity.Authority;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExportExcelHelper {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    //export file contact
    public static ByteArrayInputStream contactsToExcel(List<ContactDTO> contactList) {
        String[] HEADERs = { "Id", "FullName", "Email", "Phone", "Message" };
        String SHEET = "contact";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Contact");

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ContactDTO contact : contactList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(contact.getId());
                row.createCell(1).setCellValue(contact.getFullName());
                row.createCell(2).setCellValue(contact.getEmail());
                row.createCell(3).setCellValue(contact.getPhone());
                row.createCell(4).setCellValue(contact.getMessage());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    // export fill account
    public static ByteArrayInputStream accountsToExcel(List<AccountDTO> accountDTOS) {
        String[] HEADERs = { "Id", "FullName", "Email", "UserName", "Role" };
        String SHEET = "account";


        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
                createStyleForHeader(sheet,cell);
            }
            int rowIndex=1;
            int count = 1;
            for (AccountDTO account : accountDTOS) {
                // Create row
                if (count % 2 != 0) {
                    Row row = sheet.createRow(rowIndex++);
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    // Write data on row
                    Cell cell = row.createCell(0);
                    cell.setCellValue(account.getIdUser());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(1);
                    cell.setCellValue(account.getFullName());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(2);
                    cell.setCellValue(account.getEmail());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(3);
                    cell.setCellValue(account.getUserName());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle);
                    for (Authority ac : account.getAuthorities()) {
                        row = sheet.createRow(rowIndex++);
                        cell = row.createCell(0);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(1);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(2);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(3);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(4);
                        cell.setCellValue(ac.getRole().getName());
                        cell.setCellStyle(cellStyle);
                    }
                }else {
                    Row row = sheet.createRow(rowIndex++);
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    // Write data on row
                    Cell cell = row.createCell(0);
                    cell.setCellValue(account.getIdUser());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(1);
                    cell.setCellValue(account.getFullName());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(2);
                    cell.setCellValue(account.getEmail());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(3);
                    cell.setCellValue(account.getUserName());
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(4);
                    cell.setCellStyle(cellStyle);
                    for (Authority ac : account.getAuthorities()) {
                        row = sheet.createRow(rowIndex++);
                        cell = row.createCell(0);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(1);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(2);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(3);
                        cell.setCellStyle(cellStyle);
                        cell = row.createCell(4);
                        cell.setCellValue(ac.getRole().getName());
                        cell.setCellStyle(cellStyle);
                    }
                }
                autosizeColumn(sheet, sheet.getRow(0).getPhysicalNumberOfCells());
                count++;
            }

//            int rowIdx = 1;
//            for (AccountDTO accountDTO : accountDTOS) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(accountDTO.getIdUser());
//                row.createCell(1).setCellValue(accountDTO.getFullName());
//                row.createCell(2).setCellValue(accountDTO.getEmail());
//                row.createCell(3).setCellValue(accountDTO.getUserName());
//                for (Authority ac : accountDTO.getAuthorities()){
//                    row = sheet.createRow(rowIdx++);
//                    row.createCell(4).setCellValue(ac.getRole().getName());
//                }
//                autosizeColumn(sheet,sheet.getRow(0).getPhysicalNumberOfCells());
//                // style
//                CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
//                cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//                row.setRowStyle(cellStyle);
//
//            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create CellStyle for header
    private static void createStyleForHeader(Sheet sheet, Cell cell) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
//        font.setColor(IndexedColors.WHITE.getIndex()); // text color
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);
    }

    //export list user and list contact
    public static ByteArrayInputStream accountsAndUserToExcel(List<AccountDTO> accountDTOS, List<ContactDTO> contactList) {
        String[] HEADERs1 = { "Id", "FullName", "Email", "UserName" , "Role"};
        String SHEET1 = "account";
        String[] HEADERs2 = { "Id", "FullName", "Email", "Phone", "Message" };
        String SHEET2 = "contact";
        Workbook workbook = new XSSFWorkbook();
        try (workbook; ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet1 = workbook.createSheet(SHEET1);
            Row headerRow = sheet1.createRow(0);
            for (int col = 0; col < HEADERs1.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs1[col]);
            }

            CellStyle styleRow = workbook.createCellStyle();
            int rowIdx = 1;
            for (int i =0; i < accountDTOS.size(); i++) {
                Row row = sheet1.createRow(rowIdx++);
                row.createCell(0).setCellValue(accountDTOS.get(i).getIdUser());
                row.createCell(1).setCellValue(accountDTOS.get(i).getFullName());
                row.createCell(2).setCellValue(accountDTOS.get(i).getEmail());
                row.createCell(3).setCellValue(accountDTOS.get(i).getUserName());
                sheet1.autoSizeColumn(i);
                row.setRowStyle(styleRow);

            }

            Sheet sheet2 = workbook.createSheet(SHEET2);
            // Header
            Row headerRow2 = sheet2.createRow(0);

            for (int col = 0; col < HEADERs2.length; col++) {
                Cell cell = headerRow2.createCell(col);
                cell.setCellValue(HEADERs2[col]);
            }

            int rowIdx2 = 1;
            for (ContactDTO contact : contactList) {
                Row row = sheet2.createRow(rowIdx2++);
                row.createCell(0).setCellValue(contact.getId());
                row.createCell(1).setCellValue(contact.getFullName());
                row.createCell(2).setCellValue(contact.getEmail());
                row.createCell(3).setCellValue(contact.getPhone());
                row.createCell(4).setCellValue(contact.getMessage());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }

    //----------------------------------------------------------------

}
