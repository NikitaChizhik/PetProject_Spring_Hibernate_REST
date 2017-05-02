package com.nikitachizhik91.university.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nikitachizhik91.university.dao.DaoException;
import com.nikitachizhik91.university.dao.GroupDao;
import com.nikitachizhik91.university.model.Group;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;

@Repository
public class GroupDaoImpl implements GroupDao {

	private final static Logger log = LogManager.getLogger(GroupDaoImpl.class.getName());

	private static final String INSERT_STUDENT = "insert into groups_students (group_id,student_id) values (?,?)";
	private static final String FIND_STUDENTS_BY_GROUP_ID = "select student_id from groups_students where group_id=?";
	private static final String DELETE_ALL_STUDENTS_FROM_GROUP = "delete from groups_students where group_id=?";
	private static final String DELETE_STUDENT_FROM_GROUP = "delete from groups_students where student_id=?";

	private static final String FIND_GROUPS_WITHOUT_FACULTY = "SELECT id FROM groups g WHERE NOT EXISTS(SELECT NULL FROM faculties_groups fg WHERE fg.group_id = g.id)";

	@Autowired
	private SessionFactory sessionFactory;

	public Group create(Group group) throws DaoException {

		log.trace("Started create() method.");

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(group);
			session.getTransaction().commit();
			group.setId(id);
		}

		log.info("Created a Group :" + group);
		log.trace("Finished create() method.");

		return group;
	}

	public Group findById(int id) throws DaoException {
		log.trace("Started findById() method.");

		Group group = null;

		try (Session session = sessionFactory.openSession()) {
			group = session.get(Group.class, id);
		}

		log.info("Found the Group :" + group);
		log.trace("Finished findById() method.");

		return group;
	}

	@SuppressWarnings("unchecked")
	public List<Group> findAll() throws DaoException {
		log.trace("Started findAll() method.");

		List<Group> groups = null;
		try (Session session = sessionFactory.openSession()) {
			groups = (List<Group>) session.createQuery("from Group").list();
		}

		log.info("Found all Groups :");
		log.trace("Finished findAll() method.");

		return groups;
	}

	public Group update(Group group) throws DaoException {
		log.trace("Started update() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.update(group);
			session.getTransaction().commit();
		}

		log.info("Updated Group :" + group);
		log.trace("Finished update() method.");

		return group;
	}

	public void delete(int id) throws DaoException {
		log.trace("Started delete() method.");

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			session.delete(session.get(Group.class, id));
			session.getTransaction().commit();
		}

		log.info("Deleted Group with id=" + id);
		log.trace("Finished delete() method.");
	}

	public void addStudent(int studentId, int groupId) throws DaoException {
		log.trace("Started addStudent() method.");

		// "INSERT INTO Employee(firstName, lastName, salary)" + "SELECT
		// firstName, lastName, salary FROM old_employee";
		
		// insert into groups_students (group_id,student_id) values (?,?)
		try (Session session = sessionFactory.openSession()) {
			session.createQuery("insert into groups_students (group_id,student_id) values (groupId,studentId)")
					.setParameter("groupId", groupId).setParameter("studentId", studentId).executeUpdate();
		}

		log.info("Added Student with id=" + studentId + " to the group with id=" + groupId);
		log.trace("Finished addStudent() method.");
	}

	public List<Student> findStudentsByGroupId(int groupId) throws DaoException {

		// log.trace("Started findStudentsByGroupId() method.");
		// log.trace("Getting Conncetion and creating prepared statement.");
		//
		// List<Student> students = new ArrayList<Student>();
		//
		// try (Connection connection = dataSource.getConnection();
		// PreparedStatement statement =
		// connection.prepareStatement(FIND_STUDENTS_BY_GROUP_ID)) {
		//
		// statement.setInt(1, groupId);
		//
		// log.trace("Statement :" + statement + " is received.");
		// log.trace("Getting the result set.");
		//
		// try (ResultSet resultSet = statement.executeQuery();) {
		//
		// log.debug("Executed query :" + statement);
		// log.trace("Got the result set.");
		//
		// while (resultSet.next()) {
		//
		// students.add(studentDao.findById(resultSet.getInt("student_id")));
		// }
		// }
		//
		// } catch (SQLException e) {
		//
		// log.error("Cannot find Students by Group id=" + groupId, e);
		// throw new DaoException("Cannot find Students by Group id=" + groupId,
		// e);
		// }
		//
		// log.info("Found " + students.size() + " Students by Group id=" +
		// groupId);
		// log.trace("Finished findStudentsByGroupId() method.");

		return null;
	}

	public void deleteAllStudentsFromGroup(int groupId) throws DaoException {
		// log.trace("Started deleteAllStudentsFromGroup() method.");
		// log.trace("Getting Conncetion and creating prepared statement.");
		// try (Connection connection = dataSource.getConnection();
		// PreparedStatement statement =
		// connection.prepareStatement(DELETE_ALL_STUDENTS_FROM_GROUP);) {
		//
		// statement.setInt(1, groupId);
		//
		// log.trace("Statement :" + statement + " is received.");
		// statement.executeUpdate();
		// log.debug("Executed query :" + statement);
		//
		// } catch (SQLException e) {
		// log.error("Cannot delete all students from Group with id=" + groupId,
		// e);
		// throw new DaoException("Cannot delete all students from Group with
		// id=" + groupId, e);
		// }
		// log.info("Deleted all students from Group with id=" + groupId);
		// log.trace("Finished deleteAllStudentsFromGroup() method.");
	}

	public void deleteStudentFromGroup(int studentId) throws DaoException {
		// log.trace("Started deleteStudentFromGroup() method.");
		// log.trace("Getting Conncetion and creating prepared statement.");
		// try (Connection connection = dataSource.getConnection();
		// PreparedStatement statement =
		// connection.prepareStatement(DELETE_STUDENT_FROM_GROUP);) {
		//
		// statement.setInt(1, studentId);
		//
		// log.trace("Statement :" + statement + " is received.");
		// statement.executeUpdate();
		// log.debug("Executed query :" + statement);
		//
		// } catch (SQLException e) {
		// log.error("Cannot delete Student with id=" + studentId, e);
		// throw new DaoException("Cannot delete Student with id=" + studentId,
		// e);
		// }
		// log.info("Deleted Student with id=" + studentId);
		// log.trace("Finished deleteStudentFromGroup() method.");
		//
	}

	public List<Group> findGroupsWithoutFaculty() throws DaoException {
		// log.trace("Started findGroupsWithoutFaculty() method.");
		//
		// List<Group> groups = new ArrayList<Group>();
		//
		// log.trace("Getting Conncetion and creating prepared statement and
		// getting the result set.");
		// try (Connection connection = dataSource.getConnection();
		// PreparedStatement statement =
		// connection.prepareStatement(FIND_GROUPS_WITHOUT_FACULTY);
		// ResultSet resultSet = statement.executeQuery();) {
		//
		// log.debug("Executed query :" + statement);
		// log.trace("Got the result set.");
		//
		// while (resultSet.next()) {
		//
		// Group group = findById(resultSet.getInt("id"));
		// groups.add(group);
		// }
		//
		// } catch (SQLException e) {
		// log.error("Cannot find all groups which are without faculty.", e);
		// throw new DaoException("Cannot find all groups which are without
		// faculty.", e);
		// }
		// log.info("Found all groups which are without faculty.");
		// log.trace("Finished findGroupsWithoutFaculty() method.");

		return null;
	}
}
