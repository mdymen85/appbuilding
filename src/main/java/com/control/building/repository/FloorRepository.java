package com.control.building.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.building.model.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Long> {

}
