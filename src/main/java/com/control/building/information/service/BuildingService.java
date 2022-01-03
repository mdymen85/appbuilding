package com.control.building.information.service;

import org.springframework.stereotype.Service;

import com.control.building.information.dto.BuildingDTO;
import com.control.building.information.exception.BuildingDoesNotExistsException;
import com.control.building.information.model.Building;
import com.control.building.information.repository.BuildingRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BuildingService {

	private final BuildingRepository buildingRepository;
	private final BuildingMapper buildingMapper;
	
	public Building save(BuildingDTO buildingDTO) {
		Building building = this.buildingMapper.toBuilding(buildingDTO);
		return this.buildingRepository.save(building);	
	}
	
	public Building save(Building building) {
		return this.buildingRepository.save(building);	
	}
	
	public Building load(String uuid) {
		return this.buildingRepository.findByUuid(uuid)
				.orElseThrow(() -> new BuildingDoesNotExistsException(uuid));
	}

	public void delete(String uuid) {
		this.buildingRepository.deleteByUuid(uuid);
		
	}

	public Building loadWithFloors(String uuid) {
		return this.buildingRepository.loadWithFloors(uuid)
				.orElseThrow(() -> new BuildingDoesNotExistsException(uuid));
	}
	
}
