package ch.itenengineering.ejb3toejb2.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.ejb3toejb2.ejb.EJB3Remote;

public class EJB3Client {

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

		// get object reference
		EJB3Remote ejb3 = (EJB3Remote) ctx
				.lookup("ejb/EJB3toEJB2/EJB3Remote");

		// invoke bean method
		System.out.println(ejb3.echo("Hello EJB3"));
		System.out.println(ejb3.echoFromEJB2("Hello EJB3toEJB2"));
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			EJB3Client client = new EJB3Client(); 
			client.sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
