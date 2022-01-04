package com.control.building.information.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import com.control.building.BuildingApplication;
import com.control.building.information.AbstractApplicationTest;
import com.control.building.information.DelegateBuilding;
import com.control.building.information.dto.BuildingDTO;
import com.control.building.information.dto.FloorDTO;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.model.Floor;
import com.control.building.information.repository.ApartmentRepository;
import com.control.building.information.repository.BuildingRepository;
import com.control.building.information.repository.FloorRepository;
import com.control.building.people.model.Identification;
import com.control.building.people.model.OwnedApartment;
import com.control.building.people.model.Owner;
import com.control.building.people.repository.OwnedApartmentRepository;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(classes = BuildingApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class BuildingTests extends AbstractApplicationTest  {

	@LocalServerPort
	private int localServerPort;
	
    @BeforeEach
    public void restAssuredPort() {
    	RestAssured.port = localServerPort;  
    }
	
	@Test
	void createBuilding() {

		var building = Building.builder()
				.name("Classic")
				.address("54 Street")
				.build();
		
		String uuid = 
		with()
			.header("Content-Type","application/json")
			.body(building)
		.when()
			.post("/api/v1/building")
		.then()				
			.statusCode(201)
			.body("name", is(building.getName()))
			.body("address", is(building.getAddress()))
		.extract()
			.path("uuid");			
		
		var loadedBuilding = this.buildingRepository.findByUuid(uuid).get();
		
		assertEquals(loadedBuilding.getName(), building.getName());
		assertEquals(loadedBuilding.getAddress(), building.getAddress());
	}
	
	@Test
	void createFloorsAndBuilding() {
		
		var floor1 = FloorDTO.builder()
				.number(1)
				.build();
		
		var floor2 = FloorDTO.builder()
				.number(2)
				.build();
		
		var building = BuildingDTO.builder()
				.address("Rua 10 de Setembro")
				.name("Classic")
				.uuid(null)
				.build();
		
		building.getFloors().add(floor1);
		building.getFloors().add(floor2);
					
		String uuid = 
			with()
				.header("Content-Type","application/json")
				.body(building)
			.when()
				.post("/api/v1/building")
			.then()				
				.statusCode(201)
				.body("name", is(building.getName()))
				.body("address", is(building.getAddress()))
			.extract()
				.path("uuid");
				
		var buildingLoaded = this.buildingRepository.loadWithFloors(uuid).get();

		assertEquals(buildingLoaded.getName(), building.getName());					
	
		var floor1Loaded = buildingLoaded.findFloor(floor1.getNumber()).get();
		assertEquals(floor1Loaded.getNumber(), floor1.getNumber());
		assertEquals(floor1Loaded.getBuilding().getName(), building.getName());
		
		var floor2Loaded = buildingLoaded.findFloor(floor2.getNumber()).get();
		assertEquals(floor2Loaded.getNumber(), floor2.getNumber());
		assertEquals(floor2Loaded.getBuilding().getName(), building.getName());

	}	
	
	@Test 
	void buildingWithoutName() {
		var building = Building.builder()
				.address("")
				.name("a  ")
				.build();
	}

	@Test
	void deleteFloor() {
		
	}
}
