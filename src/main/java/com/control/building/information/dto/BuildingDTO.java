package com.control.building.information.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.control.building.information.model.Floor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class BuildingDTO {

	private final String name;
	private final String address;
	private final String uuid;
	private final List<FloorDTO> floors = new ArrayList<FloorDTO>();
	
}
