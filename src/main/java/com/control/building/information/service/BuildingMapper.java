package com.control.building.information.service;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.control.building.information.dto.ApartmentDTO;
import com.control.building.information.dto.BuildingDTO;
import com.control.building.information.dto.FloorDTO;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;

@Component
public class BuildingMapper {

	public Building toBuilding(BuildingDTO buildingDTO) {
		
		var building = Building.builder()
				.address(buildingDTO.getAddress())
				.name(buildingDTO.getName())
				.build();
		
		Set<Floor> floors = buildingDTO.getFloors()
			.stream()
			.map(fDTO -> this.toFloor(fDTO, building))
			.collect(Collectors.toSet());
		
		return building;
		
	}
	
	public Floor toFloor(FloorDTO floorDTO, Building building) {
		
		var floor = Floor.builder()
				.building(building)
				.number(floorDTO.getNumber())
				.build();
		
		if (floorDTO.getApartments() == null) {
			return floor;
		}
		
		floorDTO.getApartments()
			.stream()
			.map(aDTO -> this.toApartment(aDTO, floor))
			.forEach(a -> floor.addApartment(a));
				
		return floor;
		
	}
	
	private Apartment toApartment(ApartmentDTO apartment, Floor floor) {
		return Apartment.builder()
				.floor(floor)
				.number(apartment.getNumber())
				.build();
	}
	
}
