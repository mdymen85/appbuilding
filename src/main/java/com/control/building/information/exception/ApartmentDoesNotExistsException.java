package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ApartmentDoesNotExistsException extends BaseClassException {

	private Integer apartment;
	private Integer floor;
	private String uuid;
	
	public ApartmentDoesNotExistsException(String uuid, Integer apartment, Integer floor) {
		this.apartment = apartment;
		this.floor = floor;
		this.uuid = uuid;
	}
	
	@Override
	protected String getCode() {
		return "BID-5";
	}

}
