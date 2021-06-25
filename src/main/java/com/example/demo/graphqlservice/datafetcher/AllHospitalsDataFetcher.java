package com.example.demo.graphqlservice.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Hospital;
import com.example.demo.respositories.HospitalRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllHospitalsDataFetcher implements DataFetcher<List<Hospital>>{

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Override
	public List<Hospital> get(DataFetchingEnvironment environment) throws Exception {
		// TODO Auto-generated method stub
		return hospitalRepository.findAll();
	}

}
