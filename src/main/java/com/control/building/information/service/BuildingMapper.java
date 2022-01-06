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
import com.control.building.information.model.FloorBidirectional;

@Component
public class BuildingMapper {

	public Building toBuilding(BuildingDTO buildingDTO) {
		
		var building = Building.builder()
				.address(buildingDTO.getAddress())
				.name(buildingDTO.getName())
				.build();
		
		buildingDTO.getFloors()
				.stream()
				.map(fDTO -> FloorType.BIDIRECTIONAL.toFloor(fDTO, building))
				.collect(Collectors.toSet());
		
		return building;
		
	}
	
	public Floor toBidirectionalFloor(FloorDTO floorDTO, Building building) {
		return FloorType.UNIDIRECTIONAL.toFloor(floorDTO, building);
	}

}
