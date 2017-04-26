package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.model.Room;

@Repository
public class RoomDaoImpl implements RoomDao {

	private final static Logger log = LogManager.getLogger(RoomDaoImpl.class.getName());
	
	@Autowired
	private DataSource dataSource;
	private static final String INSERT_ROOM = "insert into rooms (number) values(?)";
	private static final String FIND_ROOM_BY_ID = "select * from rooms where id=?";
	private static final String FIND_ALL_ROOMS = "select * from rooms";
	private static final String UPDATE_ROOM = "update rooms set number=? where id =?";
	private static final String DELETE_ROOM = "delete from rooms where id =?";

	public Room create(Room roomArg) throws DaoException {
		log.trace("Started create() method.");
		Room room = null;

		log.trace("Getting Conncetion and creating prepared statement.");

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ROOM,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, roomArg.getNumber());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Room :" + roomArg, e);
			throw new DaoException("Cannot create Room :", e);

		}
		log.info("Created a Room :" + roomArg);
		log.trace("Finished create() method.");
		return room;
	}

	public Room findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Room room = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ROOM_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Room with id=" + id, e);
			throw new DaoException("Cannot find Room with id=" + id, e);
		}
		log.info("Found the Room :" + room);
		log.trace("Finished findById() method.");

		return room;
	}

	public List<Room> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Room> rooms = new ArrayList<Room>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_ROOMS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getString("number"));
				rooms.add(room);
			}
		} catch (SQLException e) {
			log.error("Cannot find all rooms.", e);
			throw new DaoException("Cannot find all rooms.", e);
		}
		log.info("Found all rooms :");
		log.trace("Finished findAll() method.");
		return rooms;
	}

	public Room update(Room roomArg) throws DaoException {
		log.trace("Started update() method.");

		Room room = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, roomArg.getNumber());
			statement.setInt(2, roomArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Room :" + roomArg, e);
			throw new DaoException("Cannot update Room :" + roomArg, e);
		}
		log.info("Updated Room :" + roomArg);
		log.trace("Finished update() method.");
		return room;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ROOM);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Room with id=" + id, e);
			throw new DaoException("Cannot delete Room with id=" + id, e);
		}
		log.info("Deleted Room with id=" + id);
		log.trace("Finished delete() method.");
	}

}
