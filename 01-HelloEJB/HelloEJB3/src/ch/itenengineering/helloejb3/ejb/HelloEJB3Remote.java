package ch.itenengineering.helloejb3.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloEJB3Remote {
	
	public String helloWorld(String message);

}
