package com.example.springbatchsupporter.utils;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;

public class CsvRowMapper<T> extends DefaultLineMapper<T> {
    private AbstractLineTokenizer delimitedLineTokenizer;
    private FieldSetMapper<T> fieldSetMapper;
    
	public CsvRowMapper(AbstractLineTokenizer delimitedLineTokenizer, FieldSetMapper<T> fieldSetMapper) {
		super();
        this.delimitedLineTokenizer = delimitedLineTokenizer;
        this.fieldSetMapper = fieldSetMapper;
		setLineTokenizer(delimitedLineTokenizer);
		setFieldSetMapper(fieldSetMapper);
	}
}
