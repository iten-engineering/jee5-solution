package ch.itenengineering.mex.client.jndi;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import ch.itenengineering.mex.domain.CurrencyType;
import ch.itenengineering.mex.domain.Rate;
import ch.itenengineering.mex.ejb.MoneyExchangeRemote;

/**
 * Das Beispiel zeigt den Aufruf von Business Methoden einer Stateless Session
 * Bean mit aktivierter Security.<p />
 * 
 * Die Angabe von User/Passwort erfolgt bei der Erstellung des InitialContext
 * mit Hilfe der JndiLoginInitialContextFactory. <p />
 * 
 * Hinweis: <br />
 * 
 * <b><i>Diese Variante funktioniert nur bis und mit JBoss Version 4.x ! <br />
 * Ab der Version 5 wird die einfache Variante mit dem JBoss Security Client
 * oder das JAAS Login empfohlen.</i></b>
 */
public class JndiMoneyExchangeClient {

	private Context getInitialContext(String user, String password)
			throws NamingException, LoginException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.security.jndi.JndiLoginInitialContextFactory");
		env
				.put(Context.URL_PKG_PREFIXES,
						"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		env.put(Context.SECURITY_PRINCIPAL, user);
		env.put(Context.SECURITY_CREDENTIALS, password);

		return new InitialContext(env);
	}

	private MoneyExchangeRemote getRemote(String user, String password)
			throws Exception {

		// get initial context
		Context ctx = getInitialContext(user, password);

		// get object reference
		return (MoneyExchangeRemote) ctx.lookup("ejb/MoneyExchange");
	}

	public void setRates(String user, String password) throws Exception {

		// init
		MoneyExchangeRemote remote = getRemote(user, password);

		// set rates
		List<Rate> rates = new ArrayList<Rate>();
		rates.add(new Rate(CurrencyType.CHF, CurrencyType.USD, 0.83));
		rates.add(new Rate(CurrencyType.CHF, CurrencyType.EUR, 0.65));
		rates.add(new Rate(CurrencyType.EUR, CurrencyType.USD, 1.37));

		remote.setRates(rates);

		// set vip bonus
		remote.setVIPBonus(20);
	}

	public void getRates(String user, String password) throws Exception {

		// init
		MoneyExchangeRemote remote = getRemote(user, password);

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
	}

	public static void main(String[] args) {

		try {
			JndiMoneyExchangeClient client = new JndiMoneyExchangeClient();

			client.setRates("admin", "verysecret");
			client.getRates("tom", "secret");
			client.getRates("sam", "anothersecret");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
