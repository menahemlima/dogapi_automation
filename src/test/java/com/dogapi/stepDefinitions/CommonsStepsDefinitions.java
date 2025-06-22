package com.dogapi.stepDefinitions;

import com.dogapi.config.TestContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonsStepsDefinitions {

	private static final Logger logger = LogManager.getLogger(DogApiStepDefinitions.class);

	private TestContext testContext;

	public CommonsStepsDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@Dado("que a api DogAPI está disponível")
	public void queAApiDogAPIEstaDisponivel() {
		logger.info("Verificando disponibilidade do endpoint da Dog API!");
	}

	@Entao("o response deve retornar o status code {int}")
	public void oResponseDeveRetornarOStatusCode(int statusCodeEsperado) {
		logger.info("Verificando status code. Esperado: {}, Recebido: {}", statusCodeEsperado,
				testContext.response.getStatusCode());
		assertEquals(statusCodeEsperado, testContext.response.getStatusCode());
		testContext.scenario.log("Status code verificado: " + testContext.response.getStatusCode());
	}

	@Entao("o campo 'status' deve ser o valor {string}")
	public void oCampoDeveSerOValor(String statusEsperado) {
		logger.info("Verificando campo 'status'. Esperado: {}, Recebido: {}", statusEsperado,
				testContext.response.jsonPath().getString("status"));
		assertEquals(statusEsperado, testContext.response.jsonPath().getString("status"));
		testContext.scenario.log("Campo 'status' verificado: " + testContext.response.jsonPath().getString("status"));
	}

}
