package ch.itenengineering.ejb3toejb2.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;

public class EJB2Bean implements javax.ejb.SessionBean {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	// ------------------------------------------------------------------------
	// bean methods
	// ------------------------------------------------------------------------

	public void ejbCreate() {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbRemove() {
	}

	public void setSessionContext(SessionContext sessionContext)
			throws EJBException, RemoteException {
		this.sessionContext = sessionContext;
	}

	// ------------------------------------------------------------------------
	// business methods
	// ------------------------------------------------------------------------

	public String echo(String message) {
		return "echo from EJB2Bean - message <" + message + "> received!";
	}

} // end class
