package com.control.building.information.dto;

import java.util.List;

import lombok.Data;

@Data
public class FloorDTO {

	private Integer number;
	private List<ApartmentDTO> apartments;
	
}
