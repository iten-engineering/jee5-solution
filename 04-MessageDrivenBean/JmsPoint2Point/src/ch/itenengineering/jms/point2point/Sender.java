package ch.itenengineering.jms.point2point;

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

public class Sender {

	//
	// fields
	//
	Connection connection;

	Session session;

	Queue queue;

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

	/**
	 * Initialize the jms message producer
	 * 
	 * @throws NamingException
	 * @throws JMSException
	 */
	public void start() throws NamingException, JMSException {

		Context ctx = getInitialContext();

		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");

		connection = cf.createConnection();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		queue = (Queue) ctx.lookup("queue/p2pQueue");

		messageProducer = session.createProducer(queue);
	}

	public void send(String message) throws NamingException, JMSException {

		TextMessage tm = session.createTextMessage(message);

		messageProducer.send(tm);

		System.out.println("send message <" + message + "> successfully done");
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
	public void stop() {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// ignore
		}
	}

	// ---------------------------------------------------------------------
	// main
	// ---------------------------------------------------------------------

	public static void main(String[] args) {

		Sender sender = new Sender();

		try {
			sender.start();

			sender.send("Hello JMS World");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sender.stop();
		}
	}

} // end of class
