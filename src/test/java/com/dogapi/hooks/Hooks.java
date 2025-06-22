package com.dogapi.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dogapi.config.TestContext;

public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class);
	private TestContext testContext;

	public Hooks(TestContext testContext) {
		this.testContext = testContext;
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		this.testContext.scenario = scenario;
		logger.info("===================================================================");
		logger.info("Iniciando cenário: " + scenario.getName());
		logger.info("Tags: " + scenario.getSourceTagNames());
		logger.info("===================================================================");
	}

	@After
	public void afterScenario(Scenario scenario) {
		logger.info("===================================================================");
		logger.info("Cenário " + scenario.getName() + " " + scenario.getStatus().name());
		logger.info("Status: " + scenario.getStatus().name());
		logger.info("===================================================================");
		this.testContext.scenario = null;
	}
}