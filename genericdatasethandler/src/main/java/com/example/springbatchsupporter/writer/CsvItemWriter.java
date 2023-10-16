package com.example.springbatchsupporter.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

@Component
public class CsvItemWriter<T> implements ItemWriter<T> {

    private String outputPath;
    private String delimiter;
    private String[] fieldNames;

    public CsvItemWriter() {
    }

    @Override
    public void write(Chunk<? extends T> items) throws Exception {
        FlatFileItemWriter<T> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(outputPath));

        LineAggregator<T> lineAggregator = createLineAggregator(fieldNames, delimiter);
        writer.setLineAggregator(lineAggregator);

        writer.open(new ExecutionContext());
        writer.write(items);
        writer.close();
    }
    
    public LineAggregator<T> createLineAggregator(String[] fieldNames, String delimiter) {
        DelimitedLineAggregator<T> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(delimiter);

        BeanWrapperFieldExtractor<T> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(fieldNames);
        lineAggregator.setFieldExtractor(fieldExtractor);

        return lineAggregator;
    }


    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
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

