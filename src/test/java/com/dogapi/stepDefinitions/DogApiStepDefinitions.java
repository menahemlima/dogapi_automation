package com.dogapi.stepDefinitions;

import com.dogapi.api.responses.ListarTodosResponse;
import com.dogapi.api.responses.ImagemPorRacaResponse;
import com.dogapi.api.responses.ErrorResponse;
import com.dogapi.api.responses.ImagemAleatoriaResponse;
import com.dogapi.config.TestContext;
import com.dogapi.utils.Utils;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

public class DogApiStepDefinitions {

	private static final Logger logger = LogManager.getLogger(DogApiStepDefinitions.class);

	private TestContext testContext;

	public DogApiStepDefinitions(TestContext testContext) {
		this.testContext = testContext;
	}

	@Quando("eu enviar uma requisição para listar todas as raças")
	public void euEnviarUmaRequisicaoParaListarTodasAsRacas() {
		logger.info("Executando: enviar requisição para listar todas as raças");
		testContext.response = testContext.dogApiClient.getListarTodos();
		testContext.scenario.log("Requisição para listar todas as raças enviada.");
		testContext.listarTodosResponse = testContext.response.as(ListarTodosResponse.class);
	}

	@Entao("a lista de raças não deve estar vazia")
	public void aListaDeRacasNaoDeveEstarVazia() {
		logger.info("Verificando se a lista de raças não está vazia.");
		assertNotNull(testContext.listarTodosResponse.getMessage());
		assertFalse(testContext.listarTodosResponse.getMessage().isEmpty());
		testContext.scenario
				.log("Lista de raças contém " + testContext.listarTodosResponse.getMessage().size() + " entradas.");
	}

	@Entao("na lista deve conter a raça {string}")
	public void naListaDeveConterARaca(String nomeRaca) {
		logger.info("Verificando se a raça '{}' existe na lista.", nomeRaca);
		assertTrue(Utils.existeRaca(testContext.listarTodosResponse.getMessage(), nomeRaca),
				"A raça '" + nomeRaca + "' deve existir na lista");
		testContext.scenario.log("Raça '" + nomeRaca + "' existe na lista.");
	}

	@Entao("a raça {string} deve conter a sub-raça {string}")
	public void aRacaDeveConterASubRaca(String racaPrincipal, String subRaca) {
		logger.info("Verificando se a raça '{}' contém a sub-raça '{}'.", racaPrincipal, subRaca);
		assertTrue(Utils.existeSubRaca(testContext.listarTodosResponse.getMessage(), racaPrincipal, subRaca),
				"A raça '" + racaPrincipal + "' deveria conter a sub-raça '" + subRaca + "'");
		testContext.scenario.log("Sub-raça '" + subRaca + "' verificada para a raça '" + racaPrincipal + "'.");
	}

	@Quando("eu enviar uma requisição para obter imagens da raça {string}")
	public void euEnviarUmaRequisicaoParaObterImagensDaRaca(String raca) {
		logger.info("Executando: enviar requisição para obter imagens da raça '{}'", raca);
		testContext.response = testContext.dogApiClient.getImagemPorRaca(raca);
		testContext.scenario.log("Requisição GET para imagens da raça '" + raca + "' enviada.");

	}

	@Entao("a resposta deve conter a Url da imagem para a raça {string}")
	public void aRespostaDeveConterAURLDaImagemParaARaca(String raca) {
		testContext.imagemPorRacaResponse = testContext.response.as(ImagemPorRacaResponse.class);
		logger.info("Verificando se a resposta contém URLs de imagens para a raça '{}'.", raca);
		Pattern validaUrl = Pattern.compile("^https?://.*\\.(jpg|jpeg|png|gif)$");

		assertNotNull(testContext.imagemPorRacaResponse.getMessage(), "A lista de imagens não deve ser nula.");
		assertFalse(testContext.imagemPorRacaResponse.getMessage().isEmpty(),
				"A lista de imagens não deve estar vazia.");

		testContext.scenario
				.log("Número de imagens retornadas: " + testContext.imagemPorRacaResponse.getMessage().size());

		for (String imageUrl : testContext.imagemPorRacaResponse.getMessage()) {
			assertNotNull(imageUrl, "A URL da imagem não deve ser nula.");
			assertFalse(imageUrl.isEmpty(), "A URL da imagem não deve estar vazia.");
			assertTrue(imageUrl.toLowerCase().contains("/breeds/" + raca.toLowerCase() + "/"),
					"A URL da imagem deve conter a raça '" + raca + "'. URL: " + imageUrl);
			assertTrue(validaUrl.matcher(imageUrl).matches(),
					"A URL da imagem deve estar em um formato válido. URL: " + imageUrl);
		}

		testContext.scenario.log("URLs de imagens verificadas para a raça '" + raca + "'.");
	}

	@Quando("eu enviar uma requisição para buscar uma imagem aleatória")
	public void euEnviarUmaRequisicaoParaBuscarUmaImagemAleatoria() {
		logger.info("Executando: enviar requisição para uma imagem aleatória");
		testContext.response = testContext.dogApiClient.getImagemAleatoria();
		testContext.scenario.log("Requisição para imagem aleatória enviada.");
		testContext.imagemAleatoriaResponse = testContext.response.as(ImagemAleatoriaResponse.class);
	}

	@Entao("a URL da imagem do cachorro deve ser válida")
	public void aUrlDaImagemDoCachorroDeveSerValida() {
		Pattern validaUrlCompleta = Pattern.compile(
				"^https://images\\.dog\\.ceo/breeds/[a-zA-Z0-9\\-]+/[a-zA-Z0-9\\-_\\.]+\\.(jpg|jpeg|png|gif)$");
		String imagemUrl = testContext.imagemAleatoriaResponse.getMessage();
		logger.info("Verificando se a resposta contém uma URL de imagem aleatória. URL: {}", imagemUrl);

		assertNotNull(imagemUrl, "A URL da imagem aleatória não deve ser nula.");
		assertFalse(imagemUrl.isEmpty(), "A URL da imagem aleatória não deve ser vazia.");

		assertTrue(validaUrlCompleta.matcher(imagemUrl).matches(),
				"A URL da imagem aleatória deve ser um formato de URL válido: " + imagemUrl);
	}

	@Entao("essa imagem deve corresponder a uma raça existente")
	public void essaImagemDeveCorresponderAUmaRacaExistente() {
		Response getResponse = testContext.dogApiClient.getListarTodos();
		ListarTodosResponse listaResponses = getResponse.as(ListarTodosResponse.class);
		logger.info("Verificando se a raça da imagem aleatória existe na lista geral.");
		String extraiNomeRaca = Utils.extraiUrlRaca(testContext.imagemAleatoriaResponse.getMessage());

		assertNotNull(extraiNomeRaca, "Não foi possível extrair a raça da URL da imagem: "
				+ testContext.imagemAleatoriaResponse.getMessage());
		assertFalse(extraiNomeRaca.isEmpty(), "Não foi possível extrair a raça da URL da imagem: "
				+ testContext.imagemAleatoriaResponse.getMessage());

		assertTrue(
				Utils.existeRaca(listaResponses.getMessage(), extraiNomeRaca) || Utils.existeSubRaca(
						listaResponses.getMessage(), extraiNomeRaca.split("-")[0], extraiNomeRaca.split("-")[1]),
				"A raça do cachorro extraída da URL '" + extraiNomeRaca + "' não existe na lista de raças válidas.");

		testContext.scenario.log("Raça da imagem aleatória verificada: " + extraiNomeRaca);
	}

	@Entao("não deve conter a raça {string}")
	public void naoDeveConterARaca(String nomeRaca) {
		logger.info("Verificando se a raça '{}' existe na lista.", nomeRaca);
		assertFalse(Utils.existeRaca(testContext.listarTodosResponse.getMessage(), nomeRaca),
				"A raça '" + nomeRaca + "' deve existir na lista");
		testContext.scenario.log("Raça '" + nomeRaca + "' existe na lista.");
	}

	@Entao("a resposta deve conter a mensagem de erro {string}")
	public void aRespostaDeveConterAMensagemDeErro(String mensagemEsperada) {
		logger.info("Verificando mensagem de erro. Esperado: {}, Recebido: {}", mensagemEsperada,
				testContext.response.jsonPath().getString("message"));

		ErrorResponse errorResponse = testContext.response.as(ErrorResponse.class);
		assertEquals(mensagemEsperada, errorResponse.getMessage(), "A mensagem de erro não corresponde ao esperado.");
		testContext.scenario.log("Mensagem de erro verificada: " + errorResponse.getMessage());
	}
}