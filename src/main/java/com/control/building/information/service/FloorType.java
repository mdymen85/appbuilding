package com.control.building.information.service;

import com.control.building.information.dto.ApartmentDTO;
import com.control.building.information.dto.FloorDTO;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;
import com.control.building.information.model.FloorBidirectional;

public enum FloorType {
	UNIDIRECTIONAL {
		@Override
		public Floor toFloor(FloorDTO floorDTO, Building building) {
			var floor = FloorBidirectional.builder()
					.building(building)
					.number(floorDTO.getNumber())
					.build();
			
			return toApartments(floorDTO, floor);
		}
	}, BIDIRECTIONAL {
		@Override
		public Floor toFloor(FloorDTO floorDTO, Building building) {
			var floor = FloorBidirectional.floorBidirectional()
				.building(building)
				.number(floorDTO.getNumber())
				.build();
			
			return toApartments(floorDTO, floor);
		}
	};
	
	public abstract Floor toFloor(FloorDTO floorDTO, Building building);
	
	private static Floor toApartments(FloorDTO floorDTO, Floor floor) {
		if (floorDTO.getApartments() == null) {
			return floor;
		}
		
		floorDTO.getApartments()
			.stream()
			.map(aDTO -> FloorType.toApartment(aDTO, floor))
			.forEach(a -> floor.addApartment(a));
				
		return floor;
	}
	
	private static Apartment toApartment(ApartmentDTO apartment, Floor floor) {
		return Apartment.builder()
				.floor(floor)
				.number(apartment.getNumber())
				.build();
	}
	
}
