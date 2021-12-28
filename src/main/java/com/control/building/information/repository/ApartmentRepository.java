package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Apartment;

@Repository
public class ApartmentRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Optional<Apartment> find(Long id) {
		return Optional.of(entityManager.find(Apartment.class, id));
	}
	
}
