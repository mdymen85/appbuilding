package com.control.building.information.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {

	Optional<Building> findByUuid(String uuid);

	void deleteByUuid(String uuid);

	@Query("SELECT building FROM Building building JOIN FETCH building.floors floors WHERE building.uuid = :uuid")
	Optional<Building> loadWithFloors(@Param("uuid") String uuid);

}
