package ch.itenengineering.ejb2toejb3.ejb;

import javax.ejb.Stateless;

@Stateless
public class EJB3Bean implements EJB3Local {

	public String echo(String message) {

		return "echo from EJB3Bean - message <" + message + "> received!";
	}

}
