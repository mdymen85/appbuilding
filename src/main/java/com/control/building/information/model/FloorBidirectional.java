package com.control.building.information.model;

import java.util.HashSet;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FloorBidirectional extends Floor {

	@Builder(builderMethodName = "floorBidirectional")
	public FloorBidirectional(Integer number, Building building, HashSet<Apartment> apartments, Long id) {
		super(number, building, apartments, id);
		super.getBuilding().addFloor(this);	
	}
	
}
