package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class Connector implements ServletContextListener {

	private final static Logger log = LogManager.getLogger(Connector.class.getName());

	private static DataSource dataSource;

	public Connector() {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

		dataSource = context.getBean("dataSource", DataSource.class);

		if (context != null) {
			((ClassPathXmlApplicationContext) context).close();
		}

	}

	public static Connection getConnection() throws DaoException {

		log.trace("Started getConnection() method.");

		Connection connection;

		try {

			connection = dataSource.getConnection();

		} catch (SQLException e) {

			log.error("Cannot get connection", e);
			throw new DaoException("Cannot get connection", e);
		}

		log.trace("Finished getConnection().");

		return connection;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
