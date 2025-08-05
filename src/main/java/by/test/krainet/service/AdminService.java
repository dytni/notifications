package by.test.krainet.service;

import by.test.krainet.dto.Roles;
import by.test.krainet.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<String> getAdminEmails() {
        return adminRepository.findEmailsByRole(Roles.ADMIN);
    }
}
