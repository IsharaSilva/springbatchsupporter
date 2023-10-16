package com.example.springbatchsupporter.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;

public class DataItemCompositeWriter<T> extends CompositeItemWriter<T> {

	public DataItemCompositeWriter(List<ItemWriter<? super T>> itemWriters) {
		super();
		setDelegates(itemWriters);
	}
}