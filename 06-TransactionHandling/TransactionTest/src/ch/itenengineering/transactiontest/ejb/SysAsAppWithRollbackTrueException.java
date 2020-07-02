package ch.itenengineering.transactiontest.ejb;

import javax.ejb.ApplicationException;
import javax.ejb.EJBException;

@ApplicationException(rollback = true)
public class SysAsAppWithRollbackTrueException extends EJBException {

	private static final long serialVersionUID = 1L;

}
