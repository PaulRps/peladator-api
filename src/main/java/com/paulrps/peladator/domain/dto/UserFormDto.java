package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {
    private List<RoleEnum> roles;
}
