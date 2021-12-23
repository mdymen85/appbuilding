package com.control.building;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.control.building.model.Building;
import com.control.building.model.Floor;
import com.control.building.repository.BuildingRepository;

@SpringBootTest
public class Tests {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Test
	void contextLoads() {
		
		var floor = Floor.builder()
		.number(10)
		.build();
		
		var building = Building.builder()
		.floors(List.of(floor))
		.name("Iria")
		.build();
		
		this.buildingRepository.save(building);
		
		assertTrue(true);
		
	}

	
}
