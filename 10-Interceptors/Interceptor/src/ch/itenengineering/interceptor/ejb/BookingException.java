package ch.itenengineering.interceptor.ejb;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BookingException extends Exception {

	private static final long serialVersionUID = 1L;

	public BookingException() {
		super();
	}

	public BookingException(String message) {
		super(message);
	}

}
