package com.example.demo.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.graphqlservice.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class GraphQLController {
	
	@Autowired
	private GraphQLService graphqlService;
	
	@PostMapping("/graphql/hospitals")
	public ResponseEntity<Object> getAllHospitals(@RequestBody final String query) {
		ExecutionResult execute = graphqlService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
}
