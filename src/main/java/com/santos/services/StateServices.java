package com.santos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.models.States;
import com.santos.repos.StateRepository;
@Service
public class StateServices {
	@Autowired
	private StateRepository stateRepository;
	public States createState(States state) {
		return stateRepository.save(state);
	}
	public List<States> allStates(){
		return stateRepository.findAll();
	}
	public States findByName(String name) {
		return stateRepository.findByName(name);
	}
	public States findStatesById(Long id){
		Optional<States> s = stateRepository.findById(id);
		if(s.isPresent()) {
			return s.get();
		}else {
			return null;
		}
	}
	public StateServices() {
		// TODO Auto-generated constructor stub
	}
	public States deleteState(Long id) {
		stateRepository.deleteById(id);
		return null;
	}

}
