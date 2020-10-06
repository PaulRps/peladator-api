package com.paulrps.peladator.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import com.paulrps.peladator.domain.enums.PlayerPositionEnum;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_player")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pk_player")
  private Long id;

  @NotNull private String name;

  @NotNull private Integer age;

  @NotNull private Integer shirtNumber;

  @NotNull
  @Enumerated(EnumType.STRING)
  private PlayerLevelEnum skillLevel;

  @NotNull
  @Enumerated(EnumType.STRING)
  private PlayerPositionEnum position;

  @Transient
  @Temporal(TemporalType.DATE)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date paymentDate;
}
