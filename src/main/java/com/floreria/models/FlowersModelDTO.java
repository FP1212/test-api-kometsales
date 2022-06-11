package com.floreria.models;

import org.springframework.stereotype.Component;

@Component
public class FlowersModelDTO implements Comparable<FlowersModelDTO>{
	
	String name;
	float price;
	
	public FlowersModelDTO() {		
	}
	
	public FlowersModelDTO( String name, float price) {
		
		this.name=name;
		this.price=price;
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
	public int compareTo(FlowersModelDTO o) {
		return this.getName().compareTo(o.getName());
	}

}
