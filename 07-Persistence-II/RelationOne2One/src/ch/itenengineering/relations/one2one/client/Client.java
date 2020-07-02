package ch.itenengineering.relations.one2one.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.one2one.domain.Address;
import ch.itenengineering.relations.one2one.domain.Company;
import ch.itenengineering.relations.one2one.ejb.ManagerRemote;

public class Client {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env
				.put(Context.URL_PKG_PREFIXES,
						"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void test() throws Exception {

		int companyId1 = 1;
		int companyId2 = 2;
		int companyId3 = 3;

		//
		// init
		//
		Context ctx = getInitialContext();
		ManagerRemote manager = (ManagerRemote) ctx
				.lookup("ejb/ROne2OneManager");

		// delete existing data (if available)
		manager.remove(Company.class, companyId1);
		manager.remove(Company.class, companyId2);
		manager.remove(Company.class, companyId3);

		//
		// add company with an address
		//
		Address a1 = new Address("Ryf", "89", "3280", "Murten", "Schweiz");
		Company c1 = new Company(companyId1, "BISE NOIRE", "Surfcenter Murten",
				a1);

		c1 = (Company) manager.persist(c1);

		System.out.println("add company with an address:");
		System.out.println(c1);

		// modifiy address an save changes
		c1.getAddress().setStreetNo("98a");
		manager.merge(c1);

		System.out.println("\nmodify company's address (street no):");
		System.out.println(manager.find(Company.class, companyId1));

		//
		// add company without an address
		//
		Company c2 = new Company(companyId2, "Intersport",
				"Sportartikel Wiederverkäufer", null);

		manager.persist(c2);

		System.out.println("\n\nadd company without an address:");
		System.out.println(manager.find(Company.class, companyId2));

		//
		// add company with an address and remove address
		//
		Address a3 = new Address("Markgasse", "50", "3000", "Bern", "Schweiz");
		Company c3 = new Company(companyId3, "Vaucher", "Vaucher Sports Bern",
				a3);

		c3 = (Company) manager.persist(c3);

		System.out.println("\n\nadd company with an address:");
		System.out.println(c3);

		// remove address an save changes
		c3.setAddress(null);
		manager.merge(c3);

		System.out.println("\nremove company's address:");
		System.out.println(manager.find(Company.class, companyId3));
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
