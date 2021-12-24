package com.control.building;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.model.Building;
import com.control.building.repository.BuildingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DelegateBuilding {

	private final BuildingRepository buildingRepository;
	
	public Building save(Building building) {
		return this.buildingRepository.save(building);
	}
	
}
