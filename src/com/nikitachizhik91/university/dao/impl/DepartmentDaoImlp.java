package com.nikitachizhik91.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.dao.Connector;
import com.nikitachizhik91.university.dao.DepartmentDao;
import com.nikitachizhik91.university.dao.SubjectDao;
import com.nikitachizhik91.university.dao.TeacherDao;
import com.nikitachizhik91.university.model.Department;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.model.Teacher;

public class DepartmentDaoImlp implements DepartmentDao {
	Connector connector;
	private static final String INSERT_DEPARTMENT = "insert into departments (name) values(?)";
	private static final String FIND_DEPARTMENT_BY_ID = "select * from departments where id=?";
	private static final String FIND_ALL_DEPARTMENTS = "select * from departments";
	private static final String UPDATE_DEPARTMENT = "update departments set name=? where id =?";
	private static final String DELETE_DEPARTMENT = "delete from departments where id =?";

	private static final String INSERT_SUBJECT = "insert into departments_subjects (department_id,subject_id) values (?,?)";
	private static final String INSERT_TEACHER = "insert into departments_teachers (department_id,teacher_id) values (?,?)";

	private static final String FIND_TEACHERS_BY_DEPARTMENT_ID = "select teacher_id from departments_teachers where department_id=?";
	private static final String DELETE_ALL_TEACHERS_FROM_DEPARTMENT = "delete from departments_teachers where department_id=?";
	private static final String DELETE_TEACHER_FROM_DEPARTMENT = "delete from departments_teachers where teacher_id=?";

	private static final String FIND_SUBJECTS_BY_DEPARTMENT_ID = "select subject_id from departments_subjects where department_id=?";
	private static final String DELETE_ALL_SUBJECTS_FROM_DEPARTMENT = "delete from departments_subjects where department_id=?";
	private static final String DELETE_SUBJECT_FROM_DEPARTMENT = "delete from departments_subjects where subject_id=?";

	public DepartmentDaoImlp() {
		connector = new Connector();
	}

	public Department create(Department departmentArg) {

		Department department = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, departmentArg.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					department = new Department();
					department.setId(resultSet.getInt("id"));
					department.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return department;
	}

	public Department findById(int id) {

		Department department = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					department = new Department();
					int departmentId = resultSet.getInt("id");
					department.setId(departmentId);
					department.setName(resultSet.getString("name"));
					department.setTeachers(findTeachersByDepartmentId(departmentId));
					department.setSubjects(findSubjectsByDepartmentId(departmentId));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return department;
	}

	public List<Department> findAll() {

		List<Department> departments = new ArrayList<Department>();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_DEPARTMENTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Department department = new Department();
				int departmentId = resultSet.getInt("id");
				department.setId(departmentId);
				department.setName(resultSet.getString("name"));
				department.setTeachers(findTeachersByDepartmentId(departmentId));
				department.setSubjects(findSubjectsByDepartmentId(departmentId));

				departments.add(department);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return departments;
	}

	public Department update(Department departmentArg) {

		Department department = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, departmentArg.getName());
			statement.setInt(2, departmentArg.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					department = new Department();
					int departmentId = resultSet.getInt("id");
					department.setId(departmentId);
					department.setName(resultSet.getString("name"));
					department.setTeachers(findTeachersByDepartmentId(departmentId));
					department.setSubjects(findSubjectsByDepartmentId(departmentId));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return department;
	}

	public void delete(int id) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void addSubject(Subject subject, Department department) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, department.getId());
			statement.setInt(2, subject.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addTeacher(Teacher teacher, Department department) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_TEACHER,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, department.getId());
			statement.setInt(2, teacher.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Teacher> findTeachersByDepartmentId(int departmentId) {

		List<Teacher> teachers = new ArrayList<Teacher>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_TEACHERS_BY_DEPARTMENT_ID)) {

			statement.setInt(1, departmentId);
			TeacherDao teacherDao = new TeacherDaoImpl();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					teachers.add(teacherDao.findById(resultSet.getInt("teacher_id")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}

	public void deleteAllTeachersFromDepartment(int departmentId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_TEACHERS_FROM_DEPARTMENT);) {

			statement.setInt(1, departmentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeacherFromDepartment(int teacherId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_FROM_DEPARTMENT);) {

			statement.setInt(1, teacherId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Subject> findSubjectsByDepartmentId(int departmentId) {

		List<Subject> subjects = new ArrayList<Subject>();
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_SUBJECTS_BY_DEPARTMENT_ID)) {

			statement.setInt(1, departmentId);
			SubjectDao subjectDao = new SubjectDaoImpl();

			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {

					subjects.add(subjectDao.findById(resultSet.getInt("subject_id")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	public void deleteAllSubjectsFromDepartment(int departmentId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALL_SUBJECTS_FROM_DEPARTMENT);) {

			statement.setInt(1, departmentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSubjectFromDepartment(int subjectId) {

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_FROM_DEPARTMENT);) {

			statement.setInt(1, subjectId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
