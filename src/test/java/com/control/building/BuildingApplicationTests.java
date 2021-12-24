package com.control.building;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.model.Building;
import com.control.building.model.Floor;
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
	private DelegateBuilding txDelegateBuilding;
	
	@Test
	void createAndSaveFloorAndBuilding() {
		
		var building = Building.builder()
				.name("Iria")
				.build();
		
		var floor1 = new Floor(1, building);
		var floor2 = new Floor(2, building);
				
		this.txDelegateBuilding.save(building);
				
		Iterable<Building> list = this.buildingRepository.findAll();
		var buildingLoaded = list.iterator().next();

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
		
		var buildingSaved = this.txDelegateBuilding.save(building);
		
		var buildingLoaded = this.buildingRepository.findById(buildingSaved.getId()).get();	
		
		assertEquals(buildingLoaded.getName(), building.getName());
		
		var floor1 = new Floor(1, building);
		
		var floor1Saved = this.floorRepository.save(floor1);
		
		var floor1Loaded = this.floorRepository.findById(floor1Saved.getId());
		
		assertEquals(floor1Loaded.get().getNumber(), floor1.getNumber());
		assertEquals(floor1Loaded.get().getBuilding().getName(), floor1.getBuilding().getName());
		
	}

}
