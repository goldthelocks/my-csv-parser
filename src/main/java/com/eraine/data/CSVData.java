package com.eraine.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CSVData {

	private final Map<String, CSVValue<?>> data;

	public CSVData() {
		this.data = new LinkedHashMap<>();
	}

	public void put(String key, CSVValue<?> value) {
		this.data.put(key, value);
	}

	public Map<String, CSVValue<?>> getData() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CSVData csvData = (CSVData) o;
		return Objects.equals(data, csvData.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

}
