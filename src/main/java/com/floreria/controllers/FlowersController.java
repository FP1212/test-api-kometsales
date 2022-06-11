package com.floreria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.floreria.models.FlowersModel;
import com.floreria.models.FlowersModelDTO;
import com.floreria.services.FlowersService;

@RestController
@RequestMapping("/flores")
public class FlowersController {
	
	@Autowired
	FlowersService flowersService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveFlowersList(@RequestBody List<FlowersModel> list) {
		flowersService.setFlowersList(list);
	}
	
	@GetMapping
	public List<FlowersModelDTO> getFlowersList() {
		return flowersService.getFlowersList();
	}
	
	@GetMapping(path="/{name}")
	public List<FlowersModel> getFlowersByName(@PathVariable("name") String name) {
		return flowersService.getFlowersByName(name);
	}

	@GetMapping
	@RequestMapping(path = "/prices", params="price")
	public List<FlowersModel> getFlowersListByPrice(@RequestParam(defaultValue=FlowersService.MAX_FLOWER_PRICE) int price) {
		return flowersService.getFlowersByPrice(price);
	}

	@DeleteMapping(path="/{id}")
	public String deleteFlowersById(@PathVariable("id") int id) {
		return flowersService.deleteFlowerById(id);
	}
}
