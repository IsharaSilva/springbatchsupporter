package com.example.springbatchsupporter.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class ExcelItemWriter<T> extends FlatFileItemWriter<T> {

    private String outputPath;
    private String delimiter;
    private String[] fieldNames;
    
    public ExcelItemWriter(String outputPath, String delimiter, String[] fieldNames) {
        this.outputPath = outputPath;
        this.delimiter = delimiter;
        this.fieldNames = fieldNames;
    	setResource(new FileSystemResource(outputPath));
        setShouldDeleteIfEmpty(true);
        setShouldDeleteIfExists(true);

        setLineAggregator(new DelimitedLineAggregator<T>() {
            {
                setDelimiter(delimiter);
                setFieldExtractor(new BeanWrapperFieldExtractor<T>() {
                    {
                        setNames(fieldNames);
                    }
                });
            }
        });
    }
    public String getOutputPath() {
        return outputPath;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }
}