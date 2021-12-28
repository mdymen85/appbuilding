package com.control.building.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.control.building.information.repository.ApartmentRepository;
import com.control.building.information.repository.BuildingRepository;
import com.control.building.information.repository.FloorRepository;
import com.control.building.people.repository.OwnedApartmentRepository;

@ActiveProfiles("test")
public class AbstractApplicationTest {

	@Autowired
	protected BuildingRepository buildingRepository;
	
	@Autowired
	protected FloorRepository floorRepository;
	
	@Autowired
	protected ApartmentRepository apartmentRepository;
	
	@Autowired
	protected OwnedApartmentRepository ownedApartmentRepository;
	
	@Autowired
	protected DelegateBuilding txDelegateBuilding;
	
}
