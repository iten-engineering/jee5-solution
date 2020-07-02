package ch.itenengineering.jms.point2point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver implements javax.jms.MessageListener {

	//
	// fields
	//
	Connection connection;

	Session session;

	Queue queue;

	MessageConsumer messageConsumer;

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

	public void start() throws NamingException, JMSException {

		Context ctx = getInitialContext();

		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");

		connection = cf.createConnection();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		queue = (Queue) ctx.lookup("queue/p2pQueue");

		messageConsumer = session.createConsumer(queue);

		messageConsumer.setMessageListener(this);

		connection.start();
	}

	/**
	 * @see javax.jms.MessageListener()
	 */
	public void onMessage(Message message) {
		try {

			TextMessage tm = (TextMessage) message;

			System.out.println("> message <" + tm.getText() + "> received");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

		Receiver receiver = new Receiver();

		try {

			receiver.start();

			System.out
					.println("receiver starts listening (press <RETURN> to stop)...");
			(new BufferedReader(new InputStreamReader(System.in))).readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			receiver.stop();
			System.out.println("receiver stopped!");
		}
	}

} // end of class
