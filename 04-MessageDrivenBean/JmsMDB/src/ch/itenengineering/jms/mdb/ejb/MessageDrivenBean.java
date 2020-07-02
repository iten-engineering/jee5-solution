package ch.itenengineering.jms.mdb.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/p2pQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageDrivenBean implements MessageListener {

	public void onMessage(Message message) {
		try {

			// recieve message form queue
			TextMessage tm = (TextMessage) message;
			
			String text = tm.getText();
			Integer id = tm.getIntProperty("id");
			
			System.out.println("MessageDrivenBean received message <" + text + "> with id <" + id + ">" );

		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

} // end of class
