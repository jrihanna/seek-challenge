package com.example.seek.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.seek.domain.Traffic;
import com.example.seek.file.FileReader;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CameraServiceTest {

	private CameraService service;
	
	private FileReader reader;
	
	
	private List<Traffic> TRAFFICS = List.of(new Traffic(LocalDateTime.parse("2021-12-01T05:00:00"), 5),
			new Traffic(LocalDateTime.parse("2021-12-01T05:30:00"), 12),
			new Traffic(LocalDateTime.parse("2021-12-01T06:00:00"), 14),
			new Traffic(LocalDateTime.parse("2021-12-01T06:30:00"), 15),
			new Traffic(LocalDateTime.parse("2021-12-09T00:00:00"), 4));
	
	@BeforeEach
	void init() {
		service = new CameraService();
		reader = new FileReader();
	}
	
	@Test
	void all_methods_should_be_called() {
		service = Mockito.spy(CameraService.class);
		ReflectionTestUtils.setField(service, "reader", reader);
		Mockito.when(service.readFile()).thenReturn(TRAFFICS);

		service.calculateAll();
		Mockito.verify(service, Mockito.times(1)).calculateTotal(TRAFFICS);
		Mockito.verify(service, Mockito.times(1)).calculateTotalPerDay(TRAFFICS);
		Mockito.verify(service, Mockito.times(1)).calculateTopThree(TRAFFICS);
		Mockito.verify(service, Mockito.times(1)).calculateMin(TRAFFICS);
	}
	
	@Test
	void throw_exception_file_not_found() {
		Assertions.assertThrows(RuntimeException.class, () -> reader.processFile("/sample2.txt"));
	}
	
	@Test
	void test_calculate_total() {
		Integer total = service.calculateTotal(TRAFFICS);
		
		assertEquals(total, 50);
	}
	
	@Test
	void test_calculate_total_per_day() {
		LinkedHashMap<Object, Integer> totalPerDay = service.calculateTotalPerDay(TRAFFICS);
		LinkedHashMap<Object, Integer> expected = new LinkedHashMap<Object, Integer>();
		expected.put(LocalDate.parse("2021-12-01"), 46);
		expected.put(LocalDate.parse("2021-12-09"), 4);

		assertEquals(totalPerDay, expected);
	}
	
	@Test
	void test_calculate_top_three() {
		List<Traffic> topThree = service.calculateTopThree(TRAFFICS);
		List<Traffic> expected = List.of(new Traffic(LocalDateTime.parse("2021-12-01T06:30:00"), 15),
				new Traffic(LocalDateTime.parse("2021-12-01T06:00:00"), 14),
				new Traffic(LocalDateTime.parse("2021-12-01T05:30:00"), 12)
				);

		assertEquals(topThree, expected);
	}
	
	@Test
	void test_calculate_least_three() {
		String leastThree = service.calculateMin(TRAFFICS);
		String expected = "2021-12-01T05:00-2021-12-01T06:00 31";

		assertEquals(leastThree, expected);
	}
}
