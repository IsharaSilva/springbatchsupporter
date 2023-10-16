package com.example.springbatchsupporter.processor;

import java.util.function.Function;

import org.springframework.batch.item.ItemProcessor;

public class DataItemProcessor<I, O> implements ItemProcessor<I, O> {
	
	private final Function<I, O> itemProcessingMachanism;

    public DataItemProcessor(Function<I, O> itemProcessingMachanism) {
		super();
		this.itemProcessingMachanism  = itemProcessingMachanism;
	}

	@Override
    public O process(I input) throws Exception {
        return itemProcessingMachanism.apply(input);
    }
}