package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class FloorDoesNotExistException extends BaseClassException {

	private Integer building;
	private Integer number;
	
	public FloorDoesNotExistException(Integer building, Integer number) {
		this.building = building;
		this.number = number;
	}
	
	@Override
	protected String getCode() {
		return "BID-4";
	}
	
	public Integer getBuilding() {
		return building;
	}
	
	public Integer getNumber() {
		return number;
	}

}
