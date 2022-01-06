package com.control.building.information.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApartmentBidirectional extends Apartment {

	@Builder(builderMethodName = "apartmentBidirectional")
	public ApartmentBidirectional(Long id, Integer number, Floor floor) {
		super(id, number, floor);
		this.getFloor().addApartment(this);
	}
	
}
