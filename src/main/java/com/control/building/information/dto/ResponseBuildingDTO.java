package com.control.building.information.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class ResponseBuildingDTO {

	private String name;
	private String address;	
	private String uuid;
	private List<ResponseFloorDTO> floors = new ArrayList<>();
	
}
