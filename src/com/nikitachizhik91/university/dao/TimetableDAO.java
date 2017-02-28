package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.nikitachizhik91.university.domain.Teacher;
import com.nikitachizhik91.university.domain.Timetable;

public class TimetableDAO {

	public Timetable getTeachersTimetableForDay(Teacher teacher, Date date) {
		Timetable timetable = new Timetable();

		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"select * from lessons join teachers on lessons.lesson_id=teachers.lesson_id_fk"
								+ "where teacher_id=? and date='2017-01-01';");) {

			statement.setInt(1, teacher.getId());

			Timestamp timestamp = new Timestamp(date.getTime());
			statement.setTimestamp(2, timestamp);

			try (ResultSet resultSet = statement.executeQuery();) {

				if (resultSet.next()) {

				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return timetable;
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
	// statement = connection.prepareStatement("select * from room where
	// number
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
	// statement = connection.prepareStatement("update room set number=?
	// where
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
	// statement = connection.prepareStatement("delete from room where
	// number
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
