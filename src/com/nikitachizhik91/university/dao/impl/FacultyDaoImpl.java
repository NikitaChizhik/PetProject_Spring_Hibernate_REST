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
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.FacultyDao;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Faculty;
import com.nikitachizhik91.university.model.Group;

public class FacultyDaoImpl implements FacultyDao {
	private Connector connector;
	private static final String INSERT_FACULTY = "insert into faculties (name) values(?)";
	private static final String FIND_FACULTY_BY_ID = "select * from faculties where id=?";
	private static final String FIND_ALL_FACULTIES = "select * from faculties";
	private static final String UPDATE_FACULTY = "update faculties set name=? where id =?";
	private static final String DELETE_FACULTY = "delete from faculties where id =?";

	private static final String INSERT_DEPARTMENT = "insert into faculties_departments (faculty_id,department_id) values (?,?)";
	private static final String INSERT_GROUP = "insert into faculties_departments (faculty_id,group_id) values (?,?)";

	private static final String FIND_DEPARTMENTS_BY_FACULTY_ID = "select department_id from faculties_departments where faculty_id=?";
	private static final String DELETE_ALL_DEPARTMENTS_FROM_FACULTY = "delete from faculties_departments where faculty_id=?";
	private static final String DELETE_DEPARTMENT_FROM_FACULTY = "delete from faculties_departments where department_id=?";

	private static final String FIND_GROUPS_BY_FACULTY_ID = "select group_id from faculties_groups where faculty_id=?";
	private static final String DELETE_ALL_GROUPS_FROM_FACULTY = "delete from faculties_groups where faculty_id=?";
	private static final String DELETE_GROUP_FROM_FACULTY = "delete from faculties_groups where group_id=?";

	public FacultyDaoImpl() {
		connector = new Connector();

	}

	public Faculty create(Faculty facultyArg) {
		Faculty faculty = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_FACULTY,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, facultyArg.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					faculty = new Faculty();
					faculty.setId(resultSet.getInt("id"));
					faculty.setName(resultSet.getString("name"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return faculty;
	}

	public Faculty findById(int id) {
		Faculty faculty = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_FACULTY_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
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
			e.getMessage();
		}
		return faculty;
	}

	public List<Faculty> findAll() {
		List<Faculty> faculties = new ArrayList<Faculty>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_FACULTIES);
				ResultSet resultSet = statement.executeQuery();) {

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
			e.getMessage();
		}
		return faculties;
	}

	public Faculty update(Faculty facultyArg) {
		Faculty faculty = null;
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FACULTY,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, facultyArg.getName());
			statement.setInt(2, facultyArg.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
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
			e.getMessage();
		}
		return faculty;
	}

	public void delete(int id) {
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void addDepartment(Faculty faculty, Department department) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT)) {

			statement.setInt(1, faculty.getId());
			statement.setInt(2, department.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addGroup(Faculty faculty, Group group) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_GROUP)) {

			statement.setInt(1, faculty.getId());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Set<Department> findDepartmentsByFacultyId(int facultyId) {

		Set<Department> departments = new HashSet<Department>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENTS_BY_FACULTY_ID)) {

			statement.setInt(1, facultyId);
			DepartmentDao departmentDao = new DepartmentDaoImlp();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					departments.add(departmentDao.findById(resultSet.getInt("department_id")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	public void deleteAllDepartmentsFromFaculty(int facultyId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_DEPARTMENTS_FROM_FACULTY);) {

			statement.setInt(1, facultyId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDepartmentFromFaculty(int departmentId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT_FROM_FACULTY);) {

			statement.setInt(1, departmentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Set<Group> findGroupsByFacultyId(int facultyId) {

		Set<Group> groups = new HashSet<Group>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_GROUPS_BY_FACULTY_ID)) {

			statement.setInt(1, facultyId);
			GroupDao groupDao = new GroupDaoImpl();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					groups.add(groupDao.findById(resultSet.getInt("group_id")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}

	public void deleteAllGroupsFromFaculty(int facultyId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_GROUPS_FROM_FACULTY);) {

			statement.setInt(1, facultyId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGroupFromFaculty(int groupId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_FROM_FACULTY);) {

			statement.setInt(1, groupId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
