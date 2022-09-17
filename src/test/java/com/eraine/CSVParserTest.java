package com.eraine;

import com.eraine.data.CSVData;
import com.eraine.data.CSVValue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CSVParserTest {

	@Test
	public void given_CsvString_When_Parse_Then_ReturnArray() {
		CSVData[] result = new CSVParser()
				.from("Message Id;Body;Delivery Status\n" +
						"\"1;\"\"\"Hi there\"\";\"\"SUCCESS\"\"\"\n" +
						"\"2;\"\"Please book appointment for the 1/2\"\";\"\"SUCCESS\"\"\"\n" +
						"\"3;\"\"Emergency contact updated for your account\"\";\"\"UNDELIVERED\"\"\"\n" +
						"\"4;\"\"You have a friend request from 'Pete'\"\";\"\"UNDELIVERED\"\"\"\n" +
						"\"5;\"\"There is a new sign in attempt for your account.\"\";\"\"SUCCESS\"\"\"\n")
				.parse();

		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(5);

		for (CSVData data : result) {
			for (Map.Entry<String , CSVValue<?>> csvData : data.getData().entrySet()) {
				String key = csvData.getKey();

				// retrieves the value in raw type
				Object value = csvData.getValue().getRaw();

				// retrieves the value in Number type
				// Number numberValue = csvData.getValue().getAsNumber();

				// retrieves the value in String type
				// String stringValue = csvData.getValue().getAsString();

				System.out.println(key + ": " + value);
			}
		}

	}

	@Test
	public void given_CsvFile_When_Parse_Then_ReturnArray() throws IOException {
		URL fileUrl = getClass().getClassLoader().getResource("sample_data.csv");

		CSVData[] result = new CSVParser()
				.from(new File(fileUrl.getFile()))
				.parse();

		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void given_EmptyString_When_Parse_Then_ThrowException() {
		new CSVParser()
				.from("")
				.parse();
	}

	@Test(expected = IOException.class)
	public void given_NullFile_When_Parse_Then_ThrowException() throws IOException {
		new CSVParser()
				.from(new File("somewhere"))
				.parse();
	}

	@Test(expected = IllegalArgumentException.class)
	public void given_BlankDelimiter_When_Parse_Then_ThrowException() {
		new CSVParser()
				.delimiter(' ')
				.from("Message Id;Body;Delivery Status")
				.parse();
	}

}
