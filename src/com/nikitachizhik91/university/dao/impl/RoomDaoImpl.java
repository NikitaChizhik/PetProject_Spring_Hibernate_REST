package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.RoomDao;
import com.nikitachizhik91.university.model.Room;

public class RoomDaoImpl implements RoomDao {
	private Connector connector;
	private static final String INSERT_ROOM = "insert into rooms (number) values(?)";
	private static final String FIND_ROOM_BY_ID = "select * from rooms where id=?";
	private static final String FIND_ALL_ROOMS = "select * from rooms";
	private static final String UPDATE_ROOM = "update rooms set number=? where id =?";
	private static final String DELETE_ROOM = "delete from rooms where id =?";

	public RoomDaoImpl() {
		connector = new Connector();
	}

	public Room create(Room roomArg) {
		Room room = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, roomArg.getNumber());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return room;
	}

	public Room findById(int id) {
		Room room = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ROOM_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return room;
	}

	public List<Room> findAll() {
		List<Room> rooms = new ArrayList<Room>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_ROOMS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getString("number"));
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return rooms;
	}

	public Room update(Room roomArg) {
		Room room = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, roomArg.getNumber());
			statement.setInt(2, roomArg.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					room = new Room();
					room.setId(resultSet.getInt("id"));
					room.setNumber(resultSet.getString("number"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return room;
	}

	public void delete(int id) {
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ROOM);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
