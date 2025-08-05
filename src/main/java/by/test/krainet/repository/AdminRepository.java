package by.test.krainet.repository;

import by.test.krainet.dto.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import by.test.krainet.dto.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.email FROM User u JOIN u.roles r WHERE r = :role")
    List<String> findEmailsByRole(Roles role);
}
