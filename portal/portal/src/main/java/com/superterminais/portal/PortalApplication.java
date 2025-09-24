package com.superterminais.portal;

import com.superterminais.portal.model.User;
import com.superterminais.portal.model.enums.Role;
import com.superterminais.portal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

    // test users
    @Bean
    public CommandLineRunner createTestUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("user.interno").isEmpty()) {
                User internalUser = new User();
                internalUser.setUsername("user.interno");
                internalUser.setPassword(passwordEncoder.encode("senha123"));
                internalUser.setRole(Role.ROLE_INTERNAL);
                userRepository.save(internalUser);
            }

            if (userRepository.findByUsername("user.externo").isEmpty()) {
                User externalUser = new User();
                externalUser.setUsername("user.externo");
                externalUser.setPassword(passwordEncoder.encode("senha123"));
                externalUser.setRole(Role.ROLE_EXTERNAL);
                userRepository.save(externalUser);
            }
        };
    }
}