package ch.itenengineering.callcenter.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.callcenter.domain.Customer;

@Stateless
@RemoteBinding(jndiBinding = "ejb/CallCenter")
public class CallCenterBean implements CallCenterRemote {

	@PersistenceContext(unitName = "CallCenterPu")
	private EntityManager em;

	public void clearAll() {
		em.createQuery("delete from Call").executeUpdate();
		em.createQuery("delete from Address").executeUpdate();
		em.createQuery("delete from Customer").executeUpdate();
	}

	public void addCustomer(Customer customer) {
		em.persist(customer);
	}

	public Customer getCustomer(int customerId) {
		Query query = em
				.createQuery("select c from Customer c where c.customerId=:id");
		query.setParameter("id", customerId);

		return (Customer) query.getSingleResult();
	}

	public Object getSingleResult(String qlString) {
		return em.createQuery(qlString).getSingleResult();
	}

	public List getResultList(String qlString) {
		return em.createQuery(qlString).getResultList();
	}

	public List getNamedQueryResultList(String name) {
		return em.createNamedQuery(name).getResultList();
	}

	public List getNamedQueryResultList(String name, Date date) {

		Query query = em.createNamedQuery(name);

		query.setParameter("date", date, TemporalType.TIMESTAMP);

		return query.getResultList();
	}

} // end of class
