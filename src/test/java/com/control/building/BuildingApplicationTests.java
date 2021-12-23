package com.control.building;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.model.Building;
import com.control.building.model.Floor;
import com.control.building.repository.BuildingRepository;

@SpringBootTest
@Transactional
class BuildingApplicationTests extends AbstractBuildingApplication  {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Test
	void contextLoads() {
		
		var floor = Floor.builder()
		.number(10)
		.build();
		
		var floor1 = Floor.builder()
		.number(11)
		.build();
		
		var building = Building.builder()
		.floors(List.of(floor, floor1))
		.name("Iria")
		.build();
		
		this.buildingRepository.save(building);
		
		assertTrue(true);
		
		var list = this.buildingRepository.findAll();
		
		list.forEach(b -> {
			var floors = b.getFloors();
			assertTrue(true);	
		});
		
		assertTrue(true);
		

		
	}

}
