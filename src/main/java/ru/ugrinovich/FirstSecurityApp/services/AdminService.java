package ru.ugrinovich.FirstSecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN`')")
    public void doAdminStaff(){
        System.out.println("Only admin here");
    }
}
