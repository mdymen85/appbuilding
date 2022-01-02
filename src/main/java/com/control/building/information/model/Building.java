package com.control.building.information.model;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.platform.commons.util.StringUtils;

import com.control.building.information.exception.ApartmentMustNotBeNullException;
import com.control.building.information.exception.BuildingBasicInformationException;
import com.control.building.information.exception.FloorMustNotBeNullException;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	
	private String name;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "building_id")
	private Set<Floor> floors;

	public Building() {}
	
	@Builder
	public Building(String name, Set<Floor> floors, Long id, String address) {		
		this.setBasicInformation(name, address);
		this.setFloors(floors);
		this.setId(id);		
	}
	
	/**
	 * 
	 * @param name
	 * @param address
	 */
	public void setBasicInformation(String name, String address) {
		if (StringUtils.isBlank(name) && StringUtils.isBlank(address)) {
			throw new BuildingBasicInformationException();
		}
		this.name = name;
		this.address = address;
		
	}

	private void setFloors(Set<Floor> floors) {
		if (floors == null) {
			this.floors = new TreeSet<Floor>();
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
	
	//when i create a floor, i must add the building 
	//in the constructor
	public void addFloor(Floor floor) {
		if (this.floors == null) {
			throw new FloorMustNotBeNullException();
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
		if (apartment == null) {
			throw new ApartmentMustNotBeNullException();
		}
		apartment.setNumber(number);
	}
	
	public void updateApartmentFloor(Apartment apartment, Floor floor) {
		apartment.setFloor(floor);	
	}
	
}
