package com.control.building.information.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

@Entity
@Table(name = "FLOORS")
@Data
@AllArgsConstructor
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@Column(nullable = false)
	private final Integer number;
	
	@ManyToOne
	private final Building building;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "floor_id" )
	private final List<Apartment> apartments;	//get precisa por una copia inmutable
	
	public Floor() {
		this.id = null;
		this.number = null;
		this.building = null;
		this.apartments = new ArrayList<Apartment>();
	}
	
	@Builder
	public Floor(Integer number, Building building, ArrayList<Apartment> apartments, Long id) {	
		this.id = null;
		this.apartments = apartments == null ? new ArrayList<Apartment>() : apartments;
		this.number = number;
		this.numberValidation();
		
		this.building = building;
		this.buildingValidation();
		
		this.building.addFloor(this);		
		
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
	
}
