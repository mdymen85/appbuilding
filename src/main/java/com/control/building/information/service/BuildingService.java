package com.control.building.information.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.control.building.information.dto.BuildingDTO;
import com.control.building.information.model.Apartment;
import com.control.building.information.model.Building;
import com.control.building.information.repository.ApartmentRepository;
import com.control.building.information.repository.BuildingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BuildingService {

	private final BuildingRepository buildingRepository;
	private final ApartmentRepository apartmentRepository;
	private final BuildingMapper buildingMapper;
	
	public Building save(BuildingDTO buildingDTO) {
		Building building = this.buildingMapper.toBuilding(buildingDTO);
		return this.buildingRepository.save(building);	
	}
	
	public Building load(Long id) {
		return this.buildingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException());
	}
	
	public Apartment loadAparment(Long id) {
		return this.apartmentRepository.find(id)
				.orElseThrow(() -> new IllegalArgumentException());
	}
	
}
