package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.FacultyDao;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

public class FacultyDaoImpl implements FacultyDao {
	private final static Logger log = LogManager.getLogger(FacultyDaoImpl.class.getName());
	private Connector connector;
	private static final String INSERT_FACULTY = "insert into faculties (name) values(?)";
	private static final String FIND_FACULTY_BY_ID = "select * from faculties where id=?";
	private static final String FIND_ALL_FACULTIES = "select * from faculties";
	private static final String UPDATE_FACULTY = "update faculties set name=? where id =?";
	private static final String DELETE_FACULTY = "delete from faculties where id =?";

	private static final String INSERT_DEPARTMENT = "insert into faculties_departments (faculty_id,department_id) values (?,?)";
	private static final String INSERT_GROUP = "insert into faculties_groups (faculty_id,group_id) values (?,?)";

	private static final String FIND_DEPARTMENTS_BY_FACULTY_ID = "select department_id from faculties_departments where faculty_id=?";
	private static final String DELETE_ALL_DEPARTMENTS_FROM_FACULTY = "delete from faculties_departments where faculty_id=?";
	private static final String DELETE_DEPARTMENT_FROM_FACULTY = "delete from faculties_departments where department_id=?";

	private static final String FIND_GROUPS_BY_FACULTY_ID = "select group_id from faculties_groups where faculty_id=?";
	private static final String DELETE_ALL_GROUPS_FROM_FACULTY = "delete from faculties_groups where faculty_id=?";
	private static final String DELETE_GROUP_FROM_FACULTY = "delete from faculties_groups where group_id=?";

	public FacultyDaoImpl() {
		connector = new Connector();

	}

	public Faculty create(Faculty facultyArg) throws DaoException {
		log.trace("Started create() method.");

		Faculty faculty = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_FACULTY,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, facultyArg.getName());
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");
				while (resultSet.next()) {
					faculty = new Faculty();
					faculty.setId(resultSet.getInt("id"));
					faculty.setName(resultSet.getString("name"));

				}
			}
		} catch (SQLException e) {
			log.error("Cannot create Faculty :" + facultyArg, e);
			throw new DaoException("Cannot create Faculty :", e);
		}
		log.info("Created a Faculty :" + facultyArg);
		log.trace("Finished create() method.");
		return faculty;
	}

	public Faculty findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Faculty faculty = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_FACULTY_BY_ID)) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery()) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				if (resultSet.next()) {
					faculty = new Faculty();
					int facultyId = resultSet.getInt("id");
					faculty.setId(facultyId);
					faculty.setName(resultSet.getString("name"));
					faculty.setDepartments(findDepartmentsByFacultyId(facultyId));
					faculty.setGroups(findGroupsByFacultyId(facultyId));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Faculty with id=" + id, e);
			throw new DaoException("Cannot find Faculty with id=" + id, e);
		}
		log.info("Found the Faculty :" + faculty);
		log.trace("Finished findById() method.");
		return faculty;
	}

	public List<Faculty> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Faculty> faculties = new ArrayList<Faculty>();

		log.trace("Getting Conncetion and creating prepared statement and getting the result set.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_FACULTIES);
				ResultSet resultSet = statement.executeQuery();) {

			log.debug("Executed query :" + statement);
			log.trace("Got the result set.");

			while (resultSet.next()) {
				Faculty faculty = new Faculty();
				int facultyId = resultSet.getInt("id");
				faculty.setId(facultyId);
				faculty.setName(resultSet.getString("name"));
				faculty.setDepartments(findDepartmentsByFacultyId(facultyId));
				faculty.setGroups(findGroupsByFacultyId(facultyId));

				faculties.add(faculty);
			}
		} catch (SQLException e) {
			log.error("Cannot find all faculties.", e);
			throw new DaoException("Cannot find all faculties.", e);
		}
		log.info("Found all faculties :");
		log.trace("Finished findAll() method.");
		return faculties;
	}

	public Faculty update(Faculty facultyArg) throws DaoException {
		log.trace("Started update() method.");

		Faculty faculty = null;

		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FACULTY,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, facultyArg.getName());
			statement.setInt(2, facultyArg.getId());

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				log.trace("Got the result set.");

				while (resultSet.next()) {
					faculty = new Faculty();
					int facultyId = resultSet.getInt("id");
					faculty.setId(facultyId);
					faculty.setName(resultSet.getString("name"));
					faculty.setDepartments(findDepartmentsByFacultyId(facultyId));
					faculty.setGroups(findGroupsByFacultyId(facultyId));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot update Faculty :" + facultyArg, e);
			throw new DaoException("Cannot update Faculty :" + facultyArg, e);
		}
		log.info("Updated Faculty :" + facultyArg);
		log.trace("Finished update() method.");
		return faculty;
	}

	public void delete(int id) throws DaoException {

		log.trace("Started delete() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY);) {

			statement.setInt(1, id);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Faculty with id=" + id, e);
			throw new DaoException("Cannot delete Faculty with id=" + id, e);
		}
		log.info("Deleted Faculty with id=" + id);
		log.trace("Finished delete() method.");
	}

	public void addDepartment(int facultyId, int departmentId) throws DaoException {

		log.trace("Started addDepartment() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT);) {

			statement.setInt(1, facultyId);
			statement.setInt(2, departmentId);
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot add Department with id=" + departmentId, e);
			throw new DaoException("Cannot add Department with id=" + departmentId, e);
		}
		log.info("Added Department with id=" + departmentId + " to the Faculty with id=" + facultyId);
		log.trace("Finished addDepartment() method.");
	}

	public void addGroup(int facultyId, int groupId) throws DaoException {

		log.trace("Started addGroup() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_GROUP);) {

			statement.setInt(1, facultyId);
			statement.setInt(2, groupId);
			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot add Group with id=" + groupId, e);
			throw new DaoException("Cannot add Group with id=" + groupId, e);
		}
		log.info("Added Group with id=" + groupId + " to the Faculty with id=" + facultyId);
		log.trace("Finished addGroup() method.");
	}

	public List<Department> findDepartmentsByFacultyId(int facultyId) throws DaoException {

		log.trace("Started findDepartmentsByFacultyId() method.");
		log.trace("Getting Conncetion and creating prepared statement.");

		List<Department> departments = new ArrayList<Department>();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENTS_BY_FACULTY_ID)) {

			statement.setInt(1, facultyId);
			DepartmentDao departmentDao = new DepartmentDaoImlp();

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery();) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				while (resultSet.next()) {

					departments.add(departmentDao.findById(resultSet.getInt("department_id")));
				}
			}

		} catch (SQLException e) {
			log.error("Cannot find Departments by Faculty id=" + facultyId, e);
			throw new DaoException("Cannot find Departments by Faculty id=" + facultyId, e);
		}
		log.info("Found " + departments.size() + " Departments by Faculty id=" + facultyId);
		log.trace("Finished findDepartmentsByFacultyId() method.");

		return departments;
	}

	public List<Group> findGroupsByFacultyId(int facultyId) throws DaoException {
		log.trace("Started findGroupsByFacultyId() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		List<Group> groups = new ArrayList<Group>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUPS_BY_FACULTY_ID)) {

			statement.setInt(1, facultyId);
			GroupDao groupDao = new GroupDaoImpl();

			log.trace("Statement :" + statement + " is received.");
			log.trace("Getting the result set.");
			try (ResultSet resultSet = statement.executeQuery();) {
				log.debug("Executed query :" + statement);
				log.trace("Got the result set.");

				while (resultSet.next()) {

					groups.add(groupDao.findById(resultSet.getInt("group_id")));
				}
			}
		} catch (SQLException e) {
			log.error("Cannot find Groups by Faculty id=" + facultyId, e);
			throw new DaoException("Cannot find groups by Faculty id=" + facultyId, e);
		}
		log.info("Found " + groups.size() + " Groups by Faculty id=" + facultyId);
		log.trace("Finished findGroupsByFacultyId() method.");

		return groups;
	}

	public void deleteAllDepartmentsFromFaculty(int facultyId) throws DaoException {
		log.trace("Started deleteAllDepartmentsFromFaculty() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_DEPARTMENTS_FROM_FACULTY);) {

			statement.setInt(1, facultyId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete all Departments from Faculty with id=" + facultyId, e);
			throw new DaoException("Cannot delete all Departments from Faculty with id=" + facultyId, e);
		}
		log.info("Deleted all Departments from Faculty with id=" + facultyId);
		log.trace("Finished deleteAllDepartmentsFromFaculty() method.");
	}

	public void deleteAllGroupsFromFaculty(int facultyId) throws DaoException {
		log.trace("Started deleteAllGroupsFromFaculty() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_GROUPS_FROM_FACULTY);) {

			statement.setInt(1, facultyId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete all Groups from Faculty with id=" + facultyId, e);
			throw new DaoException("Cannot delete all Groups from Faculty with id=" + facultyId, e);
		}
		log.info("Deleted all Groups from Faculty with id=" + facultyId);
		log.trace("Finished deleteAllGroupsFromFaculty() method.");
	}

	public void deleteDepartmentFromFaculty(int departmentId) throws DaoException {
		log.trace("Started deleteDepartmentFromFaculty() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT_FROM_FACULTY);) {

			statement.setInt(1, departmentId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Department with id=" + departmentId, e);
			throw new DaoException("Cannot delete Department with id=" + departmentId, e);
		}
		log.info("Deleted Department with id=" + departmentId);
		log.trace("Finished deleteDepartmentFromFaculty() method.");

	}

	public void deleteGroupFromFaculty(int groupId) throws DaoException {
		log.trace("Started deleteGroupFromFaculty() method.");
		log.trace("Getting Conncetion and creating prepared statement.");
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_FROM_FACULTY);) {

			statement.setInt(1, groupId);

			log.trace("Statement :" + statement + " is received.");
			statement.executeUpdate();
			log.debug("Executed query :" + statement);

		} catch (SQLException e) {
			log.error("Cannot delete Group with id=" + groupId, e);
			throw new DaoException("Cannot delete Group with id=" + groupId, e);
		}
		log.info("Deleted Group with id=" + groupId);
		log.trace("Finished deleteGroupFromFaculty() method.");

	}
}
