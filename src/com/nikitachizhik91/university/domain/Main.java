package com.nikitachizhik91.university.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private final static Logger LOGGER = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {

		LOGGER.trace("trace");
		LOGGER.debug("Debug");
		LOGGER.info("Info Message Logged !!!");

		LOGGER.warn("Warn");
		LOGGER.error("Error ", new NullPointerException("NullError"));
		LOGGER.fatal("Fatal");
	}
}
