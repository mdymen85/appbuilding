package com.control.building.model;

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
