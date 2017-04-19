package com.nikitachizhik91.university.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.RoomManager;
import com.nikitachizhik91.university.model.Room;

@Service
public class RoomManagerImpl implements RoomManager {

	private final static Logger log = LogManager.getLogger(RoomManagerImpl.class.getName());
	@Autowired
	private RoomDao roomDao;

	@Override
	public Room create(Room room) throws DomainException {
		log.trace("Started create() method.");

		Room roomTemp = null;
		try {
			log.trace("Creating room.");

			roomTemp = roomDao.create(room);

		} catch (DaoException e) {
			log.error("Cannot create room=" + room, e);
			throw new DomainException("Cannot create room=" + room, e);
		}
		log.trace("Finished create() method.");

		return roomTemp;
	}

	@Override
	public Room findById(int id) throws DomainException {
		log.trace("Started findById() method.");

		Room room = null;
		try {
			log.trace("Finding room by id.");

			room = roomDao.findById(id);

		} catch (DaoException e) {
			log.error("Cannot find room by id=" + id, e);
			throw new DomainException("Cannot find room by id=" + id, e);
		}
		log.trace("Finished findById() method.");

		return room;
	}

	@Override
	public List<Room> findAll() throws DomainException {
		log.trace("Started findAll() method.");

		List<Room> rooms = null;
		try {
			log.trace("Finding all rooms");

			rooms = roomDao.findAll();

		} catch (DaoException e) {
			log.error("Cannot find all rooms.", e);
			throw new DomainException("Cannot find all rooms.", e);
		}
		log.trace("Finished findAll() method.");

		return rooms;
	}

	@Override
	public Room update(Room room) throws DomainException {
		log.trace("Started update() method.");

		Room roomTemp = null;
		try {
			log.trace("Updating room.");

			roomTemp = roomDao.update(room);

		} catch (DaoException e) {
			log.error("Cannot update room=" + room, e);
			throw new DomainException("Cannot update room=" + room, e);
		}
		log.trace("Finished update() method.");

		return roomTemp;
	}

	@Override
	public void delete(int id) throws DomainException {
		log.trace("Started delete() method.");

		try {
			log.trace("Deleting room by id.");

			roomDao.delete(id);

		} catch (DaoException e) {
			log.error("Cannot delete room by id=" + id, e);
			throw new DomainException("Cannot delete room by id=" + id, e);
		}
		log.trace("Finished delete() method.");
	}
}
