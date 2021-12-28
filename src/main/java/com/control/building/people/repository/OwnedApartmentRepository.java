package com.control.building.people.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.control.building.people.model.OwnedApartment;

@Repository
public class OwnedApartmentRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Optional<OwnedApartment> find(Long id) {
		return Optional.of(entityManager.find(OwnedApartment.class, id));
	}
}
