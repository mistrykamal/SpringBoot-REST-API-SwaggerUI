package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Hospital;
import com.example.demo.services.HospitalService;

@RestController
@RequestMapping("/test/")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@ResponseBody 
	@RequestMapping(value="/hospitals/{id}", method = RequestMethod.GET)
	public Hospital getHospital(@PathVariable("id") int id) throws Exception {
		
		Hospital hospital = this.hospitalService.getHospital(id);
		return hospital;
	}
	
	@ResponseBody 
	@RequestMapping(value = "/hospitals", method = RequestMethod.GET)
	public List<Hospital> getAllHospitals() throws Exception {
		
			return this.hospitalService.getAllHospitals();
	}
	
}
