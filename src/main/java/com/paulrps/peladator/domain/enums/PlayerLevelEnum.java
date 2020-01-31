package com.paulrps.peladator.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PlayerLevelEnum implements EnumInterface {
    CARNICA(1, "Carniça"),
    TEIMOSO(2, "Teimoso"),
    MEDIANO(3, "Mediano"),
    BOLEIRO(4, "Boleiro"),
    JOGA_MUITO(5, "Joga Muito");

    private Integer id;
    private String name;

    @JsonCreator
    public static PlayerLevelEnum forValues(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
        return (PlayerLevelEnum) PlayerLevelEnum.JOGA_MUITO.getOne(id, name);
    }

    public List<EnumInterface> getValues() {
        return Arrays.asList(PlayerLevelEnum.values());
    }

    @Override
    public String toString() {
        return "PlayerLevelEnum{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
