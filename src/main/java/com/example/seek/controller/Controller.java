package com.example.seek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.seek.domain.Response;
import com.example.seek.service.CameraService;

@RestController("/")
public class Controller {
	
	@Autowired
	private CameraService service;
	
	@GetMapping("/parser")
	@ResponseBody
    public Response calculateTotalPerDay() {
        return service.calculateAll();
    }
}
