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
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.dao.StudentDao;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Student;

@Repository
public class GroupDaoImpl implements GroupDao {

	private final static Logger log = LogManager.getLogger(GroupDaoImpl.class.getName());

	@Autowired
	private DataSource dataSource;
	private static final String INSERT_GROUP = "insert into groups (name) values(?)";
	private static final String FIND_GROUP_BY_ID = "select * from groups where id=?";
	private static final String FIND_ALL_GROUPS = "select * from groups";
	private static final String UPDATE_GROUP = "update groups set name=? where id =?";
	private static final String DELETE_GROUP = "delete from groups where id =?";

	private static final String INSERT_STUDENT = "insert into groups_students (group_id,student_id) values (?,?)";
	private static final String FIND_STUDENTS_BY_GROUP_ID = "select student_id from groups_students where group_id=?";
	private static final String DELETE_ALL_STUDENTS_FROM_GROUP = "delete from groups_students where group_id=?";
	private static final String DELETE_STUDENT_FROM_GROUP = "delete from groups_students where student_id=?";

	private static final String FIND_GROUPS_WITHOUT_FACULTY = "SELECT id FROM groups g WHERE NOT EXISTS(SELECT NULL FROM faculties_groups fg WHERE fg.group_id = g.id)";

	@Autowired
	private StudentDao studentDao;

	public Group create(Group groupArg) throws DaoException {
		log.trace("Started create() method.");
		Group group = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_GROUP,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, groupArg.getName());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					group = new Group();
					group.setId(resultSet.getInt("id"));
					group.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Group :" + groupArg, e);
			throw new DaoException("Cannot create Group :", e);

		}
		log.info("Created a Group :" + groupArg);
		log.trace("Finished create() method.");
		return group;
	}

	public Group findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Group group = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUP_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					group = new Group();
					int groupId = resultSet.getInt("id");
					group.setId(groupId);
					group.setName(resultSet.getString("name"));
					group.setStudents(findStudentsByGroupId(groupId));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Group with id=" + id, e);
			throw new DaoException("Cannot find Group with id=" + id, e);
		}
		log.info("Found the Group :" + group);
		log.trace("Finished findById() method.");
		return group;
	}

	public List<Group> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Group> groups = new ArrayList<Group>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_GROUPS);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Group group = new Group();
				int groupId = resultSet.getInt("id");
				group.setId(groupId);
				group.setName(resultSet.getString("name"));
				group.setStudents(findStudentsByGroupId(groupId));
				groups.add(group);
			}
		} catch (SQLException e) {
			log.error("Cannot find all groups.", e);
			throw new DaoException("Cannot find all groups.", e);
		}
		log.info("Found all groups :");
		log.trace("Finished findAll() method.");
		return groups;
	}

	public Group update(Group groupArg) throws DaoException {

		log.trace("Started update() method.");

		Group group = null;

		log.trace("Getting Conncetion and creating prepared statement.");

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, groupArg.getName());
			statement.setInt(2, groupArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					group = new Group();
					int groupIdReceived = resultSet.getInt("id");
					group.setId(groupIdReceived);
					group.setName(resultSet.getString("name"));
					group.setStudents(findStudentsByGroupId(groupIdReceived));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Group :" + groupArg, e);
			throw new DaoException("Cannot update Group :" + groupArg, e);
		}
		log.info("Updated Group :" + groupArg);
		log.trace("Finished update() method.");
		return group;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Group with id=" + id, e);
			throw new DaoException("Cannot delete Group with id=" + id, e);
		}
		log.info("Deleted Group with id=" + id);
		log.trace("Finished delete() method.");
	}

	public void addStudent(int studentId, int groupId) throws DaoException {
		log.trace("Started addStudent() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);) {

			statement.setInt(1, groupId);
			statement.setInt(2, studentId);
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot add Student with id=" + studentId, e);
			throw new DaoException("Cannot add Student with id=" + studentId, e);
		}
		log.info("Added Student with id=" + studentId + " to the group with id=" + groupId);
		log.trace("Finished addStudent() method.");
	}

	public List<Student> findStudentsByGroupId(int groupId) throws DaoException {

		log.trace("Started findStudentsByGroupId() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		List<Student> students = new ArrayList<Student>();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_STUDENTS_BY_GROUP_ID)) {

			statement.setInt(1, groupId);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");

			try (ResultSet resultSet = statement.executeQuery();) {

				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				while (resultSet.next()) {

					students.add(studentDao.findById(resultSet.getInt("student_id")));
				}
			}

		} catch (SQLException e) {

			log.error("Cannot find Students by Group id=" + groupId, e);
			throw new DaoException("Cannot find Students by Group id=" + groupId, e);
		}

		log.info("Found " + students.size() + " Students by Group id=" + groupId);
		log.trace("Finished findStudentsByGroupId() method.");

		return students;
	}

	public void deleteAllStudentsFromGroup(int groupId) throws DaoException {
		log.trace("Started deleteAllStudentsFromGroup() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_STUDENTS_FROM_GROUP);) {

			statement.setInt(1, groupId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete all students from Group with id=" + groupId, e);
			throw new DaoException("Cannot delete all students from Group with id=" + groupId, e);
		}
		log.info("Deleted all students from Group with id=" + groupId);
		log.trace("Finished deleteAllStudentsFromGroup() method.");
	}

	public void deleteStudentFromGroup(int studentId) throws DaoException {
		log.trace("Started deleteStudentFromGroup() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_FROM_GROUP);) {

			statement.setInt(1, studentId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Student with id=" + studentId, e);
			throw new DaoException("Cannot delete Student with id=" + studentId, e);
		}
		log.info("Deleted Student with id=" + studentId);
		log.trace("Finished deleteStudentFromGroup() method.");

	}

	public List<Group> findGroupsWithoutFaculty() throws DaoException {
		log.trace("Started findGroupsWithoutFaculty() method.");

		List<Group> groups = new ArrayList<Group>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUPS_WITHOUT_FACULTY);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {

				Group group = findById(resultSet.getInt("id"));
				groups.add(group);
			}

		} catch (SQLException e) {
			log.error("Cannot find all groups which are without faculty.", e);
			throw new DaoException("Cannot find all groups which are without faculty.", e);
		}
		log.info("Found all groups which are without faculty.");
		log.trace("Finished findGroupsWithoutFaculty() method.");

		return groups;
	}
}
