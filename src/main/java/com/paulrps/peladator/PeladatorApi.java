package com.paulrps.peladator;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import com.paulrps.peladator.services.PlayerService;
import com.paulrps.peladator.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

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
            if ("default".equals(System.getenv("SPRING_PROFILES_ACTIVE"))) {
                userService.save(User.builder()
                    .name("Paulo")
                    .password("$2a$10$pjwIpeQ4nhUUkji323NkjuQahdESUnZCUMIgQO8F8D4RDqjflH0m.")
                    .build());
                playerService.save(Player.builder().id(1L).name("Messi").age(34).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(2L).name("Ronaldo").age(41).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(3L).name("C. Ronaldo").age(31).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(4L).name("Neymar").age(28).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(5L).name("Bale").age(31).shirtNumber(10).skillLevel(PlayerLevelEnum.BOLEIRO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(6L).name("Zico").age(71).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(7L).name("Pepe").age(31).shirtNumber(10).skillLevel(PlayerLevelEnum.TEIMOSO).position(PlayerPositionEnum.DF).build());
                playerService.save(Player.builder().id(8L).name("Nesta").age(51).shirtNumber(10).skillLevel(PlayerLevelEnum.MEDIANO).position(PlayerPositionEnum.DF).build());
                playerService.save(Player.builder().id(9L).name("Bebeto").age(41).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(10L).name("Romário").age(51).shirtNumber(10).skillLevel(PlayerLevelEnum.JOGA_MUITO).position(PlayerPositionEnum.ATA).build());
                playerService.save(Player.builder().id(11L).name("Dida").age(41).shirtNumber(12).skillLevel(PlayerLevelEnum.BOLEIRO).position(PlayerPositionEnum.GK).build());
                playerService.save(Player.builder().id(12L).name("Tafarel").age(41).shirtNumber(1).skillLevel(PlayerLevelEnum.BOLEIRO).position(PlayerPositionEnum.GK).build());
                playerService.save(Player.builder().id(13L).name("Julio").age(41).shirtNumber(1).skillLevel(PlayerLevelEnum.BOLEIRO).position(PlayerPositionEnum.GK).build());
            } else {
                Optional<User> user = userService.findByName("Paulo");
                if (!user.isPresent()) {
                    userService.save(User.builder()
                        .name("Paulo")
                        .password("$2a$10$pjwIpeQ4nhUUkji323NkjuQahdESUnZCUMIgQO8F8D4RDqjflH0m.")
                        .build());
                }
            }
        };
    }
}

