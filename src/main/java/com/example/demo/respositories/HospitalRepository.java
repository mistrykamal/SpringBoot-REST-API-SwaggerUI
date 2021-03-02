package com.example.demo.respositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
	
	
}
