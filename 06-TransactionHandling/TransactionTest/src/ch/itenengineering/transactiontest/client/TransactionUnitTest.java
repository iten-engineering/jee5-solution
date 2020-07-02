package ch.itenengineering.transactiontest.client;

import static org.junit.Assert.assertTrue;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import ch.itenengineering.transactiontest.ejb.AppException;
import ch.itenengineering.transactiontest.ejb.AppWithRollbackFalseException;
import ch.itenengineering.transactiontest.ejb.AppWithRollbackTrueException;
import ch.itenengineering.transactiontest.ejb.SysAsAppWithRollbackFalseException;
import ch.itenengineering.transactiontest.ejb.SysAsAppWithRollbackTrueException;
import ch.itenengineering.transactiontest.ejb.SysException;
import ch.itenengineering.transactiontest.ejb.TransactionTestRemote;

public class TransactionUnitTest {

	TransactionTestRemote remote;

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	@Before
	public void setUp() throws Exception {
		Context ctx = getInitialContext();
		remote = (TransactionTestRemote) ctx.lookup("ejb/TransactionTest");
	}

	/**
	 * expect rollback and bean destruction
	 */
	@Test
	public void testSysException() {
		try {
			remote.testSysException();
			assertTrue(false);
		} catch (SysException se) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * expect rollback but no bean destruction
	 */
	@Test
	public void testSysAsAppWithRollbackTrueException() {
		try {
			remote.testSysAsAppWithRollbackTrueException();
			assertTrue(false);
		} catch (SysAsAppWithRollbackTrueException se) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * expect commit (check table transaction_test for an entry)
	 */
	@Test
	public void testSysAsAppWithRollbackFalseException() {
		try {
			remote.testSysAsAppWithRollbackFalseException();
			assertTrue(false);
		} catch (SysAsAppWithRollbackFalseException se) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * expect commit (check table transaction_test for an entry)
	 */
	@Test
	public void testAppException() {
		try {
			remote.testAppException();
			assertTrue(false);
		} catch (AppException ae) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * expect rollback but no bean destruction
	 */
	@Test
	public void testAppWithRollbackTrueException() {
		try {
			remote.testAppWithRollbackTrueException();
			assertTrue(false);
		} catch (AppWithRollbackTrueException ae) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * expect commit (check table transaction_test for an entry)
	 */
	@Test
	public void testAppWithRollbackFalseException() {
		try {
			remote.testAppWithRollbackFalseException();
			assertTrue(false);
		} catch (AppWithRollbackFalseException ae) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		try {
			remote.isBeanAlive();
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

} // end test
