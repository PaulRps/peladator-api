package com.paulrps.peladator.domain.entities;

import com.paulrps.peladator.domain.enums.PlayerLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_player")
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Integer age;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PlayerLevelEnum skillLevel;
}
