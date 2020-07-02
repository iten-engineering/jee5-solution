package ch.itenengineering.ejb3toejb2.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

@Stateless
public class EJB3Bean implements EJB3Remote {

	/**
	 * Hinweis: Das direkte injizieren des Remote Interface funktioniert beim
	 * JBoss Version 4.2.2.GA einwandfrei.
	 * 
	 * Bei der Version 5.0.0.CR2 und 5.0.0.GA gibt es einen Fehler, da das
	 * Remote Interface nicht korrekt im globalen JNDI Namespace eingetragen
	 * wird.
	 */
	// @EJB
	// EJB2Remote ejb2;
	@EJB
	EJB2Home ejb2Home;

	EJB2Remote ejb2;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		try {
			ejb2 = ejb2Home.create();
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	public String echoFromEJB2(String message) throws Exception {

		return ejb2.echo(message);
	}

	public String echo(String message) {

		return "echo from EJB3Bean - message <" + message + "> received!";
	}

} // end class
