package ch.itenengineering.messaging.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SubscriberClient implements javax.jms.MessageListener {

	//
	// fields
	//
	Topic topic;

	Connection connection;

	Session session;

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

	/**
	 * Listen to the news topic for the given message selector.
	 * <p>
	 * Note:<br />
	 * The message selector is a String whose syntax is based on a subset of
	 * the SQL92 conditional expression syntax. For further details and samples
	 * see the javax.jmx.Message Javadoc.
	 * </p>
	 *
	 * @param messageSelector
	 *            filter only desired agency or all (messsageSelector=null)
	 * @throws NamingException
	 */
	public void start(String messageSelector) throws NamingException,
			JMSException {

		Context ctx = getInitialContext();

		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup("ConnectionFactory");

		connection = cf.createConnection();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		topic = (javax.jms.Topic) ctx.lookup("topic/testTopic");

		messageConsumer = session.createConsumer(topic, messageSelector);

		messageConsumer.setMessageListener(this);

		connection.start();
	}

	/**
	 * @see javax.jms.MessageListener()
	 */
	public void onMessage(Message message) {
		try {

			TextMessage tm = (TextMessage) message;

			String agency = tm.getStringProperty("Agency");
			String news = tm.getText();

			System.out.println("Subscriber Client got news from " + agency
					+ ": " + news);

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

		SubscriberClient subscriber = new SubscriberClient();

		try {

			subscriber.start(null);

			System.out
					.println("subscriber starts listening (press <RETURN> to stop)...");
			(new BufferedReader(new InputStreamReader(System.in))).readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			subscriber.stop();
			System.out.println("subscriber stopped!");
		}
	}

} // end of class

