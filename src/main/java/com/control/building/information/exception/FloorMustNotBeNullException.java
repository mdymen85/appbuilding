package com.control.building.information.exception;

@SuppressWarnings("serial")
public class FloorMustNotBeNullException extends BaseClassException {

	@Override
	protected String getCode() {
		return "BID-2";
	}

}
