package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Group;

public class GroupDAOImpl {

	private static final String INSERT_GROUP = "insert into groups (name) values(?)";
	private static final String FIND_GROUP_BY_ID = "select * from groups where id=?";
	private static final String FIND_ALL_GROUPS = "select * from groups";
	private static final String UPDATE_GROUP = "update groups set name=? where id =?";
	private static final String DELETE_GROUP = "delete from groups where id =?";

	public Group create(Group group) {
		Group groupReceived = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, group.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					groupReceived = new Group();
					groupReceived.setId(resultSet.getInt("id"));
					groupReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return groupReceived;
	}

	public Group findById(int id) {
		Group groupReceived = new Group();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_GROUP_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					groupReceived.setId(resultSet.getInt("id"));
					groupReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupReceived;
	}

	public List<Group> findAll() {
		List<Group> groupsReceived = new ArrayList<Group>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_GROUPS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Group group = new Group();
				group.setId(resultSet.getInt("id"));
				group.setName(resultSet.getString("name"));
				groupsReceived.add(group);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupsReceived;
	}

	public Group update(Group group) {
		Group groupReceived = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, group.getName());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					groupReceived = new Group();
					groupReceived.setId(resultSet.getInt("id"));
					groupReceived.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return groupReceived;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
