package ch.itenengineering.helloejb2.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;

public class HelloEJB2Bean implements javax.ejb.SessionBean {

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * session bean context
	 */
	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	// ------------------------------------------------------------------------
	// bean methods
	// ------------------------------------------------------------------------

	public void ejbCreate() {
		System.out.println("ejbCreate()");
	}

	public void ejbActivate() {
		System.out.println("ejbActivate()...");
	}

	public void ejbPassivate() {
		System.out.println("ejbPassivate()...");
	}

	public void ejbRemove() {
		System.out.println("ejbRemove()...");
	}

	public void setSessionContext(SessionContext sessionContext)
			throws EJBException, RemoteException {
		System.out.println("setSessionContext()...");
		this.sessionContext = sessionContext;
	}

	// ------------------------------------------------------------------------
	// business methods
	// ------------------------------------------------------------------------

	/**
	 * return received message with a timestamp
	 */
	public String echo(String message) {
		return "echo from HelloEJB2Bean - message <" + message + "> received!";
	}

} // end class
