package ch.itenengineering.messaging.client;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AgencyClient {

	//
	// fields
	//
	Queue queue;

	Connection connection;

	Session session;

	MessageProducer messageProducer;

	// ---------------------------------------------------------------------
	// methods
	// ---------------------------------------------------------------------

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

	private void start() throws NamingException, JMSException {

		Context ctx = getInitialContext();

		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");

		queue = (Queue) ctx.lookup("queue/testQueue");

		connection = cf.createConnection();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		messageProducer = session.createProducer(queue);
	}

	private void send(String agency, String message) throws NamingException,
			JMSException {

		TextMessage tm = session.createTextMessage(message);

		tm.setStringProperty("Agency", agency);

		messageProducer.send(tm);

		System.out.println("Agency Client sent message from " + agency + " : "
				+ message);
	}

	/**
	 * Since a provider typically allocates significant resources outside the
	 * JVM on behalf of a connection, clients should close these resources when
	 * they are not needed. Relying on garbage collection to eventually reclaim
	 * these resources may not be timely enough.
	 * 
	 * There is no need to close the sessions, producers, and consumers of a
	 * closed connection.
	 */
	private void stop() {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// ignore
		}
	}

	public void sendNews() {

		try {
			// init
			start();

			// send news
			while (true) {

				send("dpa",
						"Sven Busch (41) ist neuer Sportchef der Deutschen Presse-Agentur (dpa).");
				Thread.sleep(1500);

				send("AFP", "Un voyage en Afrique...");
				Thread.sleep(1000);

				send("AP",
						"G8 pledges 60 billion dollars to fight disease in Africa");
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// shutdown
			stop();
		}
	}

	public static void main(String[] args) {

		try {

			// run the application
			new AgencyClient().sendNews();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
