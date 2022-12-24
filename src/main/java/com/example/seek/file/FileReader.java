package com.example.seek.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;

import com.example.seek.domain.Traffic;


@Component
public class FileReader {
	
	public List<Traffic> processFile(String source) throws IOException {
		List<Traffic> traffics = new ArrayList<Traffic>();
		
		try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(readFile(source)))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	String[] split = line.split(" ");
	        	Traffic traffic = new Traffic(LocalDateTime.parse(split[0]),
	        			Integer.parseInt(split[1])
	        			);
	        	
	        	traffics.add(traffic);
	        }
	    }
		
		return traffics;
	}
	
	private InputStream readFile(String source) {
	    InputStream inputStream = getClass().getResourceAsStream(source);
	    
	    if(inputStream == null) {
	    	throw new RuntimeException("File Not Found");
	    }
	    	
		return inputStream;
	}

}
