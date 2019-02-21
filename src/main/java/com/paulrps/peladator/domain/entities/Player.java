package com.paulrps.peladator.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
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
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_position")
	private List<PlayerPosition> playerPositions;
	
}
