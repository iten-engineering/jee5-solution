package ch.itenengineering.interceptor.ejb;

import javax.ejb.Remote;

@Remote
public interface TicketAgencyRemote {

	public void clear();

	public int book(String customer, String event, int quantity)
			throws Exception;

}
