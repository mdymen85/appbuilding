package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class FloorDoesNotExistException extends BaseClassException {

	private String uuid;
	private Integer number;
	
	public FloorDoesNotExistException(String uuid, Integer number) {
		this.uuid = uuid;
		this.number = number;
	}
	
	@Override
	protected String getCode() {
		return "BID-4";
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public Integer getNumber() {
		return number;
	}

}
