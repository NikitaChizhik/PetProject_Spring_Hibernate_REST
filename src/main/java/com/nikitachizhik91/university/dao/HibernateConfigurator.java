package com.nikitachizhik91.university.dao;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfigurator {

	@Bean
	public SessionFactory getSessionFactory() {

		return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();

	}

}
