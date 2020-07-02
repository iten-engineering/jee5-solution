package ch.itenengineering.inheritance.i3.client;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.inheritance.i3.domain.Customer;
import ch.itenengineering.inheritance.i3.domain.Employee;
import ch.itenengineering.inheritance.i3.domain.Person;
import ch.itenengineering.inheritance.i3.domain.Employee.EmployeeType;
import ch.itenengineering.inheritance.i3.ejb.ManagerRemoteI3;

public class InheritanceClientI3 {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void i3SubClass() throws Exception {

		// init
		Context ctx = getInitialContext();
		ManagerRemoteI3 manager = (ManagerRemoteI3) ctx.lookup("ejb/ManagerI3");

		// delete existing data
		manager.deleteAll();

		// insert test data
		manager.add(new Person(30, "Thomas", "Iten"));
		manager.add(new Customer(31, "Daniel", "Schmutz", "ZFI"));
		manager.add(new Employee(32, "Willy", "Vollenweider", 1,
				EmployeeType.MANAGER));

		List list = manager.findAll();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Person element = (Person) iter.next();
			System.out.println(element);
		}
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new InheritanceClientI3().i3SubClass();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
