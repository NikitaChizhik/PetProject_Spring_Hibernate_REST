package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

public class GroupDaoImpl implements GroupDao {

	private static final String INSERT_GROUP = "insert into groups (name) values(?)";
	private static final String FIND_GROUP_BY_ID = "select * from groups where id=?";
	private static final String FIND_ALL_GROUPS = "select * from groups";
	private static final String UPDATE_GROUP = "update groups set name=? where id =?";
	private static final String DELETE_GROUP = "delete from groups where id =?";
	private static final String INSERT_STUDENT = "insert into groups_students (group_id,student_id) values (?,?)";

	public Group create(Group group) {
		Group groupReceived = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);) {

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
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUP_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int groupId = resultSet.getInt("id");
					groupReceived.setId(groupId);
					groupReceived.setName(resultSet.getString("name"));
					Groups_Students gsTable = new Groups_Students();
					groupReceived.setStudents(gsTable.findStudentsByGroupId(groupId));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupReceived;
	}

	public List<Group> findAll() {

		List<Group> groupsReceived = new ArrayList<Group>();
		Group group = null;
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_GROUPS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				group = new Group();
				int groupId = resultSet.getInt("id");
				group.setId(groupId);
				group.setName(resultSet.getString("name"));
				Groups_Students gsTable = new Groups_Students();
				group.setStudents(gsTable.findStudentsByGroupId(groupId));
				groupsReceived.add(group);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupsReceived;
	}

	public Group update(Group group) {

		Group groupReceived = new Group();
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_GROUP, Statement.RETURN_GENERATED_KEYS);) {

			Groups_Students groups_students = new Groups_Students();
			groups_students.deleteGroup(group.getId());

			Set<Student> students = group.getStudents();
			Iterator<Student> iterator = students.iterator();
			int groupId = group.getId();
			while (iterator.hasNext()) {
				Student student = iterator.next();
				addStudent(student.getId(), groupId);
			}

			statement.setString(1, group.getName());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					int groupIdReceived = resultSet.getInt("id");
					groupReceived.setId(groupIdReceived);
					groupReceived.setName(resultSet.getString("name"));
					Groups_Students gsTable = new Groups_Students();
					groupReceived.setStudents(gsTable.findStudentsByGroupId(groupIdReceived));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return groupReceived;
	}

	public void delete(int id) {
		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void addStudent(int studentId, int groupId) {

		Connector connector = new Connector();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);) {

			statement.setInt(1, groupId);
			statement.setInt(2, studentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
