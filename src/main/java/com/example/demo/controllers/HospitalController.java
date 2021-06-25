package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Hospital;
import com.example.demo.services.HospitalService;

@RestController
@RequestMapping("/test")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getWelcome() throws Exception {
		return "<h1>Welcome to Hospital page</h1>";
	}

	@ResponseBody
	@GetMapping("/topstories")
	public Object getTopStories() throws Exception {
		Object result = hospitalService.getTopStories();
		return result;
	}
	
	@ResponseBody
	@GetMapping("/hospitals/{id}")
	public Hospital getHospital(@PathVariable final int id) throws Exception {
		return hospitalService.getHospital(id);
	}

	@ResponseBody
	@GetMapping(value = "/hospitals")
	public List<Hospital> getAllHospitals() throws Exception {
		return hospitalService.getAllHospitals();
	}

	@PostMapping(value = "/hospitals/load")
	public ResponseEntity<String> createHospital(@RequestBody final Hospital hospital) {
		try {
			hospitalService.addHospital(hospital);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Created", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/hospitals/{id}")
	public ResponseEntity<String> updateHospital(@PathVariable("id") int id, @RequestBody Hospital hospital) {
		try {
			hospitalService.updateHospital(id, hospital);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/hospitals/{id}")
	public ResponseEntity<String> deleteHospital(@PathVariable int id) {
		try {
			hospitalService.deleteHospital(id);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
