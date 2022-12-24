package com.example.seek.domain;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Response {

	private int total;
	
	private LinkedHashMap<Object, Integer> carsPerDay;
	
	private List<Traffic> mostCars;
	
	private String leastCars;
	

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Traffic> getMostCars() {
		return mostCars;
	}

	public void setMostCars(List<Traffic> mostCars) {
		this.mostCars = mostCars;
	}

	public String getLeastCars() {
		return leastCars;
	}

	public void setLeastCars(String leastCars) {
		this.leastCars = leastCars;
	}

	public LinkedHashMap<Object, Integer> getCarsPerDay() {
		return carsPerDay;
	}

	public void setCarsPerDay(LinkedHashMap<Object, Integer> carsPerDay) {
		this.carsPerDay = carsPerDay;
	}
	
	
	
}
