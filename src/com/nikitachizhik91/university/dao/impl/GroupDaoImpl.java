package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

public class GroupDaoImpl implements GroupDao {

	private Connector connector = new Connector();
	private static final String INSERT_GROUP = "insert into groups (name) values(?)";
	private static final String FIND_GROUP_BY_ID = "select * from groups where id=?";
	private static final String FIND_ALL_GROUPS = "select * from groups";
	private static final String UPDATE_GROUP = "update groups set name=? where id =?";
	private static final String DELETE_GROUP = "delete from groups where id =?";
	private static final String INSERT_STUDENT = "insert into groups_students (group_id,student_id) values (?,?)";
	private static final String FIND_STUDENTS_BY_GROUP_ID = "select student_id from groups_students where group_id=?";
	private static final String DELETE_ALL_STUDENTS_FROM_GROUP = "delete from groups_students where group_id=?";

	public Group create(Group group) {
		Group groupReceived = null;

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

		Group groupReceived = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUP_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					groupReceived = new Group();
					int groupId = resultSet.getInt("id");
					groupReceived.setId(groupId);
					groupReceived.setName(resultSet.getString("name"));
					groupReceived.setStudents(findStudentsByGroupId(groupId));
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
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_GROUPS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				group = new Group();
				int groupId = resultSet.getInt("id");
				group.setId(groupId);
				group.setName(resultSet.getString("name"));
				group.setStudents(findStudentsByGroupId(groupId));
				groupsReceived.add(group);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupsReceived;
	}

	public Group update(Group group) {

		Group groupReceived = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(UPDATE_GROUP, Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, group.getName());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					groupReceived = new Group();
					int groupIdReceived = resultSet.getInt("id");
					groupReceived.setId(groupIdReceived);
					groupReceived.setName(resultSet.getString("name"));
					groupReceived.setStudents(findStudentsByGroupId(groupIdReceived));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return groupReceived;
	}

	public void delete(int id) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void addStudent(int studentId, int groupId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);) {

			statement.setInt(1, groupId);
			statement.setInt(2, studentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Set<Student> findStudentsByGroupId(int id) {

		Set<Student> students = new HashSet<Student>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_GROUP_ID)) {

			statement.setInt(1, id);
			StudentDao studentDao = new StudentDaoImpl();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					studentDao.findById(resultSet.getInt("student_id"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public void deleteAllStudentsFromGroup(int id) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_STUDENTS_FROM_GROUP);) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
