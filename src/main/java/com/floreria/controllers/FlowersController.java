package com.floreria.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.floreria.models.FlowersModel;
import com.floreria.models.FlowersModelDTO;
import com.floreria.services.FlowersService;

@RestController
@RequestMapping("/flores")
public class FlowersController {

	@PostMapping
	public void saveFlowersList(@RequestBody List<FlowersModel> list) {
		FlowersService.setFlowersList(list);
	}
	
	@GetMapping
	public List<FlowersModelDTO> getFlowersList() {
		return FlowersService.getFlowersList();
	}
	
	@GetMapping(path="/{name}")
	public List<FlowersModel> getFlowersByName(@PathVariable("name") String name) {
		return FlowersService.getFlowersByName(name);
	}

	@GetMapping
	@RequestMapping("/highprices")
	public List<FlowersModel> getFlowersListByPrice(@RequestParam(defaultValue="20") int price) {
		return FlowersService.getFlowersByPrice();
	}

	@DeleteMapping(path="/{id}")
	public String deleteFlowersById(@PathVariable("id") int id) {
		return FlowersService.deleteFlowerById(id);
	}
}
