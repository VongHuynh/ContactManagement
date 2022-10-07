package com.example.ContactsManagement.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String getViewPage() {
        return "home";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "ContactUs";
    }


    @GetMapping("/dashboard")
    public String getDashboard() {
        return "Dashboard";
    }

    @GetMapping("/listContact")
    public String getListContact() {
        return "ListContact";
    }

    @GetMapping("/UserManagement")
    public String getUserManagement() {
        return "UserManagement";
    }

    @GetMapping("/Register")
    public String getRegisterForm() {
        return "Register";
    }

    @GetMapping("/dataTable")
    public String getDataTable() {
        return "datatable";
    }

}
