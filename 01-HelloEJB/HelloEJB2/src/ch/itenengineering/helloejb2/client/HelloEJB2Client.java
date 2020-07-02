package ch.itenengineering.helloejb2.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.helloejb2.ejb.HelloEJB2Home;
import ch.itenengineering.helloejb2.ejb.HelloEJB2Remote;

/**
 * stateless session bean sample with ejb 2.x
 */
public class HelloEJB2Client {

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
		Object objref = ctx.lookup("ejb/HelloEJB2");

		HelloEJB2Home home = (HelloEJB2Home) PortableRemoteObject.narrow(
				objref, HelloEJB2Home.class);

		// get remote object (proxy)
		HelloEJB2Remote helloWorld = home.create();

		// invoke bean method
		String echo = helloWorld.echo("Hello EJB2 World");
		System.out.println("\n" + echo);
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new HelloEJB2Client().sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end class
