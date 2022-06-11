package com.floreria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floreria.models.FloresModel;
import com.floreria.services.FloresService;

@RestController
@RequestMapping("/flores")
public class FloresController {
	
	@Autowired
	FloresService floresService;
	
	@PostMapping
	public List<FloresModel> saveFlowersList(@RequestBody List<FloresModel> list) {
		floresService.setList(list);
		return null;
	}	
}
