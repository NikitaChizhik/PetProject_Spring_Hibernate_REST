package com.nikitachizhik91.university.domain;

import java.util.List;

import com.nikitachizhik91.university.model.Room;

public interface RoomManager {

	public abstract Room create(Room room) throws DomainException;

	public abstract Room findById(int id) throws DomainException;

	public abstract List<Room> findAll() throws DomainException;

	public abstract Room update(Room room) throws DomainException;

	public abstract void delete(int id) throws DomainException;
}
