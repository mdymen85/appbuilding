package com.control.building.information.exception;

@SuppressWarnings("serial")
public class FloorAlreadyExistsException extends BaseClassException {

	private final String uuid;
	private final Integer number;
	
	public FloorAlreadyExistsException(String uuid, Integer number) {
		this.uuid = uuid;
		this.number = number;
	}
	
	@Override
	protected String getCode() {
		return "BID-7";
	}
	
	public String getUuid() {
		return this.uuid;
	}

	public Integer getNumber() {
		return this.number;
	}
}
