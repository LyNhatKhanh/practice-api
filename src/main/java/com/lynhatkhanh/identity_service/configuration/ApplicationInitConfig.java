package com.lynhatkhanh.identity_service.configuration;

import com.lynhatkhanh.identity_service.entity.User;
import com.lynhatkhanh.identity_service.enums.RoleEnum;
import com.lynhatkhanh.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // inject logger at console by Lombok
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
          if (userRepository.findByUsername("admin").isEmpty()) {
              HashSet<String> roles = new HashSet<>();
              roles.add(RoleEnum.ADMIN.name());

              User user = User.builder()
                      .username("admin")
                      .password(passwordEncoder.encode("admin"))
                      .roles(roles)
                      .build();

              userRepository.save(user);
              log.warn("Admin user has been created with default password: admin. Please change it!");
          }
        };
    }

}
