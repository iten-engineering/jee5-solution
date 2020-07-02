package ch.itenengineering.hellowebservice.client;

import java.net.URL;

import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;

import ch.itenengineering.hellowebservice.ejb.HelloWebServiceEndpoint;

/**
 * Einfaches Web Service Beispiel mit Aufruf eines Stateless Session Bean als
 * Service Endpoint. Unter der folgenden URL können beim JBoss die vorhandenen
 * Web Service angezeigt werden: http://localhost:8080/jbossws/services
 * 
 * Hinweis:
 * 
 * Das Beispiel funktioniert mit JDK 1.5.
 * 
 * Mit den JBoss Versionen 4.2.2, 5.0.x und 5.1.0.GA erhält man bei der Ausführung
 * des Clients mit JDK 1.6 folgende Exception:
 * "java.lang.UnsupportedOperationException: setProperty must be overridden by
 * all subclasses of SOAPMessage"
 * 
 * Weitere Details siehe:
 * http://www.jboss.org/?module=bb&op=viewtopic&start=10&t=98635
 */
public class WebServiceClient {

	private String serviceEndpointAddress = "http://127.0.0.1:8080/hellowebservice-hellowebservice-ejb/HelloWebServiceBean?wsdl";

	private String namespace = "http://ejb.hellowebservice.itenengineering.ch/";

	private String serviceName = "HelloWebService";

	public void sayHello() throws NamingException, Exception {

		// init
		URL url = new URL(serviceEndpointAddress);
		QName qname = new QName(namespace, serviceName);

		ServiceFactory factory = ServiceFactory.newInstance();
		Service service = factory.createService(url, qname);

		// get endpoint
		HelloWebServiceEndpoint helloWebService = (HelloWebServiceEndpoint) service
				.getPort(HelloWebServiceEndpoint.class);

		// invoke service
		System.out.println(helloWebService.echo("Hello Web Service World!"));
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new WebServiceClient().sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
