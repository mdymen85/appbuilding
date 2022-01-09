package com.control.building.information.model;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.BuildingApplication;
import com.control.building.information.AbstractApplicationTest;
import com.control.building.information.dto.FloorDTO;
import com.control.building.information.exception.FloorAlreadyExistsException;

import io.restassured.RestAssured;

@SpringBootTest(classes = BuildingApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class FloorTests extends AbstractApplicationTest {

	@LocalServerPort
	private int localServerPort;
	
    @BeforeEach
    public void restAssuredPort() {
    	RestAssured.port = localServerPort;  
    }
	
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
		assertThrows(FloorAlreadyExistsException.class, () -> building.addFloor(floor2));
	}
	
	@Test
	void createExistingFloor() {
		var building = Building.builder()
				.name("Classic")
				.name("10 setember")
				.build();
		
		FloorBidirectional.floorBidirectional()
				.number(1)
				.building(building)
					.build();
		
		var buildingSaved = this.txDelegateBuilding.save(building);		

		var floor = FloorDTO.builder()
				.apartments(null)
				.number(1)
				.build();
		
		var r = with()
			.header("Content-Type","application/json")
			.body(floor)
		.when()
			.post("/api/v1/building/{uuid}/floor", buildingSaved.getUuid())
		.then()				
			.statusCode(400)
			.body("code", is("BID-7"))
			.body("message", is(String.format("Floor %s in the building %s already exists", floor.getNumber(), buildingSaved.getUuid())));					
		
	}
	
	
	@Test
	void saveFloorOnCreatedBuilding() {
		var building = Building.builder()
				.name("Classic")
				.name("10 setember")
				.build();
		
		Floor.builder()
				.number(1)
				.building(building)
					.build();
		
		var buildingSaved = this.txDelegateBuilding.save(building);		

		var floor = FloorDTO.builder()
				.apartments(null)
				.number(2)
				.build();
		
		with()
			.header("Content-Type","application/json")
			.body(floor)
		.when()
			.post("/api/v1/building/{uuid}/floor", buildingSaved.getUuid())
		.then()				
			.statusCode(201)
			.body("number", is(floor.getNumber()));		
		
		var floorSaved = this.floorRepository.find(buildingSaved.getUuid(), floor.getNumber()).get();

		assertEquals(floor.getNumber(), floorSaved.getNumber());
		assertEquals(building.getAddress(), floorSaved.getBuilding().getAddress());
		assertEquals(building.getName(), floorSaved.getBuilding().getName());
		
	}
	
}
