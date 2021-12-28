package com.control.building.people.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.control.building.information.model.Apartment;

import javax.persistence.JoinColumn;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "OWNERS")
@Getter
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id; 
	
	private final String name;
	
	@Embedded
	private final Identification identification;
	
	@ManyToMany
	@JoinTable(
			name = "OWNER_APARTMENT",
			joinColumns = {@JoinColumn(name = "owner_id")},
			inverseJoinColumns = {@JoinColumn(name = "apartment_id")}
			)
	private final List<Apartment> ownedApartments;
	
	public Owner() {
		this.id = null;
		this.name = null;
		this.identification = null;
		this.ownedApartments = null;
	}
	
	@Builder
	public Owner(Long id, String name, Identification identification, List<Apartment> apartments) {
		this.id = id;
		this.name = name;
		this.identification = identification;
		this.ownedApartments = apartments;
		
	}
	
}
