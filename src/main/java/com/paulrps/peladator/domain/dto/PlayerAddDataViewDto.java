package com.paulrps.peladator.domain.dto;

import java.util.List;

import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;

import lombok.Data;

@Data
public class PlayerAddDataViewDto {
	
	private List<PlayerPositionEnum> positions;
	
	private List<PlayerLevelEnum> skillLevels;

}
