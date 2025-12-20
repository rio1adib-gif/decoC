package com.digitalevidence.evidencecustody;

import com.digitalevidence.evidencecustody.entity.User;
import com.digitalevidence.evidencecustody.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DigitalevidenceCustodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalevidenceCustodyApplication.class, args);
    }

    // =================================================
    // CREATE FIRST ADMIN (RUNS ON STARTUP)
    // =================================================
    @Bean
    CommandLineRunner initAdmin(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            // Check if admin exists to avoid duplicates
            if (repo.findByUsername("admin").isEmpty()) {

                User admin = new User();
                admin.setFullName("System Administrator"); // Added Full Name to prevent null in UI
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                admin.setEnabled(true);
                admin.setBadgeNumber("ADM-001"); // Optional: Populates the badge column
                admin.setDepartment("IT Security"); // Optional: Populates the dept column

                repo.save(admin);

                System.out.println("✅ ADMIN user created: admin / admin123");
            } else {
                System.out.println("ℹ️ ADMIN user already exists");
            }
        };
    }
}