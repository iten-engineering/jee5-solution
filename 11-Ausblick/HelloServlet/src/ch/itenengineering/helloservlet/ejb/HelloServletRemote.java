package ch.itenengineering.helloservlet.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloServletRemote {

	public String echo(String message);

}
