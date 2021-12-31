package com.control.building.information.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseFloorDTO {

	private Integer number;
	private List<ResponseApartmentDTO> apartments;
	
}
