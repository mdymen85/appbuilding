package com.control.building.people.model;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OwnedApartmentRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Optional<OwnedApartment> find(Long id) {
		return Optional.of(entityManager.find(OwnedApartment.class, id));
	}
}
