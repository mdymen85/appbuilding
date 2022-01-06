package com.control.building.information.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.control.building.information.dto.ApartmentDTO;
import com.control.building.information.dto.ResponseApartmentDTO;
import com.control.building.information.service.ApartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ApartmentController {
	
	private final ApartmentService apartmentService;
	private final ModelMapper modelMapper;

	@RequestMapping(path = "/v1/building/{uuid}/floor/{floor}/apartment/{apartment}", method = RequestMethod.GET)
	public ResponseEntity<ResponseApartmentDTO> loadApartment(@PathVariable String uuid, @PathVariable Integer floor, @PathVariable Integer apartment) {
		var response = modelMapper.map(apartmentService.load(uuid, floor, apartment), ResponseApartmentDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/v1/building/{uuid}/floor/{floor}/apartment", method = RequestMethod.POST) 
	public ResponseEntity<ResponseApartmentDTO> save(@PathVariable String uuid, @PathVariable Integer floor, @RequestBody ApartmentDTO apartmentDTO){
		var apartment = apartmentService.create(uuid, floor, apartmentDTO);
		var response = modelMapper.map(apartment, ResponseApartmentDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
