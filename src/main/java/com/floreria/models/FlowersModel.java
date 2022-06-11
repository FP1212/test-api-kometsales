package com.floreria.models;

import org.springframework.stereotype.Component;

@Component
public class FlowersModel implements Comparable<FlowersModel>{
	
	int id;
	String name;
	float price;
	
	public FlowersModel() {		
	}
	
	public FlowersModel(int id, String name, float price) {
		this.id=id;
		this.name=name;
		this.price=price;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int compareTo(FlowersModel o) {
		return this.getName().compareTo(o.getName());
	}	
}
