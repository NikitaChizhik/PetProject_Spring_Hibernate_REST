package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nikitachizhik91.university.domain.Department;

public class DepartmentDAO {

	// one teacher has only one subject.
	public void create(Department department) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2", "postgres",
					"HolyBible");

			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.prepareStatement(
						"insert into teachers" + "(teacher_id,name,fk_subject_id)" + "values(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				// statement.setInt(1, id);
				// statement.setString(2, name);
				// statement.setInt(3, subject.getId());
				statement.executeUpdate();

				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getInt(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (statement != null) {
						statement.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// public Room getRoom(String name) {
	// Room roomRecieved = new Room();
	// Connection connection = null;
	//
	// try {
	// connection =
	// DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2",
	// "postgres",
	// "HolyBible");
	//
	// PreparedStatement statement = null;
	// ResultSet resultSet = null;
	// try {
	// statement = connection.prepareStatement("select * from room where number
	// like ? order by number");
	// name += "%";
	// statement.setString(1, name);
	//
	// resultSet = statement.executeQuery();
	// if (resultSet.next()) {
	// roomRecieved.setId(resultSet.getInt(1));
	// roomRecieved.setNumber(resultSet.getString(2));
	// System.out.println(resultSet.getInt(1));
	// System.out.println(resultSet.getString(2));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (resultSet != null) {
	// resultSet.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// try {
	// if (statement != null) {
	// statement.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (connection != null) {
	// connection.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// return roomRecieved;
	// }
	//
	// public ArrayList<Room> getAllRooms() {
	// ArrayList<Room> roomsRecieved = new ArrayList<Room>();
	// Connection connection = null;
	//
	// try {
	// connection =
	// DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2",
	// "postgres",
	// "HolyBible");
	//
	// PreparedStatement statement = null;
	// ResultSet resultSet = null;
	// try {
	// statement = connection.prepareStatement("select * from room order by
	// number");
	// // name += "%";
	// // statement.setString(1, name);
	//
	// resultSet = statement.executeQuery();
	//
	// while (resultSet.next()) {
	// Room room = new Room();
	// room.setId(resultSet.getInt(1));
	// room.setNumber(resultSet.getString(2));
	// roomsRecieved.add(room);
	//
	// System.out.println(resultSet.getInt(1));
	// System.out.println(resultSet.getString(2));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (resultSet != null) {
	// resultSet.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// try {
	// if (statement != null) {
	// statement.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (connection != null) {
	// connection.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// return roomsRecieved;
	// }
	//
	// public void updateRoom(String name, String newName) {
	// Connection connection = null;
	// // bez RS?
	// try {
	// connection =
	// DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2",
	// "postgres",
	// "HolyBible");
	//
	// PreparedStatement statement = null;
	// try {
	// statement = connection.prepareStatement("update room set number=? where
	// number =?");
	// // name += "%";
	// // newName += "%";
	// statement.setString(1, newName);
	// statement.setString(2, name);
	//
	// statement.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	//
	// try {
	// if (statement != null) {
	// statement.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (connection != null) {
	// connection.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	//
	// public void deleteRoom(String name) {
	// Connection connection = null;
	//
	// try {
	// connection =
	// DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/University2",
	// "postgres",
	// "HolyBible");
	//
	// PreparedStatement statement = null;
	// try {
	// statement = connection.prepareStatement("delete from room where number
	// =?");
	// // name += "%";
	// // newName += "%";
	//
	// statement.setString(1, name);
	//
	// statement.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	//
	// try {
	// if (statement != null) {
	// statement.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (connection != null) {
	// connection.close();
	// }
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }

}
