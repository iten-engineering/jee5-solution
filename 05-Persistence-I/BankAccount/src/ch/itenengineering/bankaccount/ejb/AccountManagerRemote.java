package ch.itenengineering.bankaccount.ejb;

import javax.ejb.Remote;

@Remote
public interface AccountManagerRemote {

	public void open(Integer number);

	public void setOwner(String owner);

	public void deposit(Double amount);
	
	public Double getBalance();

	public void withdraw(Double amount) throws Exception;

	public void close();

}
