package com.control.building.information.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.control.building.information.dto.FloorDTO;
import com.control.building.information.dto.ResponseFloorDTO;
import com.control.building.information.service.FloorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class FloorController {
	
	private final FloorService floorService;
	private final ModelMapper modelMapper;
	
	@RequestMapping(path = "/v1/building/{uuid}/floor", method = RequestMethod.POST)
	public ResponseEntity<ResponseFloorDTO> save(@PathVariable String uuid, @RequestBody FloorDTO floorDTO) {
		var response = modelMapper.map(this.floorService.save(uuid, floorDTO), ResponseFloorDTO.class);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/v1/building/{uuid}/floor/{floor}", method = RequestMethod.GET)
	public ResponseEntity<ResponseFloorDTO> load(@PathVariable String uuid, @PathVariable Integer floor) {
		var response = modelMapper.map(floorService.load(uuid, floor), ResponseFloorDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
