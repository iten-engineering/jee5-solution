package ch.itenengineering.ejb2toejb3.ejb;

import javax.ejb.Local;

@Local
public interface EJB3Local {

	public String echo(String message);

}
