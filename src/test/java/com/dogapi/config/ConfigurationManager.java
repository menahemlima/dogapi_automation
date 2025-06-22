package com.dogapi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {

	private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
	private static Properties properties;

	static {
		properties = new Properties();
		try (InputStream input = ConfigurationManager.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			if (input == null) {
				logger.error("Não foi possível encontrar application.properties");
				System.exit(1);
			}
			properties.load(input);
			logger.info("application.properties loaded successfully.");
		} catch (IOException ex) {
			logger.error("Erro ao carregar application.properties: " + ex.getMessage());
			System.exit(1);
		}
	}

	public static String getProperty(String key) {
		String value = System.getProperty(key);
		if (value != null) {
			return value;
		}

		return properties.getProperty(key);
	}
}