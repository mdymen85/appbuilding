package com.control.building.information.exception;

@SuppressWarnings("serial")
public class BuildingBasicInformationException extends BaseClassException {
	
	public BuildingBasicInformationException() {
		super();
	}

	@Override
	public String getCode() {
		return "BID-1";
	}
	
}
