package com.example.demo.graphqlservice;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.demo.graphqlservice.datafetcher.AllHospitalsDataFetcher;
import com.example.demo.graphqlservice.datafetcher.HospitalDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Autowired
	private AllHospitalsDataFetcher allHospitalsDataFetcher;
	@Autowired
	private HospitalDataFetcher hospitalDataFetcher;
	@Value("classpath:schema.graphql")
	Resource resource;
	private GraphQL graphQL;

	@PostConstruct
	private void loadSchema() throws Exception {
		// get the schema
		File schemaFile = resource.getFile();
		// parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allHospitals", allHospitalsDataFetcher).dataFetcher("hospital", hospitalDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
