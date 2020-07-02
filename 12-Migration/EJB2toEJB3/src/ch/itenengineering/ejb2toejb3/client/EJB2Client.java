package ch.itenengineering.ejb2toejb3.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.ejb2toejb3.ejb.EJB2Home;
import ch.itenengineering.ejb2toejb3.ejb.EJB2Remote;

/**
 * Das Beispiel zeigt wie ein Stateless Session Bean V2.x ein Stateless Session
 * Bean V3.0 (via Local Interface) aufruft. Beide Bean's werden in der gleiche
 * EAR Datei auf den Java EE Server deployed.
 * 
 * 
 * Hinweis A:
 * 
 * In der ejb-jar.xml Datei wird dem Java EE Server mit der Angabe "version=3.0"
 * mitgeteilt, dass sich nicht nur Bean's der Version 2.x in der EAR Datei
 * befinden.
 * 
 * 
 * Hinweis B:
 * 
 * Der Name für den Client Lookup auf das Home Interface wird im hersteller
 * spezifischen XML Deskriptor jboss.xml definiert. In unserem Beispiel lautet
 * er "ejb/EJB2/home".
 */
public class EJB2Client {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void sayHello() throws Exception {

		// get initial context
		Context ctx = getInitialContext();

		// get object refrence and cast to home object
		Object objref = ctx.lookup("ejb/EJB2/home");

		EJB2Home home = (EJB2Home) PortableRemoteObject.narrow(objref,
				EJB2Home.class);

		// get remote object (proxy)
		EJB2Remote ejb2 = home.create();

		// invoke bean method

		System.out.println(ejb2.echo("Hello EJB2"));
		System.out.println(ejb2.echoFromEJB3("Hello EJB2toEJB3"));
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			EJB2Client client = new EJB2Client();
			client.sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end class
