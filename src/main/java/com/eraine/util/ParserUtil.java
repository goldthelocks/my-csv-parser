package com.eraine.util;

import com.eraine.Constant;

public class ParserUtil {

	private ParserUtil() {
	}

	public static boolean isWrappedInQuotes(String text) {
		return text.matches(Constant.PATTERN_DOUBLE_QUOTES);
	}

	public static String removeWrappedQuotes(String text) {
		return text.replaceAll(Constant.PATTERN_DOUBLE_QUOTES, Constant.EMPTY);
	}

}
