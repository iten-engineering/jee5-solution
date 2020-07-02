package ch.itenengineering.mex.client.simple;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;

import ch.itenengineering.mex.domain.CurrencyType;
import ch.itenengineering.mex.domain.Rate;
import ch.itenengineering.mex.ejb.MoneyExchangeRemote;

/**
 * Das Beispiel zeigt den Aufruf von Business Methoden einer Stateless Session
 * Bean mit aktivierter Security.<p />
 * 
 * Die Angabe von User/Passwort erfolgt mit Hilfe des JBoss Security Client
 * (simple login).
 */
public class SimpleMoneyExchangeClient {

	SecurityClient securityClient;

	private void login(String user, String password) throws Exception {
		securityClient = SecurityClientFactory.getSecurityClient();
		securityClient.setSimple(user, password.toCharArray());
		securityClient.login();
	}

	private void logout() {
		securityClient.logout();
	}

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		env
				.put(Context.URL_PKG_PREFIXES,
						"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		InitialContext initialContext = new InitialContext(env);

		return initialContext;
	}

	private MoneyExchangeRemote getRemote() throws Exception {

		// get initial context
		Context ctx = getInitialContext();

		// get object reference
		return (MoneyExchangeRemote) ctx.lookup("ejb/MoneyExchange");
	}

	public void setRates(String user, String password) throws Exception {

		// init
		login(user, password);

		MoneyExchangeRemote remote = getRemote();

		// set rates
		List<Rate> rates = new ArrayList<Rate>();
		rates.add(new Rate(CurrencyType.CHF, CurrencyType.USD, 0.83));
		rates.add(new Rate(CurrencyType.CHF, CurrencyType.EUR, 0.65));
		rates.add(new Rate(CurrencyType.EUR, CurrencyType.USD, 1.37));

		remote.setRates(rates);

		// set vip bonus
		remote.setVIPBonus(20);

		// logout
		logout();
	}

	public void getRates(String user, String password) throws Exception {

		// init
		login(user, password);
		MoneyExchangeRemote remote = getRemote();

		// get rates
		System.out.println("\n" + user + "'s rates:");

		System.out.println("  CHF/EUR = "
				+ remote.getRate(CurrencyType.CHF, CurrencyType.EUR));

		System.out.println("  CHF/USD = "
				+ remote.getRate(CurrencyType.CHF, CurrencyType.USD));

		System.out.println("  USD/CHF = "
				+ remote.getRate(CurrencyType.USD, CurrencyType.CHF));

		// get bonus
		try {
			System.out.println("  bonus is " + remote.getVIPBonus()
					+ " percent");

		} catch (EJBAccessException e) {

			System.out.println("  no bonus (access denied)");
		}

		// logout
		logout();
	}

	public static void main(String[] args) {

		try {
			SimpleMoneyExchangeClient client = new SimpleMoneyExchangeClient();

			client.setRates("admin", "verysecret");
			client.getRates("tom", "secret");
			client.getRates("sam", "anothersecret");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
