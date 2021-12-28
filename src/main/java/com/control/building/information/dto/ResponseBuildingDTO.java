package com.control.building.information.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBuildingDTO {

	private final String name;
	private final String address;	
}
