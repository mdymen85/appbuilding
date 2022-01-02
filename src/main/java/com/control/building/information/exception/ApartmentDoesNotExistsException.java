package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ApartmentDoesNotExistsException extends BaseClassException {

	private Integer apartment;
	private Integer floor;
	
	public ApartmentDoesNotExistsException(Integer apartment, Integer floor) {
		this.apartment = apartment;
		this.floor = floor;
	}
	
	@Override
	protected String getCode() {
		return "BID-5";
	}

}
