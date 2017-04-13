package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connector {
	private final static Logger log = LogManager.getLogger(Connector.class.getName());

	private DataSource dataSource = null;

	public Connection getConnection() throws DaoException {
		log.trace("Started getConnection() method.");

		Connection connection = null;

		try {
			Context context = (Context) new InitialContext().lookup("java:comp/env");

			dataSource = (DataSource) context.lookup("jdbc/university2");

			connection = dataSource.getConnection();

		} catch (SQLException | NamingException e) {

			log.error("Cannot get connection", e);
			throw new DaoException("Cannot get connection", e);

		}

		log.trace("Finished getConnection().");

		return connection;
	}
}
