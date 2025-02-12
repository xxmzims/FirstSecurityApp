package ru.ugrinovich.FirstSecurityApp.controllers;

import org. springframework. security. core. Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ugrinovich.FirstSecurityApp.security.PersonDetails;
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
    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }
    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStaff();
        return "admin";
    }
}
