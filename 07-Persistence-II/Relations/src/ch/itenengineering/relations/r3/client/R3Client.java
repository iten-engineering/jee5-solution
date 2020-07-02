package ch.itenengineering.relations.r3.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r3.domain.R3Address;
import ch.itenengineering.relations.r3.domain.R3Company;
import ch.itenengineering.relations.r3.ejb.R3ManagerRemote;

/**
 * Relationship Beispiel R3 - OneToMany unidirectional:
 * 
 * <ul>
 * <li>Eine Firma kann 0..n Adressen beinhalten. Die Beziehung wird über eine
 * Verbindungstabelle mit einer JoinTable Annotation definiert.</li>
 * <li>Die Adressen wissen nicht, dass Sie zu einer Firma gehören können.</li>
 * <li>Die Firma ist Besitzer der Beziehung.</li>
 * </ul>
 */
public class R3Client {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void test() throws Exception {

		int companyId = 1;

		//
		// init
		//
		Context ctx = getInitialContext();
		R3ManagerRemote manager = (R3ManagerRemote) ctx.lookup("ejb/R3Manager");

		// delete existing data
		manager.remove(R3Company.class, companyId);

		//
		// create company with address list
		//
		List<R3Address> addressList = new ArrayList<R3Address>();
		addressList.add(new R3Address("Obere Zollgasse", "75", "3072",
				"Ostermundigen", "Schweiz"));
		addressList.add(new R3Address("Postfach", null, "3072",
				"Ostermundigen", "Schweiz"));
		addressList.add(new R3Address("Chutzenstrasse", "17", "3007",
				"Bern", "Schweiz"));

		R3Company company = new R3Company(companyId, "Intersport",
				"Sportartikel Wiederverkäufer", addressList);

		// add company
		manager.persist(company);

		System.out.println("create company with address list:");
		System.out.println(manager.find(R3Company.class, companyId));

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new R3Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
