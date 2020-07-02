package ch.itenengineering.messaging.ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class BrokerBean implements MessageListener {

	@Resource(mappedName = "ConnectionFactory")
	ConnectionFactory connectionFactory;

	@Resource(mappedName = "topic/testTopic")
	Topic topic;

	/**
	 * receive messages from agencyQueue
	 */
	public void onMessage(Message message) {
		try {

			// recieve message form queue
			TextMessage tm = (TextMessage) message;
			String agency = tm.getStringProperty("Agency");
			String news = tm.getText();
			
			System.out.println("received message form " + agency + ": " + news);

			// publish to topic
			publish(tm);
			
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * publish news to topic
	 * 
	 * @param agency
	 *            source of the news message
	 * @param news
	 *            to publish
	 */
	private void publish(TextMessage tm) {
		try {

			// init
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(topic);

			// publish
			sender.send(tm);

			// close connection
			connection.close();

		} catch (Exception e) {
			System.out.println("publish failed with exception " + e.toString());
		}
	}

} // end of class
