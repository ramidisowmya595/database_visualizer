package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Data;

public class DataItemProcessor implements ItemProcessor<Data,Data> {



	@Override
	public Data process(Data item) throws Exception {
		System.err.println(item);
		return item;
	}

}
