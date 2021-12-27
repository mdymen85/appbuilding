package com.control.building.information.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Identification {

	private String identification;
	
	public Identification(String identificacion) {
		if (identification == null) {
			throw new IllegalArgumentException();
		}
	}
	
}
