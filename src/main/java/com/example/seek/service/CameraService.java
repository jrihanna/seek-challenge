package com.example.seek.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seek.domain.Response;
import com.example.seek.domain.Traffic;
import com.example.seek.file.FileReader;

@Service
public class CameraService {
	
	@Autowired
	private FileReader reader;
	
	public List<Traffic> readFile() {
		try {
			return reader.processFile("/sample.txt"); // file path should come from somewhere else
		} catch (IOException e) {
			throw new RuntimeException("Error in reading file", e);
		}
	}
	
	public Response calculateAll() {
		List<Traffic> traffics = readFile();
		
		Response response = new Response();

		response.setTotal(calculateTotal(traffics));
		response.setCarsPerDay(calculateTotalPerDay(traffics));
		response.setMostCars(calculateTopThree(traffics));
		response.setLeastCars(calculateMin(traffics));
		
		return response;
	}
	
	public Integer calculateTotal(List<Traffic> traffics) {
		return traffics.stream().map(Traffic::getCars).reduce(0, Integer::sum);
	}
	
	public LinkedHashMap<Object, Integer> calculateTotalPerDay(List<Traffic> traffics) {
		return traffics.stream().collect(
				Collectors.groupingBy(t -> t.getTime().toLocalDate(),
						LinkedHashMap::new,
						Collectors.reducing(0, Traffic::getCars, Integer::sum)));
	}

	public List<Traffic> calculateTopThree(List<Traffic> traffics) {
		return traffics.stream().sorted().limit(3).collect(Collectors.toList());
	}

	public String calculateMin(List<Traffic> traffics) {
		
		Integer leastNumber = Integer.MAX_VALUE;
		String least = "";
		
		for (int i = 0; i < traffics.size() - 3; i++) {
			Traffic current = traffics.get(i);
			int sum = current.getCars();
			
			for(int j = i+1; j < i + 3; j++) {
				sum += traffics.get(j).getCars();
			}
			
			if(sum < leastNumber) {
				leastNumber = sum;
				least = current.getTime() + "-" + traffics.get(i+2).getTime() + " " + sum;
			}
		}
		
		return least;
	}
}
