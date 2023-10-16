package com.example.springbatchsupporter.writer;

import java.util.function.Function;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DataItemWriter<T, R> implements ItemWriter<T> {
    
	private final Function<T,R> itemWritingMachanism;

    public DataItemWriter(Function<T,R> itemWritingMachanism) {
		super();
		this.itemWritingMachanism  = itemWritingMachanism;
	}
   
	@Override
	public void write(Chunk<? extends T> chunk) throws Exception {
		System.out.println("Thread Name: " + Thread.currentThread().getName());	
		
		for (T item : chunk) {
            R result = itemWritingMachanism.apply(item);
            System.out.println(result);
        }
	}
}