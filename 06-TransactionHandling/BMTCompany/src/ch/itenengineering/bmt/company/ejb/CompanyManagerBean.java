package ch.itenengineering.bmt.company.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.bmt.company.domain.Company;

@Stateless
@RemoteBinding(jndiBinding = "ejb/BMTCompanyManager")
@TransactionManagement(TransactionManagementType.BEAN)
public class CompanyManagerBean implements CompanyManagerRemote {

	@PersistenceContext(unitName = "BMTCompanyPu")
	private EntityManager em;

	@Resource
	private UserTransaction ut;

	public Company persist(Company company) {
		try {
			ut.begin();
			em.persist(company);
			ut.commit();

			return company;

		} catch (Exception e) {
			silentRollback();
			throw new EJBException("adding a new company failed with "
					+ e.toString());
		}
	}

	public Company merge(Company company) {
		try {
			ut.begin();
			Company result = em.merge(company);
			ut.commit();

			return result;

		} catch (Exception e) {
			silentRollback();
			throw new EJBException("modify failed with " + e.toString());
		}
	}

	public void remove(int id) {
		try {
			ut.begin();

			// find company
			Company company = em.find(Company.class, id);

			// remove company
			if (company != null) {
				em.remove(company);
			}

			ut.commit();

		} catch (Exception e) {
			silentRollback();
			throw new EJBException("delete failed with " + e.toString());
		}
	}

	public Company find(int id) {
		try {
			ut.begin();
			Company result = em.find(Company.class, id);
			ut.commit();

			return result;

		} catch (Exception e) {
			silentRollback();
			throw new EJBException("find failed with " + e.toString());
		}
	}

	public List find(String namePattern) {
		try {
			ut.begin();
			Query query = em
					.createQuery("select c from Company as c where c.name like :paramName order by c.name");
			query.setParameter("paramName", namePattern);

			List result = query.getResultList();
			ut.commit();

			return result;

		} catch (Exception e) {
			silentRollback();
			throw new EJBException("find by name failed with " + e.toString());
		}

	}

	private void silentRollback() {
		try {
			ut.rollback();
		} catch (Exception e) {
			// ignore
		}
	}

} // end of class
