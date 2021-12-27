package com.control.building.people.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "APARTMENTS")
@Data
@Immutable
public class OwnedApartment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	private final String address;
	
	private final Integer number;
	
	@ManyToMany(mappedBy = "ownedApartments")
	private final List<Owner> owners;
	
	public OwnedApartment() {
		this.id = null;
		this.address = null;
		this.owners = new ArrayList<Owner>();
		this.number = null;
	}
	
	@Builder
	public OwnedApartment(Long id, String address, List<Owner> owners, Integer number) {
		this.id = id;
		this.address = address;
		this.owners = owners;
		this.number = number;
	}
		
}
