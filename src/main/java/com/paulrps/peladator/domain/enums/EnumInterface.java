package com.paulrps.peladator.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EnumInterface extends Serializable {
    public Integer getId();

    public String getName();

    @JsonIgnore
    public List<EnumInterface> getValues();

    public default boolean isEqual(EnumInterface o) {
        return getId().equals(o.getId()) && getName().equals(o.getName());
    }

    public default EnumInterface getOne(final Integer id) {
        return getValues().stream()
            .filter(enm -> enm.getId().equals(id))
            .findFirst()
            .get();
    }

    public default EnumInterface getOne(final String description) {
        return getValues().stream()
            .filter(enm -> enm.getName().equals(description))
            .findFirst()
            .get();
    }

    public default EnumInterface getOne(final Integer id, final String description) {
        return getValues().stream()
            .filter(enm -> enm.getId().equals(id) && enm.getName().equals(description))
            .findFirst()
            .get();
    }
}
