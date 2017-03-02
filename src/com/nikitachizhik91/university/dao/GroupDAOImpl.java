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

	private static final String create = "insert into groups (name) values(?)";
	private static final String findById = "select * from groups where group_id=?";
	private static final String findAll = "select * from groups order by name";
	private static final String update = "update groups set name=? where group_id =?";
	private static final String delete = "delete from groups where group_id =?";

	public Group create(Group group) {
		Group newGroup = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, group.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newGroup = new Group();
					newGroup.setId(resultSet.getInt("group_id"));
					newGroup.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newGroup;
	}

	public Group findById(int id) {
		Group groupRecieved = new Group();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(findById)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					groupRecieved.setId(resultSet.getInt("group_id"));
					groupRecieved.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupRecieved;
	}

	public List<Group> findAll() {
		List<Group> groupRecieved = new ArrayList<Group>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(findAll);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Group group = new Group();
				group.setId(resultSet.getInt("group_id"));
				group.setName(resultSet.getString("name"));
				groupRecieved.add(group);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupRecieved;
	}

	public Group update(Group group) {
		Group newStudent = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, group.getName());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					newStudent = new Group();
					newStudent.setId(resultSet.getInt("group_id"));
					newStudent.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return newStudent;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(delete);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
