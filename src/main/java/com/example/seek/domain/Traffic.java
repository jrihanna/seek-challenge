package com.example.seek.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class Traffic implements Comparable<Traffic> {

	private LocalDateTime time;
	private Integer cars;

	public Traffic(LocalDateTime time, Integer cars) {
		super();
		this.time = time;
		this.cars = cars;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Integer getCars() {
		return cars;
	}

	@Override
	public int compareTo(Traffic u) {
		if (getCars() == null || u.getCars() == null) {
			return 0;
		}

		// compared descending
		return u.getCars().compareTo(getCars());
	}

}
