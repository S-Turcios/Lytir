package com.santos.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.santos.models.States;

public interface StateRepository extends CrudRepository<States, Long> {
	List<States> findAll();
	States findByName(String name);
}
