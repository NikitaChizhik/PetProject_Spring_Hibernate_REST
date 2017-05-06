package com.nikitachizhik91.university.service;

import java.util.List;

import com.nikitachizhik91.university.model.Room;

public interface RoomService {

	Room create(Room room) throws DomainException;

	Room findById(int id) throws DomainException;

	List<Room> findAll() throws DomainException;

	Room update(Room room) throws DomainException;

	void delete(int id) throws DomainException;
}
