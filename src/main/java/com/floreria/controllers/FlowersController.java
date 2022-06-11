package com.floreria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floreria.models.FlowersModel;
import com.floreria.services.FlowersService;

@RestController
@RequestMapping("/flores")
public class FlowersController {
	
	@PostMapping
	public void saveFlowersList(@RequestBody List<FlowersModel> list) {		
		FlowersService.setFlowersList(list);
	}	
	
	@GetMapping
	public List<FlowersModel> getFlowersList (){
		return FlowersService.getFlowersList();
	}
	
	@GetMapping
	@RequestMapping("/prices")
	public List<FlowersModel> getFlowersListByPrice (){
		return FlowersService.getFlowersByPrice();
	}
}
