package ch.itenengineering.transactiontest.ejb;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class AppWithRollbackFalseException extends Exception {

	private static final long serialVersionUID = 1L;

}
