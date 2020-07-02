package ch.itenengineering.callcenter.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ch.itenengineering.callcenter.domain.Customer;

@Remote
public interface CallCenterRemote {

	public void clearAll();

	public void addCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public Object getSingleResult(String qlString);

	public List getResultList(String qlString);

	public List getNamedQueryResultList(String name);

	public List getNamedQueryResultList(String name, Date date);

}
