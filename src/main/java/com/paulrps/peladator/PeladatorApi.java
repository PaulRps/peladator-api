package com.paulrps.peladator;

import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.services.PlayerService;

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
			playerService.save( Player.builder().id(1L).name("Messi").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(2L).name("Ronaldo").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(3L).name("C. Ronaldo").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build());
			playerService.save( Player.builder().id(4L).name("Neymar").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(5L).name("Bale").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(6L).name("Zico").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(7L).name("Pele").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(8L).name("Garrincha").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(9L).name("Pepe").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(10L).name("Tostão").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(11L).name("Bebeto").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
			playerService.save( Player.builder().id(12L).name("Romário").age(1).skillLevel(PlayerLevelEnum.JOGA_MUITO).build() );
		};
	}
}

