package com.paulrps.peladator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paulrps.peladator.domain.entities.Player;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.services.PlayerService;

@SpringBootApplication
public class PeladatorApi {

	public static void main(String[] args) {
		SpringApplication.run(PeladatorApi.class, args);
	}
	
	@Bean
	CommandLineRunner runner(PlayerService playerService) {
		return args -> {
//			playerService.addPlayer ( new Player(1L,"João", 1, 2) );
//			playerService.addPlayer ( new Player(2L,"Paulo", 1, 3) );
//			playerService.addPlayer ( new Player(3L,"Pedro", 1, 2) );
//			playerService.addPlayer ( new Player(4L,"Roberto", 1, 1) );
//			playerService.addPlayer ( new Player(5L,"Marcos", 1, 1) );
//			playerService.addPlayer ( new Player(6L,"Ronaldo", 1, 5) );
			playerService.addPlayer ( new Player(7L,"Messi", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
			playerService.addPlayer ( new Player(8L,"Romário", 1, PlayerLevelEnum.JOGA_MUITO.getDescription()) );
		};
	}

}

