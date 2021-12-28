package com.control.building.information.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="BUILDINGS")
@Getter
@AllArgsConstructor
@Slf4j
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "building_id")
	private List<Floor> floors;

	public Building() {}
	
	@Builder
	public Building(String name, List<Floor> floors, Long id, String address) {			
		this.setFloors(floors);
		this.setName(name);
		this.setId(id);		
		this.address = address;
	}
	
	private void setFloors(List<Floor> floors) {
		if (floors == null) {
			this.floors = new ArrayList<Floor>();
		} else {
			this.floors = floors;	
		}
	}
	
	private void setId(Long id) {
		if (id == null) {		
			log.warn("The object Building has a null id, so it will be created...");
			return;
		} 
		
		if (id < 0) {
			throw new IllegalArgumentException();						
		}
		this.id = id;
		
	}
	
	private void setName(String name) {
		
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}		
		
		this.name = name;
		
	}
	
	//when i create a floor, i must add the building 
	//in the constructor
	public void addFloor(Floor floor) {
		if (this.floors == null) {
			throw new IllegalArgumentException();
		}
		
		boolean anyMatch = this.floors
		.stream()
		.anyMatch(f -> f.getNumber() == floor.getNumber());
		
		if (!anyMatch) {
			this.floors.add(floor);
		}

	}
	
	public Optional<Floor> findFloor(Integer number) {
		return this.floors.stream()
				.filter(f -> f.getNumber() == number)
				.findFirst();
	}
	
	public Optional<Apartment> findApartment(Integer number) {
		
		for (Floor floor : floors) {
			return floor.getApartments()
					.stream()
					.filter(a -> a.getNumber() == number)
					.findAny();
		}
		return Optional.empty();
	}
	
	public void updateApartmentNumber(Apartment apartment, Integer number) {
		apartment.setNumber(number);
	}
	
	public void updateApartmentFloor(Apartment apartment, Floor floor) {
		//TODO: validations		
	}
	
}
