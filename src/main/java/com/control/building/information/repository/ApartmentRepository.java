package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Apartment;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long>{

	@Query("SELECT apartment FROM Apartment apartment JOIN FETCH apartment.floor floor JOIN FETCH floor.building building WHERE building.uuid = :uuid AND floor.number = :floor AND apartment.number = :apartment")
	public Optional<Apartment> find(@Param("uuid") String uuid, @Param("floor") Integer floor, @Param("apartment") Integer apartment);
}
