package com.paulrps.peladator.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="tb_player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_player")
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Integer age;
	
	@NotNull
	private Integer skillLevel;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name="fk_position")
//	private List<PlayerPosition> playerPositions;
	
	public Player() {}
	
}
