package ch.itenengineering.naming.ejb;

import javax.ejb.Stateless;

@Stateless
public class DefaultNamingBean implements DefaultNamingRemote {

	public String echo(String message) {

		return "echo from DefaultNamingBean - message <" + message + "> received!";
	}

}
