package com.eraine;

import com.eraine.converter.CSVValueConverter;
import com.eraine.data.CSVData;
import com.eraine.util.ParserUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

	private char delimiter;
	private String[] headers;
	private List<String> lines;

	private static final int INDEX_CSV_CONTENT_START = 1;

	private final CSVValueConverter csvValueConverter;

	public CSVParser() {
		this.delimiter = Constant.DEFAULT_DELIMITER;
		this.csvValueConverter = new CSVValueConverter();
	}

	public CSVParser delimiter(char delimiter) {
		if (delimiter == Constant.BLANK_CHAR) {
			throw new IllegalArgumentException("Delimiter must not be blank");
		}

		this.delimiter = delimiter;
		return this;
	}

	public CSVParser from(File csvFile) throws IOException {
		if (csvFile == null) {
			throw new IllegalArgumentException("CSV file must not be null");
		}

		this.lines = Files.readAllLines(csvFile.toPath());
		return this;
	}

	public CSVParser from(String csvString) {
		if (StringUtils.isBlank(csvString)) {
			throw new IllegalArgumentException("CSV string must not be null");
		}

		this.lines = getLines(csvString);
		return this;
	}

	public CSVData[] parse() {
		if (lines.size() == 0) {
			return new CSVData[0];
		}

		populateHeaders(lines.get(0));

		return lines.stream()
				.skip(INDEX_CSV_CONTENT_START)
				.map(this::createCsvData)
				.toArray(CSVData[]::new);
	}

	private CSVData createCsvData(String line) {
		CSVData csvData = new CSVData();
		String[] columnTokens = getTokens(ParserUtil.removeWrappedQuotes(line));

		for (int tokenIndex = 0; tokenIndex < headers.length; tokenIndex++) {
			csvData.put(headers[tokenIndex], csvValueConverter.convert(columnTokens[tokenIndex]));
		}

		return csvData;
	}

	private void populateHeaders(String headerLine) {
		String[] headers = getHeaders(headerLine);

		if (headers.length == 0) {
			throw new IllegalArgumentException("No headers found");
		}

		this.headers = headers;
	}

	private String[] getHeaders(String text) {
		if (ParserUtil.isWrappedInQuotes(text)) {
			return getTokens(ParserUtil.removeWrappedQuotes(text));
		}

		return getTokens(text);
	}

	private List<String> getLines(String csvString) {
		return Arrays.asList(csvString.split(Constant.NEW_LINE));
	}

	private String[] getTokens(String text) {
		if (StringUtils.isBlank(text)) {
			return new String[0];
		}

		return text.split(String.valueOf(delimiter));
	}

}
