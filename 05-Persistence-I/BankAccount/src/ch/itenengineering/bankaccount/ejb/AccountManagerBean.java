package ch.itenengineering.bankaccount.ejb;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.ejb3.annotation.RemoteBinding;


import ch.itenengineering.bankaccount.domain.BankAccount;

@Stateful
@RemoteBinding(jndiBinding = "ejb/AccountManager")
public class AccountManagerBean implements AccountManagerRemote {

	// session context
	@Resource
	SessionContext sessionContext;

	// important: context type must be set to extended
	@PersistenceContext(unitName = "BankPu", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	// our managed entity
	private BankAccount account;

	public void open(Integer number) {
		account = em.find(BankAccount.class, number);
		if (account == null) {
			account = new BankAccount(number);
			em.persist(account);
		}
	}

	public void setOwner(String owner) {
		account.setOwner(owner);
	}

	public void deposit(Double amount) {
		account.deposit(amount);
	}

	public Double getBalance() {
		return account.getBalance();
	}

	public void withdraw(Double amount) throws Exception {

		account.withdraw(amount);

		if (account.getBalance() < 0) {
			// throw unchecked Exception, forces rollback of transaction
			// and destroy stateful session bean
			throw new EJBException("sorry, withdraw denied: insufficent money");
		}

	}

	@Remove
	public void close() {
		System.out
				.println("remove bean and close account (end of persistence context)");
	}

} // end of class
