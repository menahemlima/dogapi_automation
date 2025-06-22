package com.dogapi.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dogapi.config.TestContext;
import com.dogapi.utils.Utils;
import io.cucumber.java.pt.Entao;

public class SchemaStepsDefinitions {

	private static final Logger logger = LogManager.getLogger(DogApiStepDefinitions.class);

	private TestContext testContext;

	public SchemaStepsDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@Entao("o schema da resposta deve ser válido para {string}")
	public void oSchemaDaRespostaDeveSerValidoPara(String schemaFileName) {
		logger.info("Validando schema com o arquivo: {}", schemaFileName);
		Utils.validarSchema(testContext.response, schemaFileName);
		testContext.scenario.log("Schema validado com sucesso para: " + schemaFileName);
	}

}
