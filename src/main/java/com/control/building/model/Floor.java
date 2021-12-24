package com.control.building.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "FLOORS")
@Data
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Integer number;
	
	@ManyToOne
	private Building building;
	
	@Setter(value = AccessLevel.PRIVATE)
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "apartment_id" )
	private Set<Apartment> apartments;
	
	public Floor() {}
	
	public Floor (Integer number, Building building) {
		this.commonConstructor(number, building);	
	}
	
	public Floor (Integer number, Building building, Set<Apartment> apartments) {
		this.commonConstructor(number, building);	
		this.apartments = apartments;
	}
	
	private void commonConstructor(Integer number, Building building) {
		this.numberValidation(number);
		this.buildingValidation(building);
		
		this.number = number;
		this.building = building;
		this.building.addFloor(this);
	}
	
	private void numberValidation(Integer number) {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	private void buildingValidation(Building building) {
		if (building == null) {
			throw new IllegalArgumentException();
		}
	}
	
	public void setNumber(Integer number) {
		this.numberValidation(number);
		this.number = number;
	}
	
	public void setBuilding(Building building) {
		this.buildingValidation(building);
		this.building = building;
		this.building.addFloor(this);
	}
	
	public void addApartment(Apartment apartment) {
		if (this.apartments == null) {
			this.apartments = new HashSet<Apartment>();
		}
		
		if (!this.apartments.contains(apartment)) {
			this.apartments.add(apartment);
			apartment.setFloor(this);
		}
	}
	
}
