package com.control.building.information.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.building.information.model.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Long> {

	Optional<Floor> findByNumber(Integer integer, Integer floor);

}
