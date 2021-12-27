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
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="BUILDINGS")
@Data
@Builder
@AllArgsConstructor
@Slf4j
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@Column(nullable = false)
	private final String name;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "building_id")
	private final List<Floor> floors;

	public Building() {
		this.id = null;
		this.name = null;
		this.floors = null;
	}
	
	@Builder
	public Building(String name, List<Floor> floors, Long id) {	
		
		if (floors == null) {
			this.floors = new ArrayList<Floor>();
		} else {
			this.floors = floors;	
		}

		this.validateName(name);
		this.name = name;

		this.id = id;
		this.validateId();
		
	}
	
	private void validateId() {
		if (id == null) {		
			log.warn("The object Building has a null id, so it will be created...");
			return;
		} 
		
		if (id < 0) {
			throw new IllegalArgumentException();						
		}
		
	}
	
	private void validateName(String name) {
		
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}		
		
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
	
	public Optional<Floor> findFloor(Floor floor) {
		return this.floors.stream()
				.filter(f -> f.getNumber() == floor.getNumber())
				.findFirst();
	}
	
}
