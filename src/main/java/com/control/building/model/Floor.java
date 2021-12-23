package com.control.building.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "FLOOR")
@Data
@Builder
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private Integer number;
	
//	@ManyToOne
//	private Building Building;
//	
	public void setNumber(Integer number) {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		
		this.number = number;
	}
}
