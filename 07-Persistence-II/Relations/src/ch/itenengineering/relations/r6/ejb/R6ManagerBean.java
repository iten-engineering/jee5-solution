package ch.itenengineering.relations.r6.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.relations.r6.domain.R6Course;
import ch.itenengineering.relations.r6.domain.R6Student;

@Stateless
@RemoteBinding(jndiBinding = "ejb/R6Manager")
public class R6ManagerBean implements R6ManagerRemote {

	public void clear() {
		em.createNativeQuery("delete from r6_student_course").executeUpdate();
		em.createQuery("delete from R6Student").executeUpdate();
		em.createQuery("delete from R6Course").executeUpdate();
	}

	public void book(int studentId, int courseId) {

		// get managed course instance
		R6Course course = (R6Course) find(R6Course.class, courseId);

		// get managed student
		R6Student student = (R6Student) find(R6Student.class, studentId);

		// book course
		student.getCourses().add(course);
		merge(student);

		// alternative via native query
		// Query query = em.createNativeQuery("insert into r6_student_course (student_id, course_id) values (:sid, :cid)");
		// query.setParameter("sid", studentId);
		// query.setParameter("cid", courseId);
		// query.executeUpdate();
	}

	public void cancel(int studentId, int courseId) {
		// get managed course instance
		R6Course course = (R6Course) find(R6Course.class, courseId);

		// get managed student
		R6Student student = (R6Student) find(R6Student.class, studentId);

		// cancel course
		student.getCourses().remove(course);
		merge(student);

		// alternative via native query
		// Query query = em.createNativeQuery("delete from r6_student_course where student_id=:sid and course_id=:cid");
		// query.setParameter("sid", studentId);
		// query.setParameter("cid", courseId);
		// query.executeUpdate();
	}

	@PersistenceContext(unitName = "RelationsPu")
	private EntityManager em;

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
