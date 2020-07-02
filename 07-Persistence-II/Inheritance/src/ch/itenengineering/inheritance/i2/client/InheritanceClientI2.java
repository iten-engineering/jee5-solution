package ch.itenengineering.inheritance.i2.client;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.inheritance.i2.domain.Customer;
import ch.itenengineering.inheritance.i2.domain.Employee;
import ch.itenengineering.inheritance.i2.domain.Person;
import ch.itenengineering.inheritance.i2.domain.Employee.EmployeeType;
import ch.itenengineering.inheritance.i2.ejb.ManagerRemoteI2;

/**
 * Einfaches Beispiel mit InheritanceType.TABLE_PER_CLASS Strategy.
 * 
 * Hinweis:
 * 
 * Bei der Selektion der Personen werden die Eigenschaften der Subklassen nicht
 * zurückgelifert. Die Werte sind daher null. Dies ist ein Hibernate Fehler.
 * 
 * Details siehe Hibernate Bug HHH-2920: "Polymorphic association with explicit
 * table_per_class strategy: properties of subclass are not retrieved by
 * queries" (http://opensource.atlassian.com/projects/hibernate/browse/HHH-2920)
 */
public class InheritanceClientI2 {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void i2Class() throws Exception {

		// init
		Context ctx = getInitialContext();
		ManagerRemoteI2 manager = (ManagerRemoteI2) ctx.lookup("ejb/ManagerI2");

		// delete existing data
		manager.deleteAll();

		// insert test data
		manager.add(new Person(20, "Thomas", "Iten"));
		manager.add(new Customer(21, "Daniel", "Schmutz", "ZFI"));
		manager.add(new Employee(22, "Willy", "Vollenweider", 1,
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
			new InheritanceClientI2().i2Class();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
