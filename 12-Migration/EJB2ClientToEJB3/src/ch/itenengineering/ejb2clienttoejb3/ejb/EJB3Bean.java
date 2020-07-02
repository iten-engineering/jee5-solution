package ch.itenengineering.ejb2clienttoejb3.ejb;

import javax.ejb.RemoteHome;
import javax.ejb.Stateless;

@Stateless
@RemoteHome(EJB2Home.class)
public class EJB3Bean  {

	public String echo(String message) {

		return "echo from EJB3Bean - message <" + message + "> received!";
	}

}
