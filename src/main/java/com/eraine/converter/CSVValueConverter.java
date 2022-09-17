package com.eraine.converter;

import com.eraine.Constant;
import com.eraine.data.CSVValue;
import com.eraine.util.ParserUtil;

public class CSVValueConverter {

	public CSVValue<?> convert(String value) {
		if (isNumeric(value)) {
			return new CSVValue<>(new NumberConverter().apply(value));
		}

		return new CSVValue<>(new StringConverter().apply(ParserUtil.removeWrappedQuotes(value)));
	}

	private boolean isNumeric(String text) {
		if (ParserUtil.isWrappedInQuotes(text)) {
			return false;
		}

		return text.matches(Constant.PATTERN_DIGIT);
	}

}
