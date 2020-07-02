package ch.itenengineering.helloservlet.ejb;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding="ejb/HelloServlet")
public class HelloServletBean implements HelloServletRemote {

	public String echo(String message) {

		return "echo from HelloServletBean - received message = " + message;
	}

}
