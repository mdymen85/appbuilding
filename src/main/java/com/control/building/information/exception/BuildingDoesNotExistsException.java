package com.control.building.information.exception;

@SuppressWarnings("serial")
public class BuildingDoesNotExistsException extends BaseClassException {

	private final String uuid;
	
	public BuildingDoesNotExistsException(String uuid) {
		this.uuid = uuid;
	}

	@Override
	protected String getCode() {
		return "BID-6";
	}
	
	public String getUuid() {
		return uuid;
	}

}
