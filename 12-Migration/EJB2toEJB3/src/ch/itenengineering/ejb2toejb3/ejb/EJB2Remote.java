package ch.itenengineering.ejb2toejb3.ejb;

import java.rmi.RemoteException;

public interface EJB2Remote extends java.rmi.Remote, javax.ejb.EJBObject {

	public String echo(String message) throws RemoteException;

	public String echoFromEJB3(String message) throws RemoteException;

}

