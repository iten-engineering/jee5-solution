package ch.itenengineering.naming.ejb;

import javax.ejb.Remote;

@Remote
public interface DefaultNamingRemote {

	public String echo(String message);
	
}
