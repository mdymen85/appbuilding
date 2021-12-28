package com.control.building.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;
import com.control.building.people.model.OwnedApartment;

public class OwnerTests {

//	@Test
//	void createApartmentOwner() {
//		
//		var building = Building.builder()
//				.name("Classic")
//				.address("54 Street")
//				.build();
//		
//		var floor = Floor.builder()
//				.building(building)
//				.number(1)
//				.build();
//		
//		var apartment = Apartment.builder()
//				.floor(floor)
//				.number(10)
//				.build();
//		
//		this.txDelegateBuilding.save(building);
//		
//		Optional<OwnedApartment> ownedApartment = this.ownedApartmentRepository.find(apartment.getId());
//		
//		assertEquals(ownedApartment.get().getNumber(), apartment.getNumber());
//		
//		var apartment2 = Apartment.builder()
//				.floor(floor)
//				.number(11)
//				.build();
//		
//		floor.getApartments().add(apartment2);
//		
//		this.txDelegateBuilding.save(building);
//		
//		var floorLoaded = this.floorRepository.find(floor).get();
//		
//		System.out.println(floorLoaded.getApartments().size());
//		
//		floor.addApartment(apartment2);
//		
//		this.txDelegateBuilding.save(building);
//
//		floorLoaded = this.floorRepository.find(floor).get();
//		
//		System.out.println(floorLoaded.getApartments().size());
//		
//		
//		int i = 0;
//		
//	}
	
}
