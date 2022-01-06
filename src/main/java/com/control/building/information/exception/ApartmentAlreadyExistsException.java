package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ApartmentAlreadyExistsException extends BaseClassException {

	private final String uuid;
	private final Integer floorNumber;
	private final Integer apartmentNumber;
	
	public ApartmentAlreadyExistsException(String uuid, Integer floorNumber, Integer apartmentNumber) {
		this.uuid = uuid;
		this.floorNumber = floorNumber;
		this.apartmentNumber = apartmentNumber;
	}
	
	@Override
	protected String getCode() {
		return "BID-8";
	}

}
