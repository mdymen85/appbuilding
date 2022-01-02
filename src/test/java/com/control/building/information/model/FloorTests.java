package com.control.building.information.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FloorTests {
//
//	@Autowired
//	private BuildingCon
	
	/**
	 * As building has a Set collection of floors
	 * this test verify that doesnt allow repeated floors
	 * inside de building
	 */
	@Test
	void repeatedFloor() {
		
		var building = Building.builder()
				.address("7 Street")
				.name("Classic")
				.build();
		
		var floor1 = Floor.builder()
				.number(70)
				.building(building)
				.build();
		
		var floor2 = Floor.builder()
				.number(70)
				.building(building)
				.build();
		
		var floor3 = Floor.builder()
				.number(80)
				.building(building)
				.build();
		
		building.addFloor(floor1); 
		building.addFloor(floor2); //repeated
		building.addFloor(floor3);
		
		assertEquals(building.getFloors().size(), 2);					
		
	}
	
}
