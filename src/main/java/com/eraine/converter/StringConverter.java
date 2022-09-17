package com.eraine.converter;

import java.util.function.Function;

public class StringConverter implements Function<String, String> {

	@Override
	public String apply(String text) {
		return text;
	}

}
