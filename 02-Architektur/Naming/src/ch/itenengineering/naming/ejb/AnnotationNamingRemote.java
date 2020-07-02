package ch.itenengineering.naming.ejb;

import javax.ejb.Remote;

@Remote
public interface AnnotationNamingRemote {

	public String echo(String message);

}
