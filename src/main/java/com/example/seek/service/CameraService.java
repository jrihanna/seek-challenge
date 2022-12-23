package com.example.seek.service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.seek.domain.Response;
import com.example.seek.domain.Traffic;
import com.example.seek.file.FileReader;

@Service
public class CameraService {
	
	@Autowired
	private FileReader reader;
	
	private List<Traffic> traffics;
	
	public void readFile() {
		try {
			traffics = reader.processFile("/sample.txt"); // file path should come from somewhere else
		} catch (IOException e) {
			throw new RuntimeException("Error in reading file", e);
		}
	}
	
	public Response calculateAll() {
		readFile();
		
		Response response = new Response();

		response.setTotal(calculateTotal());
		response.setCarsPerDay(calculateTotalPerDay());
		response.setMostCars(calculateTopThree());
		
		return response;
	}
	
	public Integer calculateTotal() {
		return traffics.stream().map(Traffic::getCars).reduce(0, Integer::sum);
	}
	
	public LinkedHashMap<Object, Integer> calculateTotalPerDay() {
		return traffics.stream().collect(
				Collectors.groupingBy(t -> t.getTime().toLocalDate(),
						LinkedHashMap::new,
						Collectors.reducing(0, Traffic::getCars, Integer::sum)));
	}

	public List<Traffic> calculateTopThree() {
		return traffics.stream().sorted().limit(3).collect(Collectors.toList());
	}
}
