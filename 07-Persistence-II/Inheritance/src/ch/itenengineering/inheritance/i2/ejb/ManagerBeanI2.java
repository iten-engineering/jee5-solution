package ch.itenengineering.inheritance.i2.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.inheritance.i2.domain.Person;

@Stateless
@RemoteBinding(jndiBinding = "ejb/ManagerI2")
public class ManagerBeanI2 implements ManagerRemoteI2 {

	@PersistenceContext(unitName = "InheritancePu")
	private EntityManager em;

	public Person add(Person person) {

		em.persist(person);

		return person;
	}

	public void deleteAll() {
		em.createQuery("delete from PersonI2").executeUpdate();
	}

	public List findAll() {
		return em.createQuery("select p from PersonI2 p").getResultList();
	}

} // end of class
