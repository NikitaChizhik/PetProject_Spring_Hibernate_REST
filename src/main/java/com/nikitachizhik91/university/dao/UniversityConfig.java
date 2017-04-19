package com.nikitachizhik91.university.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nikitachizhik91.university.model.Room;

@Configuration
public class UniversityConfig {

	@Bean
	public Room room() {
		return new Room();
	}

}
