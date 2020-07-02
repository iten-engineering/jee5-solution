package ch.itenengineering.relations.r2.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r2.domain.R2Address;
import ch.itenengineering.relations.r2.domain.R2Company;
import ch.itenengineering.relations.r2.ejb.R2ManagerRemote;

/**
 * Relationship Beispiel R2 - OneToOne bidirectional:
 * <ul>
 * <li>Eine Firma hat immer eine Adresse (zwingend).</li>
 * <li>Eine Adresse kann zu einer Firma gehören (optional).</li>
 * <li>Die Firma wird weiterhin als Besitzer bezeichnet (da sie nicht das
 * mappedBy Attribut besitzt).</li>
 * <li> Auf der Datenbank gibt es je eine Tabelle für die Firmen und eine für
 * die Adressen.</li>
 * <li> Die Bezeihung wird mit einem Foreign Key von der Firmen auf die Adressen
 * Tabelle definiert.</li>
 * </ul>
 */
public class R2Client {

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

		int companyId1 = 1;
		int addressId1 = 1;
		int addressId2 = 2;

		//
		// init
		//
		Context ctx = getInitialContext();
		R2ManagerRemote manager = (R2ManagerRemote) ctx.lookup("ejb/R2Manager");

		// delete existing data (if available)
		manager.remove(R2Company.class, companyId1);
		manager.remove(R2Address.class, addressId2);

		//
		// add company with address
		//
		R2Address a1 = new R2Address(addressId1, "Ryf", "89", "3280", "Murten",
				"Schweiz");
		R2Company c1 = new R2Company(companyId1, "BISE NOIRE",
				"Surfcenter Murten", a1);

		manager.persist(c1);

		System.out.println("add company with an address:");
		System.out.println(manager.find(R2Company.class, companyId1));

		//
		// get address with corresponging company
		//
		System.out.println("\nget address with corresponging company:");
		System.out.println(manager.find(R2Address.class, addressId1));

		//
		// add address without a company
		//
		R2Address a2 = new R2Address(addressId2, "Obere Zollgasse", "75",
				"3072", "Ostermundigen", "Schweiz");

		manager.persist(a2);

		System.out.println("\nadd address without a company:");
		System.out.println(manager.find(R2Address.class, addressId2));

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new R2Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
