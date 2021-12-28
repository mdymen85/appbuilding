package com.control.building.people.repository;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.building.people.model.Owner;

import lombok.RequiredArgsConstructor;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
