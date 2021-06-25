package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.beans.Hospital;
import com.example.demo.respositories.HospitalRepository;

@Service
public class HospitalService {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	private static RestTemplate restTemplate;

//	private List<Hospital> hospitalList = new ArrayList<>(Arrays.asList(
//
//			new Hospital(1001, "Apollo Hospital", "Chennai", 3.8),
//
//			new Hospital(1002, "Global Hospital", "Chennai", 3.5),
//
//			new Hospital(1003, "VCare Hospital", "Bangalore", 3)));

	public List<Hospital> getAllHospitals() {
//		List<Hospital> hospitals = hospitalList;
		return hospitalRepository.findAll();
	}

	public Hospital getHospital(int id) {
		return hospitalRepository.findById(id);
	}

	public void addHospital(Hospital hospital) {
//		hospitalList.stream().forEach(hospi -> hospitalRepository.save(hospi));
		hospitalRepository.save(hospital);
	}

	public void updateHospital(int id, Hospital hospital) {
//		Hospital eHospital = hospitalList.stream().filter(h -> h.getId() == id).findFirst().get();
		Hospital eHospital = hospitalRepository.findById(id);
		eHospital.setCity(hospital.getCity());
		eHospital.setName(hospital.getName());
		eHospital.setRating(hospital.getRating());
		hospitalRepository.save(eHospital);
	}

	public void deleteHospital(int id) {
//		Hospital dHospital = hospitalList.stream().filter(h -> h.getId() == id).findFirst().get();
		Hospital dHospital = hospitalRepository.findById(id);
		hospitalRepository.delete(dHospital);
	}

}
