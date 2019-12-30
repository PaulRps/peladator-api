package com.paulrps.peladator.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum PlayerPositionEnum implements EnumInterface {
	GK(1, "Goleiro", "GL"),
	DF(2, "Zagueiro", "ZAG"),
	LTE(3, "Lateral Esquerdo", "LTE"),
	LTR(4, "Lateral Direito", "LTD"),
	VOL(5, "Volante", "VOL"),
	MDC(6, "Meio de Campo", "MDC"),
	ATA(7, "Atacante", "ATA");
	
	private Integer id;
	private String name;
	private String shortName;
	
	@Override
	public List<EnumInterface> getValues() {
		return Arrays.asList(PlayerPositionEnum.values());
	}

	@JsonCreator
	public static PlayerPositionEnum forValues(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
		return (PlayerPositionEnum) PlayerPositionEnum.ATA.getOne(id, name);
	}

	@Override
	public String toString() {
		return "PlayerPositionEnum{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
