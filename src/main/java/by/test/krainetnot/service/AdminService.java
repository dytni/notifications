package by.test.krainetnot.service;

import by.test.krainetnot.model.Roles;
import by.test.krainetnot.repository.AdminRepository;
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
