package com.control.building.information.model;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.control.building.information.exception.ApartmentMustNotBeNullException;
import com.control.building.information.exception.BuildingBasicInformationException;
import com.control.building.information.exception.FloorAlreadyExistsException;
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
	
	@Column(unique = true)
	private String uuid; //UUID has a bad performance, but i put it in this case just for simplify
	
	private String name;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "building_id")
	protected Set<Floor> floors;

	public Building() {}
	
	@Builder
	public Building(String name, Set<Floor> floors, Long id, String address, String uuid, Boolean bidirectionalFloor) {		
		this.setBasicInformation(name, address);
		this.setFloors(floors);
		this.setId(id);		
		this.uuid = uuid == null ? UUID.randomUUID().toString() : uuid;
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
		
		boolean saved = this.floors.add(floor);
		
		if (!saved) {
			throw new FloorAlreadyExistsException(this.uuid, floor.getNumber());
		}

	}
	
	public Optional<Floor> findFloor(Integer number) {
		Iterator<Floor> it = this.floors.iterator();
		while (it.hasNext()) {
			Floor floor = it.next();
			if (floor.getNumber() == number) {
				return Optional.of(floor);
			}
		}		
		return Optional.empty();
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
