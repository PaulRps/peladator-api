package com.paulrps.peladator;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.PlayerService;
import com.paulrps.peladator.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PeladatorApi /*extends SpringBootServletInitializer*/ {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(PeladatorApi.class);
//	}

    public static void main(String[] args) {
        SpringApplication.run(PeladatorApi.class, args);
    }

    @Bean
    CommandLineRunner runner(PlayerService playerService, UserService userService) {
        return args -> {
            userService.save(User.builder()
                .name("Paulo")
                .password("$2a$10$pjwIpeQ4nhUUkji323NkjuQahdESUnZCUMIgQO8F8D4RDqjflH0m.")
                .build());

        };
    }
}

