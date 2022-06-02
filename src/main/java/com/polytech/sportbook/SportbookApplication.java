package com.polytech.sportbook;

import com.polytech.sportbook.domain.Role;
import com.polytech.sportbook.domain.User;
import com.polytech.sportbook.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SportbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportbookApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role("ADMIN"));
            userService.saveRole(new Role("USER"));

            userService.saveUser(new User("Kostia", "Shuryhin", "Andreevich", "+380951427261",
                    "ksurygin5@gmail.com", "Shkaff02","1234", new ArrayList<>()));
            userService.saveUser(new User("Andrew", "Brovko", "Anatolych", "+380994445522", "anbro95@gmail.com",
                    "Biba", "1235", new ArrayList<>()));

            userService.addRoleToUser("Shkaff02", "ADMIN");
            userService.addRoleToUser("Shkaff02", "USER");

            userService.addRoleToUser("Biba", "USER");
        };

    }

}
