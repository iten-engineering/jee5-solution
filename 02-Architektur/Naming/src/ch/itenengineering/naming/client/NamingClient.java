package ch.itenengineering.naming.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.naming.ejb.AnnotationNamingRemote;
import ch.itenengineering.naming.ejb.DefaultNamingRemote;
import ch.itenengineering.naming.ejb.DescriptorNamingRemote;

public class NamingClient {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void testLookups() throws NamingException {

		// get initial context
		Context ctx = getInitialContext();

		// test lookups
		AnnotationNamingRemote annoNaming = (AnnotationNamingRemote) ctx.lookup("ejb/AnnotationNaming");
		System.out.println( annoNaming.echo("test1")  );

		DefaultNamingRemote defNaming = (DefaultNamingRemote) ctx.lookup("naming/DefaultNamingBean/remote");
		System.out.println( defNaming.echo("test2")  );
		
		DescriptorNamingRemote descNaming = (DescriptorNamingRemote) ctx.lookup("ejb/DescriptorNaming");
		System.out.println( descNaming.echo("test3")  );
	}


	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new NamingClient().testLookups();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

} // end of class
