package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1")
@Tag(name = "REST services")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@Operation(summary = "show welcome page")
	public String getWelcome() throws Exception {
		return "<h1>Welcome to Hospital page</h1>";
	}

	@ResponseBody
	@GetMapping("/get-hospital/{id}")
	@Operation(summary = "get hospital by id",
			parameters = {@Parameter(name="hospitalId", description="the path variable is integer", example="1")},
			responses = {@ApiResponse(responseCode = "200", description="Success Response")})
	public Hospital getHospital(@PathVariable("hospitalId") final int id) throws Exception {
		return hospitalService.getHospital(id);
	}

	@ResponseBody
	@GetMapping(value = "/all-hospitals")
	@Operation(summary = "get all the saved hospitals",
			responses = {@ApiResponse(responseCode = "200", description="Success Response", content = @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
	public List<Hospital> getAllHospitals() throws Exception {
		return hospitalService.getAllHospitals();
	}

	@PostMapping(value = "/load-hospital")
	@Operation(summary = "create and save a hospital", 
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "this is the request body description"),
			responses = {@ApiResponse(responseCode = "200", description="Success Response")})
	public ResponseEntity<String> createHospital(@RequestBody final Hospital hospital) {
		try {
			hospitalService.addHospital(hospital);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Created", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit-hospital/{id}")
	@Operation(summary = "update the hospital", description = "modify the hospital with respect to the provided hospital-id",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "this is the request body description"),
			parameters = {@Parameter(name="hospitalId", description="the path variable is integer", example="1")},
			responses = {@ApiResponse(responseCode = "201", description="Success Response")})
	public ResponseEntity<String> updateHospital(@PathVariable("hospitalId") int id, @RequestBody Hospital hospital) {
		try {
			hospitalService.updateHospital(id, hospital);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/remove-hospital/{id}")
	@Operation(summary = "delete the hospital", description = "delete the hospital with respect to the provided hospital-id",
			parameters = {@Parameter(name="hospitalId", description="the path variable is integer", example="1")})
	public ResponseEntity<String> deleteHospital(@PathVariable("hospitalId") int id) {
		try {
			hospitalService.deleteHospital(id);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Hospital Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
