package ch.itenengineering.helloejb2.ejb;

import java.rmi.RemoteException;

/**
 * stateless session bean sample with ejb 2.x
 */
public interface HelloEJB2Remote extends java.rmi.Remote, javax.ejb.EJBObject {

	/**
	 * return echo form client with additional timestamp
	 * 
	 * @param message
	 *            hello from client
	 * @return echo from ejb
	 * @throws RemoteException
	 */
	public String echo(String message) throws RemoteException;

} // end class

