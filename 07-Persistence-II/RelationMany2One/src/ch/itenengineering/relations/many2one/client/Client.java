package ch.itenengineering.relations.many2one.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.many2one.domain.Address;
import ch.itenengineering.relations.many2one.domain.Company;
import ch.itenengineering.relations.many2one.ejb.ManagerRemote;

public class Client {

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
		ManagerRemote manager = (ManagerRemote) ctx
				.lookup("ejb/RMany2OneManager");

		// delete existing data
		manager.remove(Company.class, companyId);

		//
		// create company with two addresses
		//
		List<Address> addressList = new ArrayList<Address>();
		Company company = new Company(companyId, "Intersport",
				"Sportartikel Wiederverkäufer", addressList);
		addressList.add(new Address("Obere Zollgasse", "75", "3072",
				"Ostermundigen", "Schweiz", company));
		addressList.add(new Address("Postfach", null, "3072", "Ostermundigen",
				"Schweiz", company));

		manager.persist(company);

		System.out.println("create company with two addresses:");
		System.out.println(manager.find(Company.class, companyId));

		// 
		// add third address with relationship to the company
		//
		Address address = new Address("Obere Zollgasse", "75b", "3072",
				"Ostermundigen", "Schweiz", company);

		manager.persist(address);

		System.out
				.println("\nadd third address with relationship to the company:");
		System.out.println(manager.find(Company.class, companyId));

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
