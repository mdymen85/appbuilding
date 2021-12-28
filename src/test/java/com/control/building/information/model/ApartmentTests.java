package com.control.building.information.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.information.AbstractApplicationTest;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;
import com.control.building.people.model.OwnedApartment;

@SpringBootTest
@Transactional
public class ApartmentTests extends AbstractApplicationTest {

	/**
	 * create an apartment and change its number from
	 * building aggregate root
	 */
	@Test
	void updateApartmentFromBuilding() {
		var building = Building.builder()
				.name("Better Hall")
				.address("Constituyente")
				.build();
		
		var floor = Floor.builder()
				.building(building)
				.number(0)
				.build();	
		
		var apartment = Apartment.builder()
				.floor(floor)
				.number(3)
				.build();
		
		Apartment.builder()
				.floor(floor)
				.number(4)
				.build();
		
		Apartment.builder()
				.floor(floor)
				.number(5)
				.build();
		
		Apartment.builder()
				.floor(floor)
				.number(6)
				.build();
		
		Apartment.builder()
				.floor(floor)
				.number(5)
				.build();
		
		this.txDelegateBuilding.save(building);
		
		//check if the apartment is saved correctly, and change the apartment number
		var apartmentLoaded = this.apartmentRepository.find(apartment.getId()).get();
		
		assertEquals(apartmentLoaded.getNumber(), apartment.getNumber());
		assertEquals(apartmentLoaded.getFloorNumber(), floor.getNumber());
		
		var buildingLoaded = apartmentLoaded.getBuilding();
		
		buildingLoaded.updateApartmentNumber(apartmentLoaded, 2);
		
		//save the building after change de apartment number
		this.txDelegateBuilding.save(buildingLoaded);
		
		var buildingLoadedLast = this.buildingRepository.findById(building.getId()).get();
		
		//validate that the new apartment with number 2 exists and the old apartment
		//with number 1 not longer exists
		var apartment2Loaded = buildingLoadedLast.findApartment(2);
		assertTrue(apartment2Loaded.isPresent());
		
		var apartment1Loaded = buildingLoadedLast.findApartment(1);
		assertFalse(apartment1Loaded.isPresent());

	}
	
}
