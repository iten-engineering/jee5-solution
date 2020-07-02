package ch.itenengineering.interceptor.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.interceptor.ejb.TicketAgencyRemote;

public class ReservationClient {

	TicketAgencyRemote agency;

	public ReservationClient() throws NamingException {

		Context ctx = getInitialContext();

		this.agency = (TicketAgencyRemote) ctx.lookup("ejb/TicketAgency");

	}

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void reserve() throws Exception {

		// init test database
		agency.clear();

		// reserve some events
		book("Wild Markus", "Rolling Stones, Zürich", 2);
		book("Rouvinez Jean Claude", "Hochzeitsnacht im Paradies, Leipzig", 2);
		book("Bolzli Patrick", "YB - Sion, Bern", 4);

		// maximal allowed quantity exceeded
		try {
			book("Nydegger Stefan", "Deep Purple, Lausanne", 10);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// double booking
		try {
			book("Rouvinez Jean Claude", "Hochzeitsnacht im Paradies, Leipzig",
					2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private void book(String customer, String event, int quantity)
			throws Exception {

		int reservationId = agency.book(customer, event, quantity);

		System.out.println(quantity + " Tickets for " + event + " reserved by "
				+ customer + " with id #" + reservationId);

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new ReservationClient().reserve();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
