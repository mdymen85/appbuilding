package com.control.building.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "PERSONS")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id; 
	
	private final String name;
	
	@Embedded
	private final Identification identification;
	
	@ManyToMany
	private final List<Apartment> apartments;
	
	public Person() {
		this.id = null;
		this.name = null;
		this.identification = null;
		this.apartments = null;
	}
	
	@Builder
	public Person(Long id, String name, Identification identification, List<Apartment> apartments) {
		this.id = id;
		this.name = name;
		this.identification = identification;
		this.apartments = apartments;
	}
	
}
