package ru.ugrinovich.FirstSecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ugrinovich.FirstSecurityApp.services.AdminService;


@Controller
public class HelloController {

    private final AdminService adminService;

    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStaff();
        return "admin";
    }
}
