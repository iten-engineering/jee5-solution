package ch.itenengineering.env.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.env.ejb.EnvRemote;

public class EnvClient {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void testEnvMessages() throws NamingException {

		// init
		Context ctx = getInitialContext();
		EnvRemote env = (EnvRemote) ctx.lookup("ejb/Env");

		// get environment entries (messages)
		System.out.println(env.getMessages());

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new EnvClient().testEnvMessages();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

} // end of class
