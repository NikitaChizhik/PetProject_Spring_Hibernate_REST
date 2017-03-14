package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connector {
	private final static Logger log = LogManager.getLogger(Connector.class.getName());

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/University2";
	private static final String USER = "postgres";
	private static final String PASSWORD = "HolyBible";

	public Connection getConnection() throws DaoException {
		log.trace("Started getConnection() method.");
		try {

			Class.forName(DRIVER);
			log.trace("Found DRIVER=" + DRIVER);
		} catch (ClassNotFoundException e) {

			log.error("Cannot find DRIVER=" + DRIVER, e);
			throw new DaoException("Cannot find DRIVER=" + DRIVER, e);
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			log.error("Cannot get connection", e);
			throw new DaoException("Cannot get connection", e);
		}
		log.trace("Got connection.");
		log.trace("Finished getConnection().");

		return connection;
	}
}
