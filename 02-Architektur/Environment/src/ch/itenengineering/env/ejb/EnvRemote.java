package ch.itenengineering.env.ejb;

import javax.ejb.Remote;

@Remote
public interface EnvRemote {

	public String getMessages();

}
