package com.paulrps.peladator.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PlayerLevelEnum {
	CARNICA(1, "Carniça"),
	TEIMOSO(2, "Teimoso"),
	MEDIANO(3, "Mediano"),
	BOLEIRO(4, "Boleiro"),
	JOGA_MUITO(5, "Joga Muito");
	
	private Integer id;
	private String description;	
	
}
