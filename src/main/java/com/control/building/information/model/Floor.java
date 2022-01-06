package com.control.building.information.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "FLOORS")
@Getter
@AllArgsConstructor
public class Floor implements Comparable<Floor> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Integer number;
	
	@ManyToOne
	private Building building;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "floor_id" )
	private Set<Apartment> apartments;
	
	public Floor() {}
	
	
	@Builder
	public Floor(Integer number, Building building, HashSet<Apartment> apartments, Long id) {	
		this.id = null;
		this.apartments = apartments == null ? new TreeSet<Apartment>() : apartments;
		this.number = number;
		this.numberValidation();
		
		this.building = building;
		this.buildingValidation();	
		
	}
	
	private void numberValidation() {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	private void buildingValidation() {
		if (building == null) {
			throw new IllegalArgumentException();
		}
	}


	public void addApartment(Apartment apartment) {
		if (this.apartments == null) {
			throw new IllegalArgumentException();
		}
		
		if (!this.apartments.contains(apartment)) {
			this.apartments.add(apartment);
		}
	}
	
	public Optional<Apartment> findApartment(Apartment apartment) {
		return this.apartments.stream()
				.filter(a -> a.getNumber() == apartment.getNumber())
				.findFirst();
	}
	
	public List<Apartment> getApartments() {
		return new ArrayList<Apartment>(this.apartments);
	}

	@Override
	public int compareTo(Floor floor) {
		if (floor == null) {
			throw new IllegalArgumentException();
		}
		return this.number.intValue() - floor.getNumber().intValue();
	}
	
}
