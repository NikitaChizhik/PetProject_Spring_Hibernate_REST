package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.model.Room;

public class RoomDAOImpl implements RoomDAO {
	private static final String INSERT_ROOM = "insert into rooms (number) values(?)";
	private static final String FIND_ROOM_BY_ID = "select * from rooms where id=?";
	private static final String FIND_ALL_ROOMS = "select * from rooms";
	private static final String UPDATE_ROOM = "update rooms set number=? where id =?";
	private static final String DELETE_ROOM = "delete from rooms where id =?";

	public Room create(Room room) {
		Room roomReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, room.getNumber());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					roomReceived = new Room();
					roomReceived.setId(resultSet.getInt("id"));
					roomReceived.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return roomReceived;
	}

	public Room findById(int id) {
		Room roomReceived = new Room();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_ROOM_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					roomReceived.setId(resultSet.getInt("id"));
					roomReceived.setNumber(resultSet.getString("number"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomReceived;
	}

	public List<Room> findAll() {
		List<Room> roomsReceived = new ArrayList<Room>();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_ROOMS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setNumber(resultSet.getString("number"));
				roomsReceived.add(room);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return roomsReceived;
	}

	public Room update(Room room) {
		Room roomReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, room.getNumber());
			statement.setInt(2, room.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					roomReceived = new Room();
					roomReceived.setId(resultSet.getInt("id"));
					roomReceived.setNumber(resultSet.getString("number"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return roomReceived;
	}

	public void delete(int id) {
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ROOM);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
