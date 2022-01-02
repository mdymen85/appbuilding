package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Floor;

@Repository
public class FloorRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Optional<Floor> find(Floor floor) {
		return this.find(floor.getId());
	}
	
	public Optional<Floor> find(Long id) {
		return Optional.of(entityManager.find(Floor.class, id));
	}
}
