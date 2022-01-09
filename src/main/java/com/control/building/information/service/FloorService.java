package com.control.building.information.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.control.building.information.dto.FloorDTO;
import com.control.building.information.exception.FloorAlreadyExistsException;
import com.control.building.information.exception.FloorDoesNotExistException;
import com.control.building.information.model.Floor;
import com.control.building.information.repository.FloorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FloorService {

	private final BuildingService buildingService; 
	private final FloorRepository floorRepository;
	private final BuildingMapper buildingMapper;
	
	public Floor load(String uuid, Integer floor) {
		return floorRepository.find(uuid, floor)
				.orElseThrow(() -> new FloorDoesNotExistException(uuid, floor)); 
	}
	
	public Floor save(String uuid, FloorDTO floorDTO) {
		Optional<Floor> optFloor = floorRepository.find(uuid, floorDTO.getNumber());
		
		if (optFloor.isEmpty()) {
			var building = this.buildingService.load(uuid);
			
			//In CQRS pattern, probably i wont need this.
			var floor = buildingMapper.toBidirectionalFloor(floorDTO, building);
						
			this.floorRepository.save(floor);
			
			return floor;
		}
		
		throw new FloorAlreadyExistsException(uuid, floorDTO.getNumber());
		
	}
	
}
