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
			playerService.save( new Player(1L,"Messi", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(2L,"Ronaldo", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(3L,"C. Ronaldo", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(4L,"Neymar", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(5L,"Bale", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(6L,"Zico", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(7L,"Pele", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(8L,"Garrincha", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(9L,"Pepe", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(10L,"Tostão", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(11L,"Bebeto", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.save( new Player(12L,"Romário", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
		};
	}
}

