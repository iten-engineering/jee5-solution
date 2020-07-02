package ch.itenengineering.ejb2clienttoejb3.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.ejb2clienttoejb3.ejb.EJB2Home;
import ch.itenengineering.ejb2clienttoejb3.ejb.EJB2Remote;

/**
 * Migrationsbeispiel mit EJB 2.x Client und EJB3 Bean. Dem Client wird ein Home
 * und Remote Interface nach EJB 2.x zur Verfügung gestellt. Das Bean wird
 * gemäss EJB 3 erstellt mit folgenden Anpassugen:
 * 
 * <ol>
 * <li>Die Bean Klasse wird mit @RemoteHome(EJB2Home.class) annotiert, um dem
 * Container mitzuteilen, dass das Bean über ein Home Interface verfügt.</li>
 * <li>Das Bean implementiert das Remote Interface nicht, da es sonst auch alle
 * Callback Methoden gemäss EJB 2.x zur Verfügung stellen müsste.</li>
 * </ol>
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
		Object objref = ctx.lookup("ejb/EJB3AsEJB2/home");

		EJB2Home home = (EJB2Home) PortableRemoteObject.narrow(objref,
				EJB2Home.class);

		// get remote object (proxy)
		EJB2Remote remote = home.create();

		// invoke bean method
		System.out.println(remote.echo("Hello EJB3 Bean"));
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
