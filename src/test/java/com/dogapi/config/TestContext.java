package com.dogapi.config;

import com.dogapi.api.clients.DogApiClient;
import com.dogapi.api.responses.ListarTodosResponse;
import com.dogapi.api.responses.ImagemPorRacaResponse;
import com.dogapi.api.responses.ImagemAleatoriaResponse;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;

public class TestContext {

	public Scenario scenario;
	public DogApiClient dogApiClient;
	public Response response;
	public ListarTodosResponse listarTodosResponse;
	public ImagemPorRacaResponse imagemPorRacaResponse;
	public ImagemAleatoriaResponse imagemAleatoriaResponse;

	public TestContext() {
		this.dogApiClient = new DogApiClient();
	}

}
