package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Apartment;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long>{

	public Optional<Apartment> findByNumber(Integer floor, Integer apartment);
}
