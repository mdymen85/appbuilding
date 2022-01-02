package com.control.building.information.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class FloorDoesNotExistException extends BaseClassException {

	private Long id;
	
	public FloorDoesNotExistException(Long id) {
		this.id = id;
	}
	
	@Override
	protected String getCode() {
		return "BID-4";
	}
	
	public Long getId() {
		return id;
	}

}
