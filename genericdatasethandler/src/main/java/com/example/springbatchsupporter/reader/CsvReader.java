package com.example.springbatchsupporter.reader;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

public class CsvReader<T> extends FlatFileItemReader<T> {

	public CsvReader(Resource csvFile, LineMapper<T> lineMapper) {
		super();
		setResource(csvFile);
		setLineMapper(lineMapper);
		setLinesToSkip(1);
	}
}