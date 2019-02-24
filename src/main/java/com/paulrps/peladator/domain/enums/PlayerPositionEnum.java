package com.paulrps.peladator.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PlayerPositionEnum {

	GK(1l, "Goleiro"),
	DF(2l, "Zagueiro"),
	LTE(3l, "Lateral Esquerdo"),
	LTR(4l, "Lateral Direito"),
	VOL(5l, "Volante"),
	MDC(6l, "Meio de Campo"),
	ATA(7l, "Atacante");
	
	private Long id;
	
	private String name;
	
	public static PlayerPositionEnum getOneById(Long id) {
		
		for (PlayerPositionEnum p : PlayerPositionEnum.values()){
			if (p.getId().equals(id))
				return p;
		}
		
		return null;
	}
	
	public static PlayerPositionEnum getOneByName(String name) {
		
		for (PlayerPositionEnum p : PlayerPositionEnum.values()){
			if (p.getName().equals(name))
				return p;
		}
		
		return null;
	}
}
