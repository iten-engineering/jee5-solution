package ch.itenengineering.messaging.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SubscriberAFPClient {

	public static void main(String[] args) {

		SubscriberClient subscriber = new SubscriberClient();

		try {

			subscriber.start("Agency='AFP'");

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
