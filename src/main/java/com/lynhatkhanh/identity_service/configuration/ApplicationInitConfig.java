package com.lynhatkhanh.identity_service.configuration;

import com.lynhatkhanh.identity_service.entity.Role;
import com.lynhatkhanh.identity_service.entity.User;
import com.lynhatkhanh.identity_service.enums.RoleEnum;
import com.lynhatkhanh.identity_service.exception.AppException;
import com.lynhatkhanh.identity_service.exception.ErrorCode;
import com.lynhatkhanh.identity_service.repository.RoleRepository;
import com.lynhatkhanh.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // inject logger at console by Lombok
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
          if (userRepository.findByUsername("admin").isEmpty()) {
              HashSet<Role> roles = new HashSet<>();
              Role roleAdmin;
              if (roleRepository.findById("ADMIN").isEmpty()) {
                  roleAdmin = Role.builder()
                          .name("ADMIN")
                          .description("Admin role.")
                          .build();
              } else
                  roleAdmin = roleRepository.findById("ADMIN")
                          .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

              roles.add(roleAdmin);

              User user = User.builder()
                      .username("admin")
                      .firstName("Khanh")
                      .lastName("Ly")
                      .password(passwordEncoder.encode("admin"))
                      .roles(roles)
                      .build();

              userRepository.save(user);
              log.warn("Admin user has been created with default password: admin. Please change it!");
          }
        };
    }

}
