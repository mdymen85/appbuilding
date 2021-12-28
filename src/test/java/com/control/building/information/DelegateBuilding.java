package com.control.building.information;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.information.model.Building;
import com.control.building.information.repository.BuildingRepository;
import com.control.building.people.model.Owner;
import com.control.building.people.repository.OwnerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class DelegateBuilding {

	private final BuildingRepository buildingRepository;
	private final OwnerRepository ownerRepository;
	
	public Building save(Building building) {
		return this.buildingRepository.save(building);
	}
	
	public Owner save(Owner owner) {
		return this.ownerRepository.save(owner);				
	}
	
}
