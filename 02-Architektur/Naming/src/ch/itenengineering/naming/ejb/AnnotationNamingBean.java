package ch.itenengineering.naming.ejb;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding = "ejb/AnnotationNaming")
public class AnnotationNamingBean implements AnnotationNamingRemote {

	public String echo(String message) {

		return "echo from AnnotationNamingBean - message <" + message
				+ "> received!";
	}

}
