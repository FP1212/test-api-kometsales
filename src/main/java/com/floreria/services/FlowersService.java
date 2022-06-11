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

	// Static Flowers List Variable
	static List<FlowersModel> flowersList;

	private static final String KOMET_QUOTE = "-kometsales";
	public static final String MAX_FLOWER_PRICE = "20";

	static Logger logger = (Logger) LoggerFactory.getLogger(FlowersService.class);

	public void setFlowersList(List<FlowersModel> list) {
		FlowersService.flowersList = list;
	}
	
	public List<FlowersModelDTO> getFlowersList() {
		List<FlowersModelDTO> flowersListKomet = new ArrayList<>();

		// Deep Clone
		Iterator<FlowersModel> iterator = FlowersService.flowersList.iterator();

		while (iterator.hasNext()) {
			FlowersModel flower = iterator.next();
			FlowersModelDTO quotedFlower = new FlowersModelDTO(flower.getName() + KOMET_QUOTE, flower.getPrice());
			flowersListKomet.add(quotedFlower);
		}

		Collections.sort(flowersListKomet);

		return flowersListKomet;
	}

	public List<FlowersModel> getFlowersByPrice(int price) {
		List<FlowersModel> flowersListKomet = new ArrayList<>();

		// Deep Clone
		Iterator<FlowersModel> iterator = FlowersService.flowersList.iterator();

		while (iterator.hasNext()) {
			FlowersModel flower = iterator.next();
			if (flower.getPrice() >= price) {
				FlowersModel quotedFlower = new FlowersModel(flower.getId(), flower.getName(),
						flower.getPrice());
				flowersListKomet.add(quotedFlower);
			}
		}

		return flowersListKomet;
	}

	public String deleteFlowerById(int id) {
		try {
			if (FlowersService.flowersList.removeIf(flower -> flower.getId() == id))
				return String.format("Item identfied by id:%d, Deleted", id);
			else
				return String.format("Item id:%d not found, omitted", id);
		} catch (Exception e) {
			logger.error(e.toString());
			return e.toString();
		}
	}

	public List<FlowersModel> getFlowersByName(String name) {
		return FlowersService.flowersList.stream().filter(flower -> flower.getName().contains(name))
				.collect(Collectors.toList());
	}

}
