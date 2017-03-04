package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nikitachizhik91.university.domain.Department;

public class DepartmentDAOImlp {

	private static final String INSERT_DEPARTMENT = "insert into departments (name) values(?)";
	private static final String FIND_DEPARTMENT_BY_ID = "select * from departments where id=?";
	private static final String FIND_ALL_DEPARTMENTS = "select * from departments";
	private static final String UPDATE_DEPARTMENT = "update departments set name=? where id =?";
	private static final String DELETE_DEPARTMENT = "delete from departments where id =?";

	public Department create(Department department) {
		Department departmentReceived = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, department.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					departmentReceived = new Department();
					departmentReceived.setId(resultSet.getInt("id"));
					departmentReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return departmentReceived;
	}

	public Department findById(int id) {
		Department departmentReceived = new Department();

		try (Connection connection = Connector.getConnection();

		PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {

					departmentReceived.setId(resultSet.getInt("id"));
					departmentReceived.setName(resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return departmentReceived;
	}

	public List<Department> findAll() {
		List<Department> departmentsReceived = new ArrayList<Department>();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_DEPARTMENTS);
				ResultSet resultSet = statement.executeQuery();) {

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getInt("id"));
				department.setName(resultSet.getString("name"));
				departmentsReceived.add(department);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return departmentsReceived;
	}

	public Department update(Department department) {
		Department departmentReceived = null;
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.setString(1, department.getName());
			statement.setInt(2, department.getId());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					departmentReceived = new Department();
					departmentReceived.setId(resultSet.getInt("id"));
					departmentReceived.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.getMessage();
		}
		return departmentReceived;
	}

	public void delete(int id) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT);) {

			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
