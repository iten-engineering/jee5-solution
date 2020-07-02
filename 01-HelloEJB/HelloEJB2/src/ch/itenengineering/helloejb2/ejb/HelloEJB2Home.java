package ch.itenengineering.helloejb2.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface HelloEJB2Home extends javax.ejb.EJBHome {

	HelloEJB2Remote create() throws CreateException, RemoteException;

} // end class
