package ch.itenengineering.timer.client;

import java.rmi.ServerException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.timer.ejb.TimerRemote;

public class TimerClient {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	/**
	 * test Create, Read, Update and Delete operations
	 * 
	 * @throws ServerException
	 */
	public void testTimers() throws Exception {

		Context ctx = getInitialContext();
		TimerRemote timer = (TimerRemote) ctx.lookup("ejb/Timer");

		timer.start("05secTimer", 5000);
		timer.start("10secTimer", 10000);
		timer.start("20secTimer", 20000);
		System.out.println("5sec, 10sec and 20 sec timers started...");

		Thread.sleep(30000);

		timer.stop("10secTimer");
		System.out.println("10sec timer stopped");

		Thread.sleep(30000);

		timer.stop("05secTimer");
		timer.stop("20secTimer");
		System.out.println("5sec and 20sec timer stopped");
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new TimerClient().testTimers();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
