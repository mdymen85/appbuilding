package com.control.building.information.exception;

@SuppressWarnings("serial")
public class ApartmentMustNotBeNullException extends BaseClassException {

	@Override
	protected String getCode() {
		return "BID-3";
	}

}
