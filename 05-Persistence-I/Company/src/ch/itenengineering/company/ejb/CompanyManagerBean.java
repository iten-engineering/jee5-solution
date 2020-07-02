package ch.itenengineering.company.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.company.domain.Company;

@Stateless
@RemoteBinding(jndiBinding = "ejb/CompanyManager")
public class CompanyManagerBean implements CompanyManagerRemote {

	@PersistenceContext(unitName = "CompanyPu")
	private EntityManager em;

	public Company persist(Company company) {

		em.persist(company);

		return company;
	}

	public Company merge(Company company) {

		return em.merge(company);
	}

	public void remove(int id) {
		// find company
		Company company = em.find(Company.class, id);

		// remove company
		if (company != null) {
			em.remove(company);
		}
	}

	public Company find(int id) {

		return em.find(Company.class, id);
	}

	public List find(String namePattern) {

		Query query = em
				.createQuery("select c from Company as c where c.name like :paramName order by c.name");

		query.setParameter("paramName", namePattern);

		return query.getResultList();
	}

} // end of class
