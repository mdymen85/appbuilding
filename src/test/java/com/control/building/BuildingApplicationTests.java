package com.control.building;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;
import com.control.building.people.model.OwnedApartment;
import com.control.building.people.model.OwnedApartmentRepository;
import com.control.building.repository.BuildingRepository;
import com.control.building.repository.FloorRepository;

@SpringBootTest
@Transactional
class BuildingApplicationTests extends AbstractBuildingApplication  {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private OwnedApartmentRepository ownedApartmentRepository;
	
	@Autowired
	private DelegateBuilding txDelegateBuilding;
	
	@Test
	void createBuilding() {
		var building = Building.builder()
				.name("Classic")
				.build();
		
		var savedBuilding = this.txDelegateBuilding.save(building);
		
		var loadedBuilding = this.buildingRepository.findById(savedBuilding.getId()).get();
		
		assertEquals(loadedBuilding.getName(), building.getName());
	}
	
	@Test
	void createAndSaveFloorAndBuilding() {
		
		var building = Building.builder()
				.name("Iria")
				.build();		
		
		var floor1 = Floor.builder()
				.number(1)
				.building(building)
				.build();
		
		var floor2 = Floor.builder()
				.number(2)
				.building(building)
				.build();		
				
		var savedBuilding = this.txDelegateBuilding.save(building);
				
		var buildingLoaded = this.buildingRepository.findById(savedBuilding.getId()).get();

		assertEquals(buildingLoaded.getName(), building.getName());
		
		List<Floor> floors = buildingLoaded.getFloors();
	
		var floor1Loaded = floors.get(0);
		assertEquals(floor1Loaded.getNumber(), floor1.getNumber());
		assertEquals(floor1Loaded.getBuilding().getName(), floor1.getBuilding().getName());
		
		var floor2Loaded = floors.get(1);
		assertEquals(floor2Loaded.getNumber(), floor2.getNumber());
		assertEquals(floor2Loaded.getBuilding().getName(), floor2.getBuilding().getName());

	}
	
	@Test
	void saveFloorOnCreatedBuilding() {
		var building = Building.builder()
				.name("Classic")
				.build();
		
		var floor1 = Floor.builder()
				.number(1)
				.building(building)
					.build();
		
		var buildingSaved = this.txDelegateBuilding.save(building);
		
		System.out.println(floor1.getId());
		
		var buildingLoaded = this.buildingRepository.findById(buildingSaved.getId()).get();	
		
		assertEquals(buildingLoaded.getName(), building.getName());
				
		var floor1Loaded = this.floorRepository.find(floor1);

		assertEquals(floor1Loaded.get().getNumber(), floor1.getNumber());
		assertEquals(floor1Loaded.get().getBuilding().getName(), floor1.getBuilding().getName());
		
	}
	
	@Test
	void createApartments() {
		
		var building = Building.builder()
				.name("Classic")
				.build();
		
		var floor = Floor.builder()
				.building(building)
				.number(1)
				.build();
		
		var apartment = Apartment.builder()
				.floor(floor)
				.number(10)
				.address("54 Street")
				.build();
		
		this.txDelegateBuilding.save(building);
		
		Optional<OwnedApartment> ownedApartment = this.ownedApartmentRepository.find(apartment.getId());
		
		assertEquals(ownedApartment.get().getAddress(), apartment.getAddress());
		assertEquals(ownedApartment.get().getNumber(), apartment.getNumber());
		
	}
	
	@Test
	void createPerson() {
		
	}

}
