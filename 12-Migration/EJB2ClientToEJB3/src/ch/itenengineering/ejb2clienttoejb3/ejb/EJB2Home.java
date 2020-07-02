package ch.itenengineering.ejb2clienttoejb3.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface EJB2Home extends EJBHome {

	public EJB2Remote create() throws CreateException, RemoteException;

}
