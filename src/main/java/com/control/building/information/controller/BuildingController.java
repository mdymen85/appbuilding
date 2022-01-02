package com.control.building.information.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.control.building.information.dto.BuildingDTO;
import com.control.building.information.dto.ResponseBuildingDTO;
import com.control.building.information.dto.ResponseFloorDTO;
import com.control.building.information.service.BuildingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class BuildingController {

	private final BuildingService buildingService;
	private final ModelMapper modelMapper;
	
	@RequestMapping(path = "/v1/building", method = RequestMethod.POST)
	public ResponseEntity<ResponseBuildingDTO> create(@RequestBody BuildingDTO buildingDTO) {		
		var building = buildingService.save(buildingDTO);
		var response = modelMapper.map(building, ResponseBuildingDTO.class);
		return new ResponseEntity<>(response, HttpStatus.CREATED);		
	}
	
	@RequestMapping(path = "/v1/building/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseBuildingDTO> load(@PathVariable Long id) {
		var building = buildingService.load(id);
		var response = modelMapper.map(building, ResponseBuildingDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/v1/building/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		

	}
	
	@RequestMapping(path = "/v1/building/{id}/floor", method = RequestMethod.GET)
	public ResponseEntity<ResponseFloorDTO> loadFloor(@PathVariable Long id) {
		var response = modelMapper.map(buildingService.loadFloor(id), ResponseFloorDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
