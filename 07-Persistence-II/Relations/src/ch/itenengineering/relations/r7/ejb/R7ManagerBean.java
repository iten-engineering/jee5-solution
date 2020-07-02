package ch.itenengineering.relations.r7.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding = "ejb/R7Manager")
public class R7ManagerBean implements R7ManagerRemote {

	@PersistenceContext(unitName = "RelationsPu")
	private EntityManager em;

	public void clear() {
		em.createNativeQuery("delete from r7_student_course").executeUpdate();
		em.createQuery("delete from R7Student").executeUpdate();
		em.createQuery("delete from R7Course").executeUpdate();
	}

	public void book(int studentId, int courseId) {
		Query query = em
				.createNativeQuery("insert into r7_student_course (student_id, course_id) values (:sid, :cid)");
		query.setParameter("sid", studentId);
		query.setParameter("cid", courseId);
		query.executeUpdate();
	}

	public void cancel(int studentId, int courseId) {
		Query query = em
				.createNativeQuery("delete from r7_student_course where student_id=:sid and course_id=:cid");
		query.setParameter("sid", studentId);
		query.setParameter("cid", courseId);
		query.executeUpdate();
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
