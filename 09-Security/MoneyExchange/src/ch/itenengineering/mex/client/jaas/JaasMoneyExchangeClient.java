package ch.itenengineering.mex.client.jaas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.security.auth.callback.AppCallbackHandler;

import ch.itenengineering.mex.domain.CurrencyType;
import ch.itenengineering.mex.domain.Rate;
import ch.itenengineering.mex.ejb.MoneyExchangeRemote;

/**
 * Das Beispiel zeigt den Aufruf von Business Methoden einer Stateless Session
 * Bean mit aktivierter Security. <br />
 * 
 * Die Angabe von User/Passwort erfolgt mit Hilfe des JAAS (Java Authorization
 * and Autentication Service) Login. <p />
 * 
 * Für diese Variante sind folgende Konfigurationen auf dem Client notwendig:<br
 * />
 * 
 * a) Erstellung der Datei auth.conf mit folgendem Inhalt: <br />
 * 
 * <code>
 * 	loginContextName {
 * 		org.jboss.security.ClientLoginModule  required;
 * 	}; 		 
 * </code> <p />
 * 
 * b) Start des Client mit folgendem VM Argument: <br />
 * 
 * <code>
 *	-Djava.security.auth.login.config=c:\course\ejb3\labs\MoneyExchange\src\ch\itenengineering\mex\client\jaas\auth.conf
 * </code>
 * 
 * 
 * <p />Hinweis:<br />
 * 
 * Eine gute Einführung zu JAAS findet man zum Beispiel unter:<br />
 * http://www.javaworld.com/javaworld/jw-09-2002/jw-0913-jaas.html?page=1
 */
public class JaasMoneyExchangeClient {

	LoginContext loginContext;

	private void login(String user, String password) throws Exception {

		AppCallbackHandler callbackHandler = new AppCallbackHandler(user,
				password.toCharArray());
		loginContext = new LoginContext("loginContextName", callbackHandler);
		loginContext.login();

	}

	private void logout() throws LoginException {
		loginContext.logout();
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
			JaasMoneyExchangeClient client = new JaasMoneyExchangeClient();

			client.setRates("admin", "verysecret");
			client.getRates("tom", "secret");
			client.getRates("sam", "anothersecret");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
