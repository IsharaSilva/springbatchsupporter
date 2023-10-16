package com.example.springbatchsupporter.utils;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class CsvDelimitedLineTokenizer extends DelimitedLineTokenizer {

	public CsvDelimitedLineTokenizer(String[] csvHeaders) {
		super();
		setNames(csvHeaders);
	}
}
