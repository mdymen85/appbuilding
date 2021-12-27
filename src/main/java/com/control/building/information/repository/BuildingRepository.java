package com.control.building.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {

}
