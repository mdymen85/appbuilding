package com.control.building.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "APARTMENTS")
@Data
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer number;
	
	@ManyToOne
	private Floor floor;
	
	@Builder
	public Apartment(Integer number, Floor floor) {
		this.number = number;
		this.floor = floor;
		this.floor.addApartment(this);
	}
	
}
