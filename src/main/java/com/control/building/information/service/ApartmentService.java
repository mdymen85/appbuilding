package com.control.building.information.service;

import org.springframework.stereotype.Service;

import com.control.building.information.dto.ApartmentDTO;
import com.control.building.information.exception.ApartmentAlreadyExistsException;
import com.control.building.information.exception.ApartmentDoesNotExistsException;
import com.control.building.information.model.Apartment;
import com.control.building.information.repository.ApartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {

	private final FloorService floorService;
	private final ApartmentRepository apartmentRepository;

	public Apartment load(String uuid, Integer floor, Integer apartment) {
		return this.apartmentRepository.find(uuid, floor, apartment)
				.orElseThrow(() -> new ApartmentDoesNotExistsException(uuid, floor, apartment));
	}

	public Apartment create(String uuid, Integer floorNumber, ApartmentDTO apartmentDTO) {

		var optApartment = this.apartmentRepository.find(uuid, floorNumber, apartmentDTO.getNumber());

		if (optApartment.isEmpty()) {
			var floor = floorService.load(uuid, floorNumber);	
			
			//In CQRS pattern, probably i wont need this.
			var apartment = ApartmentType.UNIDIRECTIONAL.toApartment(apartmentDTO, floor);
			
			this.apartmentRepository.save(apartment);
		}
		
		
		throw new ApartmentAlreadyExistsException(uuid, floorNumber, apartmentDTO.getNumber());
	}

}
