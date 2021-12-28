package com.control.building.people.model;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Embeddable
@Data
@Getter
public class Identification {

	private final String identification;
	
	public Identification() {
		this.identification = null;
	}
	
	@Builder
	public Identification(String identification) {
		if (identification == null) {
			throw new IllegalArgumentException();
		}
		this.identification = identification;
		
	}
	
}
