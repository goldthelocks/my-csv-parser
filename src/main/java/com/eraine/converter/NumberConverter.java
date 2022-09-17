package com.eraine.converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.function.Function;

public class NumberConverter implements Function<String, Number> {

	@Override
	public Number apply(String text) {
		try {
			return NumberFormat.getInstance().parse(text);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Failed to parse value to number: " + text, ex);
		}
	}

}
