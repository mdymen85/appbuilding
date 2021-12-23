package com.control.building.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BUILDING")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private String name;
	
	@Setter(value = AccessLevel.PRIVATE)
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "building_id")
	private List<Floor> floors;
	
	public void setName(String name) {
		
//		if (Strings.isNullOrEmpty(name)) {
//			throw new IllegalArgumentException();
//		}		
		
		this.name = name;
	}
	
	public void addFloor(Floor floor) {
		if (this.floors == null) {
			this.floors = new ArrayList<Floor>();
		}
		
		this.floors.add(floor);
	}
	
}
