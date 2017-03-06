package com.nikitachizhik91.university.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/University2";
	private static final String USER = "postgres";
	private static final String PASSWORD = "HolyBible";

	public Connection getConnection() {

		try {

			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
