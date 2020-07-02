package ch.itenengineering.helloejb3.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloEJB3Bean implements HelloEJB3Remote {

	public String helloWorld(String message) {

		return "echo from HelloEJB3Bean - message <" + message + "> received!";
	}
}
