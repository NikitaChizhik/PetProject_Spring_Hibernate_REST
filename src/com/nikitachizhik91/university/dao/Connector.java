package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	public static Connection getConnection() {

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2", "postgres",
					"HolyBible");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
