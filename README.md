# My CSV Parser
A really simple CSV parser. Supports parsing of CSV in string format and CSV file.

## Getting Started

1. Build library:

```
cd my-csv-parser
mvn clean install
```

2. Add to maven dependencies:

```
<dependency>
  <groupId>com.eraine</groupId>
  <artifactId>my-csv-parser</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Example Usage

Currently, this supports two input types via `String`:
```
CSVData[] result = new CSVParser()
    .from("Message Id;Body;Delivery Status\n" +
            "\"1;\"\"\"Hi there\"\";\"\"SUCCESS\"\"\"\n")
    .parse();
```

and `File`:

```
CSVData[] result = new CSVParser()
    .from(new File("sample_csv.csv"))
    .parse();
```

This returns an array of `CsvData` which contains a map of keys and their corresponding values. To traverse through 
the results:

```
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
```