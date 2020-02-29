package com.paulrps.peladator.domain.dto;

import com.paulrps.peladator.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
  private String value;
  private String type;
  private Long id;
  private RoleEnum role;
}
