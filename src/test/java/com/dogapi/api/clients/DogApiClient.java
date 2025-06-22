package com.dogapi.api.clients;

import com.dogapi.config.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.dogapi.api.endpoints.DogApiEndpoints;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DogApiClient {

	private static final Logger logger = LogManager.getLogger(DogApiClient.class);

	public DogApiClient() {
		RestAssured.baseURI = ConfigurationManager.getProperty("dogapi.base.url");
		logger.info("Base URL: {}", RestAssured.baseURI);
	}

	public Response getListarTodos() {
		logger.info("Requisição GET para {}", RestAssured.baseURI + DogApiEndpoints.LISTAR_TODOS);
		return given()
				.when()
				.get(DogApiEndpoints.LISTAR_TODOS);
	}

	public Response getImagemPorRaca(String breed) {
		String path = DogApiEndpoints.IMAGEM_POR_RACA.replace("{breed}", breed.toLowerCase());
		logger.info("Requisição GET para {}", RestAssured.baseURI + path);
		return given().pathParam("breed", breed.toLowerCase())
				.when()
				.get(DogApiEndpoints.IMAGEM_POR_RACA);
	}

	public Response getImagemAleatoria() {
		logger.info("Requisição GET para {}", RestAssured.baseURI + DogApiEndpoints.IMAGEM_ALEATORIA);
		return given()
				.when()
				.get(DogApiEndpoints.IMAGEM_ALEATORIA);
	}
}