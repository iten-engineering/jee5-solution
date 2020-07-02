package ch.itenengineering.relations.r5.client;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r5.domain.R5Address;
import ch.itenengineering.relations.r5.domain.R5Company;
import ch.itenengineering.relations.r5.ejb.R5ManagerRemote;

/**
 * Relationship Beispiel R5 - ManyToOne / OneToMany bidirectional:
 * <ul>
 * <li>Eine Firma hat 0..n Adressen.</li>
 * <li>Umgekehrt kann eine Adresse zu einer Firma gehören (optional).</li>
 * <li>Die bidirektionale Beziehung wird enger angesehen als die
 * unidirektionle, daher erfolgt per default eine Verknüpfung via
 * Primär-/Fremdschlüssel.</li>
 * <li>Mit der Annotation JoinColumn (bei den Adressen) kann der Name der
 * Fremdschüsselspalte angegeben werden</li>
 * <li>Mit dem mappedBy Attribut (bei der Firma) wird angegeben welches Feld in
 * der Address Klasse für die Bezeihung verantwortlich ist.</li>
 * <li>Besitzer der Bezeihung sind die Addressen, da Sie kein mappedBy Attribut
 * besitzen.</li>
 * <li> Auf der Datenbank gibt es je eine Tabelle für die Firmen und eine für
 * die Adressen.</li>
 * </ul>
 */
public class R5Client {

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
		R5ManagerRemote manager = (R5ManagerRemote) ctx.lookup("ejb/R5Manager");

		// delete existing data
		manager.remove(R5Company.class, companyId);

		//
		// create company with two addresses
		//
		List<R5Address> addressList = new ArrayList<R5Address>();
		R5Company company = new R5Company(companyId, "Intersport",
				"Sportartikel Wiederverkäufer", addressList);
		addressList.add(new R5Address("Obere Zollgasse", "75", "3072",
				"Ostermundigen", "Schweiz", company));
		addressList.add(new R5Address("Postfach", null, "3072",
				"Ostermundigen", "Schweiz", company));

		manager.persist(company);

		System.out.println("create company with two addresses:");
		System.out.println(manager.find(R5Company.class, companyId));

		//
		// add third address with relationship to the company
		//
		R5Address address = new R5Address("Obere Zollgasse", "75b", "3072",
				"Ostermundigen", "Schweiz", company);

		manager.persist(address);

		System.out
				.println("\nadd third address with relationship to the company:");
		System.out.println(manager.find(R5Company.class, companyId));

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new R5Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
