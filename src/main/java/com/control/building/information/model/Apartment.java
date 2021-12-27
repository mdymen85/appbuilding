package com.control.building.information.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	private final Long id;
	
	private final Integer number;
	
	private final String address;
	
	@ManyToOne
	private final Floor floor;
	
	public Apartment() {
		this.id = null;
		this.number = null;
		this.address = null;
		this.floor = null;
	}
	
	@Builder
	public Apartment(Long id, String address, Integer number, Floor floor) {
		this.id = id;
		this.address = address;
		this.number = number;
		this.floor = floor;
		this.floor.addApartment(this);
	}
	
}
