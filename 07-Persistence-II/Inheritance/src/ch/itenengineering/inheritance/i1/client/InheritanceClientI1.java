package ch.itenengineering.inheritance.i1.client;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.inheritance.i1.domain.Customer;
import ch.itenengineering.inheritance.i1.domain.Employee;
import ch.itenengineering.inheritance.i1.domain.Person;
import ch.itenengineering.inheritance.i1.domain.Employee.EmployeeType;
import ch.itenengineering.inheritance.i1.ejb.ManagerRemoteI1;

public class InheritanceClientI1 {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void i1Hierarchy() throws Exception {

		// init
		Context ctx = getInitialContext();
		ManagerRemoteI1 manager = (ManagerRemoteI1) ctx.lookup("ejb/ManagerI1");

		// delete existing data
		manager.deleteAll();

		// insert test data
		manager.add(new Person(10, "Thomas", "Iten"));
		manager.add(new Customer(11, "Daniel", "Schmutz", "ZFI"));
		manager.add(new Employee(12, "Willy", "Vollenweider", 1,
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
			new InheritanceClientI1().i1Hierarchy();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
