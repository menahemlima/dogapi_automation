package com.dogapi.utils;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Utils {

	private static final Logger logger = LogManager.getLogger(Utils.class);

	public static boolean existeRaca(Map<String, List<String>> todasRacasMap, String nomeRaca) {
		if (todasRacasMap == null || nomeRaca == null || nomeRaca.trim().isEmpty()) {
			logger.warn("Mapa de raças ou nome da raça é nulo/vazio.");
			return false;
		}
		boolean exists = todasRacasMap.keySet().stream().anyMatch(b -> b.equalsIgnoreCase(nomeRaca.trim()));
		logger.debug("Verificando se a raça '{}' existe. Resultado: {}", nomeRaca, exists);
		return exists;
	}

	public static boolean existeSubRaca(Map<String, List<String>> todasRacasMap, String racaPrincipal, String subRaca) {
		if (racaPrincipal == null || racaPrincipal == null || racaPrincipal.trim().isEmpty() || subRaca == null
				|| subRaca.trim().isEmpty()) {
			logger.warn("Um dos parâmetros para verificar sub-raça é nulo/vazio.");
			return false;
		}

		List<String> subRacas = todasRacasMap.get(racaPrincipal.toLowerCase());
		if (subRacas == null || subRacas.isEmpty()) {
			logger.debug("Não há sub-raças para a raça principal '{}'.", racaPrincipal);
			return false;
		}

		boolean existe = subRacas.stream().anyMatch(sb -> sb.equalsIgnoreCase(subRaca.trim()));
		logger.debug("Verificando se a sub-raça '{}' existe para '{}'. Resultado: {}", subRaca, racaPrincipal, existe);
		return existe;
	}

	public static String extraiUrlRaca(String imagemUrl) {
		if (imagemUrl == null || imagemUrl.trim().isEmpty()) {
			return null;
		}
		try {
			String path = imagemUrl.substring(imagemUrl.indexOf("/breeds/") + "/breeds/".length());
			String racaPath = path.substring(0, path.indexOf("/"));
			return racaPath;
		} catch (StringIndexOutOfBoundsException e) {
			logger.warn("Não foi possível extrair a raça da URL da imagem: {}", imagemUrl);
			return null;
		}
	}

	public static void validarSchema(Response response, String nomeArquivoSchema) {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + nomeArquivoSchema));
	}
}