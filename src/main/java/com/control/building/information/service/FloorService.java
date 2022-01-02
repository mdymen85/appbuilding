package com.control.building.information.service;

import org.springframework.stereotype.Service;

import com.control.building.information.exception.FloorDoesNotExistException;
import com.control.building.information.model.Floor;
import com.control.building.information.repository.FloorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FloorService {

	private final FloorRepository floorRepository;
	
	public Floor load(Integer building, Integer floor) {
		return floorRepository.findByNumber(building, floor)
				.orElseThrow(() -> new FloorDoesNotExistException(building, floor));
	}
	
}
