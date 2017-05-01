package com.nikitachizhik91.university.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.model.Room;

@Repository
public class RoomDaoImpl implements RoomDao {

	private final static Logger log = LogManager.getLogger(RoomDaoImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public Room create(Room roomArg) throws DaoException {

		log.trace("Started create() method.");

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(roomArg);
			session.getTransaction().commit();
		}

		log.info("Created a Room :" + roomArg);
		log.trace("Finished create() method.");

		return roomArg;

	}

	public Room findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Room room = null;
		try (Session session = sessionFactory.openSession()) {
			room = session.get(Room.class, id);
		}

		log.info("Found the Room :" + room);
		log.trace("Finished findById() method.");

		return room;
	}

	public List<Room> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Room> rooms = null;
		try (Session session = sessionFactory.openSession()) {
			rooms = (List<Room>) session.createQuery("from Room").list();
		}

		log.info("Found all rooms :");
		log.trace("Finished findAll() method.");
		return rooms;
	}

	public Room update(Room roomArg) throws DaoException {
		log.trace("Started update() method.");

		Room room = null;

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(room);
			session.getTransaction().commit();
		}

		log.info("Updated Room :" + roomArg);
		log.trace("Finished update() method.");
		return room;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Room.class, id));
			session.getTransaction().commit();
		}

		log.info("Deleted Room with id=" + id);
		log.trace("Finished delete() method.");
	}
}
