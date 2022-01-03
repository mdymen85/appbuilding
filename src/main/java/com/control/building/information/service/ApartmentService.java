package com.control.building.information.service;

import org.springframework.stereotype.Service;

import com.control.building.information.exception.ApartmentDoesNotExistsException;
import com.control.building.information.model.Apartment;
import com.control.building.information.repository.ApartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {

	private final ApartmentRepository apartmentRepository;

	public Apartment load(String uuid, Integer floor, Integer apartment) {
		return this.apartmentRepository.find(uuid, floor, apartment)
				.orElseThrow(() -> new ApartmentDoesNotExistsException(uuid, floor, apartment));
	}

}
