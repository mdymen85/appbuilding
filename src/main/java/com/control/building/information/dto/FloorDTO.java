package com.control.building.information.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FloorDTO {

	private Integer number;
	private List<ApartmentDTO> apartments = new ArrayList<ApartmentDTO>();
	
}
