package ch.itenengineering.transactiontest.ejb;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AppWithRollbackTrueException extends Exception {

	private static final long serialVersionUID = 1L;

}
