package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Data;
import com.example.demo.model.People;


public class Appitemprocess implements ItemProcessor<People,Data> {

	@Override
	public Data process(People item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(item);
		Data d = new Data("MONGO",item.getEmail(),item.getUsername(),item.getPassword());
		return d;
	}

}
