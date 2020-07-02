package ch.itenengineering.ejb2toejb3.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;

public class EJB2Bean implements javax.ejb.SessionBean {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private SessionContext sessionContext;
	
	@EJB
	EJB3Local ejb3;

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

	public String echoFromEJB3(String message) {
		return ejb3.echo(message);
	}

} // end class
