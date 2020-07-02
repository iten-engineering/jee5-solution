package ch.itenengineering.naming.ejb;

public class DescriptorNamingBean implements DescriptorNamingRemote {

	public String echo(String message) {

		return "echo from DescriptorNamingBean - message <" + message
				+ "> received!";
	}

}
