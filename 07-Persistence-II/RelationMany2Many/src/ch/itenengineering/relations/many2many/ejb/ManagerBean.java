package ch.itenengineering.relations.many2many.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.relations.many2many.domain.Course;
import ch.itenengineering.relations.many2many.domain.Student;

@Stateless
@RemoteBinding(jndiBinding = "ejb/RMany2ManyManager")
public class ManagerBean implements ManagerRemote {

	@PersistenceContext(unitName = "RelationMany2ManyPu")
	private EntityManager em;

	public void clear() {
		em.createNativeQuery("delete from r_many2many_student_course")
				.executeUpdate();
		em.createQuery("delete from Student").executeUpdate();
		em.createQuery("delete from Course").executeUpdate();
	}

	public void book(int studentId, int courseId) {

		// get managed course instance
		Course course = (Course) find(Course.class, courseId);

		// get managed student
		Student student = (Student) find(Student.class, studentId);

		// book course
		student.getCourses().add(course);
		merge(student);

		// alternative via native query
		// Query query = em.createNativeQuery(
		// "insert into r_many2many_student_course (student_id, course_id) values (:sid, :cid)"
		// );
		// query.setParameter("sid", studentId);
		// query.setParameter("cid", courseId);
		// query.executeUpdate();
	}

	public void cancel(int studentId, int courseId) {
		// get managed course instance
		Course course = (Course) find(Course.class, courseId);

		// get managed student
		Student student = (Student) find(Student.class, studentId);

		// cancel course
		student.getCourses().remove(course);
		merge(student);

		// alternative via native query
		// Query query = em.createNativeQuery(
		// "delete from r_many2many_student_course where student_id=:sid and course_id=:cid"
		// );
		// query.setParameter("sid", studentId);
		// query.setParameter("cid", courseId);
		// query.executeUpdate();
	}

	public Object persist(Object entity) {
		em.persist(entity);
		return entity;
	}

	public Object merge(Object entity) {
		return em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey) {
		return em.find(clazz, primaryKey);
	}

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey) {
		Object obj = find(clazz, primaryKey);
		if (obj != null) {
			em.remove(obj);
		}
	}

} // end of class
