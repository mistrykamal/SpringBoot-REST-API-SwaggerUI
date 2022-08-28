package com.example.demo.graphqlservice.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Hospital;
import com.example.demo.respositories.HospitalRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class HospitalDataFetcher implements DataFetcher<Hospital> {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public Hospital get(DataFetchingEnvironment environment) throws Exception {
		// TODO Auto-generated method stub
		String id = environment.getArgument("id");

		return hospitalRepository.findById(Integer.parseInt(id));
	}

}
