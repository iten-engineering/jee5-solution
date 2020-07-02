package ch.itenengineering.bmt.company.client;

import java.rmi.ServerException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.bmt.company.domain.Company;
import ch.itenengineering.bmt.company.ejb.CompanyManagerRemote;

public class CompanyClient {

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
	public void testCRUD() throws Exception {

		// init
		Context ctx = getInitialContext();
		CompanyManagerRemote cm = (CompanyManagerRemote) ctx
				.lookup("ejb/BMTCompanyManager");

		// test data
		Company c1 = new Company("BISE NOIRE", "Surfcenter Murten");
		Company c2 = new Company("Vaucher AG", "Sportgeschäft");
		Company c3 = new Company("Intersport International GmbH",
				"Wiederverkauf von Sportartikeln");

		// create
		c1 = cm.persist(c1);
		c2 = cm.persist(c2);
		c3 = cm.persist(c3);

		// read
		Company c = cm.find(c2.getCompanyId());
		System.out.println("\nfind single company:");
		System.out.println(c);

		List l = cm.find("%");
		System.out.println("\nfind all companies:");
		printCompanies(l);

		// update
		c3.setDescription("An- und Wiederverkauf von Sportartikeln");
		cm.merge(c3);

		c = cm.find(c3.getCompanyId());
		System.out.println("\nupdated company:");
		System.out.println(c);

		// delete
		cm.remove(c1.getCompanyId());
		cm.remove(c2.getCompanyId());
		cm.remove(c3.getCompanyId());

		l = cm.find("%");
		System.out.println("\nremove all companies:");
		printCompanies(l);

	}

	/**
	 * test Create, Read, Update and Delete operations
	 * 
	 * @throws ServerException
	 */
	public void testVersion() throws Exception {

		// init
		Context ctx = getInitialContext();
		CompanyManagerRemote cm = (CompanyManagerRemote) ctx
				.lookup("ejb/BMTCompanyManager");

		// insert test data
		Company c1 = new Company("BISE NOIRE", "Surfcenter Murten");
		int id = cm.persist(c1).getCompanyId();

		// test concurrent update
		Company cOrig = cm.find(id);
		System.out.println("\noriginal company:");
		System.out.println(cOrig);

		Company cNew = cm.find(id);
		cNew.setDescription("Murten's Surfcenter Nr 1");
		cNew = cm.merge(cNew);
		System.out.println("\nmodified company:");
		System.out.println(cNew);

		try {
			System.out
					.println("\ntry to modify company with old version number:");
			cm.merge(cOrig);
			System.out.println("oops, modify should fail!");
		} catch (Exception e) {
			System.out.println("expected result: operation failed with "
					+ e.toString());
		}

		// clean up
		cm.remove(id);
	}

	private void printCompanies(List list) {
		if ((list == null) || (list.isEmpty())) {
			System.out.println("no companies found (empty list)");
		} else {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Company element = (Company) iter.next();
				System.out.println(element.toString());
			}
		}

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			CompanyClient c = new CompanyClient();

			c.testCRUD();
			c.testVersion();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
