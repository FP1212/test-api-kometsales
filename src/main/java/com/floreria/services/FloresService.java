package com.floreria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floreria.models.FloresModel;

@Service
public class FloresService {

	@Autowired
	private static List<FloresModel> listFlores;
	
	public static List<FloresModel> setList(List<FloresModel> list) {
		listFlores = list;
		return listFlores;
	}
	
}
