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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traffic other = (Traffic) obj;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	

}
