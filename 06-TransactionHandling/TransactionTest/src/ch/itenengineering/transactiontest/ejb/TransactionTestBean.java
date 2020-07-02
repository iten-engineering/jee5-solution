package ch.itenengineering.transactiontest.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import ch.itenengineering.transactiontest.domain.TransactionTest;

@Stateful
@RemoteBinding(jndiBinding = "ejb/TransactionTest")
public class TransactionTestBean implements TransactionTestRemote {

	@PersistenceContext(unitName = "TransactionTestPu")
	private EntityManager em;

	public boolean isBeanAlive() {
		return true;
	}

	public void testSysException() {
		em.persist(new TransactionTest(
				"testSysException, rollback & bean destruction expected"));
		throw new SysException();
	}

	public void testSysAsAppWithRollbackTrueException() {
		em.persist(new TransactionTest(
				"testSysAsAppWithRollbackTrueException, rollback expected"));
		throw new SysAsAppWithRollbackTrueException();
	}

	public void testSysAsAppWithRollbackFalseException() {
		em.persist(new TransactionTest(
				"testSysAsAppWithRollbackFalseException, commit expected"));
		throw new SysAsAppWithRollbackFalseException();
	}

	public void testAppException() throws Exception {
		em.persist(new TransactionTest("testAppException, commit expected"));
		throw new AppException();
	}

	public void testAppWithRollbackTrueException() throws Exception {
		em.persist(new TransactionTest(
				"testAppWithRollbackTrueException, rollback expected"));
		throw new AppWithRollbackTrueException();
	}

	public void testAppWithRollbackFalseException() throws Exception {
		em.persist(new TransactionTest(
				"testAppWithRollbackFalseException, commit expected"));
		throw new AppWithRollbackFalseException();
	}

} // end of class
