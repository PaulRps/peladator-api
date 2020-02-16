package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.enums.RoleEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {
  private List<RoleEnum> roles;
}
