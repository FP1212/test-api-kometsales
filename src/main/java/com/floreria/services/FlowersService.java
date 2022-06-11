package com.floreria.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.floreria.models.FlowersModel;

@Service
public class FlowersService {

	private static List<FlowersModel> listFlores;
	private static final String KOMET_QUOTE="-kometsales";
	private static final int MAX_FLOWER_PRICE = 20;
	
	public static void setFlowersList(List<FlowersModel> list) {
		FlowersService.listFlores = list;		
	}
	
	public static List<FlowersModel> getFlowersList(){
		List<FlowersModel> listFloresKomet = new ArrayList<>();		
		Iterator<FlowersModel> iterator = FlowersService.listFlores.iterator();
		 
		while(iterator.hasNext())
		{
			FlowersModel flower = iterator.next();
			FlowersModel quotedFlower = new FlowersModel(flower.getId(),flower.getName()+ KOMET_QUOTE,flower.getPrice());
			listFloresKomet.add(quotedFlower);
		}
		
		return listFloresKomet;
	}
	
	public static List<FlowersModel> getFlowersByPrice(){
		List<FlowersModel> listFloresKomet = new ArrayList<>();		
		Iterator<FlowersModel> iterator = FlowersService.listFlores.iterator();
		 
		while(iterator.hasNext())
		{
			FlowersModel flower = iterator.next();
			if (flower.getPrice()>= MAX_FLOWER_PRICE) {
				FlowersModel quotedFlower = new FlowersModel(flower.getId(),flower.getName()+ KOMET_QUOTE,flower.getPrice());
				listFloresKomet.add(quotedFlower);
			}	
		}		
			
		return listFloresKomet;
	}
	
	public static Boolean deleteFlowerFromList(int id) {	
		
		return true;
	}
	
	public static Boolean deleteFlowerFromList(String name) {
		return true;
	}
	
}
