package com.control.building.information.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.control.building.information.exception.FloorMustNotBeNullException;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "APARTMENTS")
@Getter
public class Apartment implements Comparable<Apartment> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer number;
	
	@ManyToOne
	private Floor floor;
	
	public Apartment() {}
	
	@Builder
	public Apartment(Long id, Integer number, Floor floor) {
		this.id = id;
		this.number = number;
		this.floor = floor;
		this.floor.addApartment(this);
	}
	
	public Building getBuilding() {
		return this.floor.getBuilding();
	}
	
	protected void setNumber(Integer number) {
		this.number = number;
	}
	
	public Integer getFloorNumber() {
		return this.floor.getNumber();
	}
	
	protected void setFloor(Floor floor) {
		if (floor == null) {
			throw new FloorMustNotBeNullException();
		}
		this.floor = floor;
	}

	@Override
	public int compareTo(Apartment apartment) {
		if (apartment == null) {
			throw new IllegalArgumentException();
		}
		return this.number.intValue() - apartment.getNumber().intValue();
	}
	
}
