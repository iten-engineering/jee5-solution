package ch.itenengineering.inheritance.i3.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.inheritance.i3.domain.Person;

@Stateless
@RemoteBinding(jndiBinding = "ejb/ManagerI3")
public class ManagerBeanI3 implements ManagerRemoteI3 {

	@PersistenceContext(unitName = "InheritancePu")
	private EntityManager em;

	public Person add(Person person) {

		em.persist(person);

		return person;
	}

	public void deleteAll() {
		em.createQuery("delete from PersonI3").executeUpdate();
	}

	public List findAll() {
		return em.createQuery("select p from PersonI3 p").getResultList();
	}

} // end of class
