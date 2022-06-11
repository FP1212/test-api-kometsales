package com.floreria.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.floreria.models.FlowersModel;
import com.floreria.models.FlowersModelDTO;

import ch.qos.logback.classic.Logger;

@Service
public class FlowersService {

	static Logger logger = (Logger) LoggerFactory.getLogger(FlowersService.class);

	private static List<FlowersModel> listFlores;
	private static final String KOMET_QUOTE = "-kometsales";
	private static final int MAX_FLOWER_PRICE = 20;

	public static void setFlowersList(List<FlowersModel> list) {
		FlowersService.listFlores = list;
		
	}

	public static List<FlowersModelDTO> getFlowersList() {
		List<FlowersModelDTO> listFloresKomet = new ArrayList<>();

		// Deep Clone
		Iterator<FlowersModel> iterator = FlowersService.listFlores.iterator();

		while (iterator.hasNext()) {
			FlowersModel flower = iterator.next();
			FlowersModelDTO quotedFlower = new FlowersModelDTO(flower.getName() + KOMET_QUOTE, flower.getPrice());
			listFloresKomet.add(quotedFlower);
		}

		Collections.sort(listFloresKomet);

		return listFloresKomet;
	}

	public static List<FlowersModel> getFlowersByPrice() {
		List<FlowersModel> listFloresKomet = new ArrayList<>();

		// Deep Clone
		Iterator<FlowersModel> iterator = FlowersService.listFlores.iterator();

		while (iterator.hasNext()) {
			FlowersModel flower = iterator.next();
			if (flower.getPrice() >= MAX_FLOWER_PRICE) {
				FlowersModel quotedFlower = new FlowersModel(flower.getId(), flower.getName() + KOMET_QUOTE,
						flower.getPrice());
				listFloresKomet.add(quotedFlower);
			}
		}

		return listFloresKomet;
	}

	public static String deleteFlowerById(int id) {
		try {
			if (FlowersService.listFlores.removeIf(flower -> flower.getId() == id))
				return String.format("Item identfied by id:%d, Deleted", id);
			else
				return String.format("Item id:%d not found, omitted", id);
		} catch (Exception e) {
			logger.error(e.toString());
			return e.toString();
		}
	}

	public static List<FlowersModel> getFlowersByName(String name) {
		return FlowersService.listFlores.stream().filter(flower -> flower.getName().contains(name))
				.collect(Collectors.toList());
	}

}
