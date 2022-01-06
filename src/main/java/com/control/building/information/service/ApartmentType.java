package com.control.building.information.service;

import com.control.building.information.dto.ApartmentDTO;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.ApartmentBidirectional;
import com.control.building.information.model.Floor;

public enum ApartmentType {

	UNIDIRECTIONAL {
		@Override
		public Apartment toApartment(ApartmentDTO apartmentDTO, Floor floor) {
			return ApartmentBidirectional.builder()
					.floor(floor)
					.number(apartmentDTO.getNumber())
					.build();
		}
	}, BIDIRECTIONAL {
		@Override
		public Apartment toApartment(ApartmentDTO apartmentDTO, Floor floor) {
			return ApartmentBidirectional.apartmentBidirectional()
					.floor(floor)
					.number(apartmentDTO.getNumber())
					.build();
		}
	};		
	
	public abstract Apartment toApartment(ApartmentDTO apartmentDTO, Floor floor);
	
}
