package com.example.seek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.seek.controller.Controller;
import com.example.seek.domain.Response;

@SpringBootTest
class SeekApplicationTests {
	
	@Autowired
	private Controller controller;

	@Test
	void contextLoads() {
	}

	@Test
	void test_api() {
		Response r = controller.calculate();
		assertNotNull(r);
		assertEquals(398, r.getTotal());
		assertEquals("2021-12-01T15:00-2021-12-01T23:30 20", r.getLeastCars());
	}
}
