package ch.itenengineering.relations.r1.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding = "ejb/R1Manager")
public class R1ManagerBean implements R1ManagerRemote {

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
