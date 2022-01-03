package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Long> {

	@Query("SELECT floor FROM Floor floor JOIN FETCH floor.building building WHERE building.uuid = :uuid AND floor.number = :floor")
	Optional<Floor> find(@Param("uuid") String uuid, Integer floor);

}
