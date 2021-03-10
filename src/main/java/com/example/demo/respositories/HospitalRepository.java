package com.example.demo.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Hospital;

@Component
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	Hospital findById(int id);
	
	
}
