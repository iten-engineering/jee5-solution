package ch.itenengineering.helloejb3.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.helloejb3.ejb.HelloEJB3Remote;

/**
 * Einfaches EJB3 Beispiel mit Aufruf eines Stateless Session Bean via Remote
 * Interface.
 * 
 * Hinweis:
 * 
 * Das Beispiel führt einen direkten cast auf das (via JNDI Lookup)
 * referenzierte Remote Interface aus. Falls der JavaEE Server RMI-IIOP als
 * Transportprotokoll verwendet, ist ein narrow via PortableRemoteObject
 * notwendig (dito EJB2.x).
 * 
 * <code>
 *   Object objref = ctx.lookup("ejb/HelloEJB3");
 *   HelloEJB3Remote helloEJB3 = (HelloEJB3Remote) 
 *     PortableRemoteObject.narrow(objref, HelloEJB3Remote.class);
 * </code>
 */
public class HelloEJB3Client {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void sayHello() throws NamingException {

		// get initial context
		Context ctx = getInitialContext();

		// get object reference
		HelloEJB3Remote helloEJB3 = (HelloEJB3Remote) ctx
				.lookup("ejb/HelloEJB3");

		// invoke bean method
		String echo = helloEJB3.helloWorld("Hello EJB3 World");
		System.out.println("\n" + echo);
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new HelloEJB3Client().sayHello();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

} // end of class
