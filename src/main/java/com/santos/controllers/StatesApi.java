package com.santos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santos.models.States;
import com.santos.services.StateServices;

@RestController
public class StatesApi {
	private final StateServices stateService;
	
	
	public StatesApi(StateServices stateService) {
		this.stateService = stateService;
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="/api/states" , method=RequestMethod.POST)
	public States create(@RequestParam(value="name") String name) {
		States state = new States(name);
		return stateService.createState(state);
	}
	@RequestMapping(value="/api/allstates")
	public List<States> index() {
		return stateService.allStates();
	}
	@RequestMapping(value="/api/states/{id}/delete", method=RequestMethod.DELETE)
	public States delete(@PathVariable("id")Long id) {
		return stateService.deleteState(id);
	}
}
