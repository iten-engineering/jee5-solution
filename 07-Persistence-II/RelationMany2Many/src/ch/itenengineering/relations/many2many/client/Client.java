package ch.itenengineering.relations.many2many.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.many2many.domain.Course;
import ch.itenengineering.relations.many2many.domain.Student;
import ch.itenengineering.relations.many2many.ejb.ManagerRemote;

public class Client {

	private Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
			"org.jnp.interfaces.NamingContextFactory");
		env.put(Context.URL_PKG_PREFIXES,
			"org.jboss.naming;org.jnp.interfaces");
		env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	public void test() throws Exception {
		Course c1 = new Course(1, "Mathematik");
		Course c2 = new Course(2, "Englisch");
		Course c3 = new Course(3, "Sport");

		Student s1 = new Student(1, "Michael Reber");
		Student s2 = new Student(2, "Claudia Nacht");
		Student s3 = new Student(3, "Jaqueline Müller");

		//
		// init
		//
		Context ctx = getInitialContext();
		ManagerRemote manager = (ManagerRemote) ctx.lookup("ejb/RMany2ManyManager");

		manager.clear();

		//
		// add courses, students and do bookings
		//
		manager.persist(c1);
		manager.persist(c2);
		manager.persist(c3);

		manager.persist(s1);
		manager.persist(s2);
		manager.persist(s3);

		// book one course for student 1
		manager.book(s1.getId(), c3.getId());

		System.out.println("book one course for student 1:");
		System.out.println(manager.find(Student.class, s1.getId()));

		// book two course for student 2
		manager.book(s2.getId(), c1.getId());
		manager.book(s2.getId(), c2.getId());

		System.out.println("\nbook two course for student 2:");
		System.out.println(manager.find(Student.class, s2.getId()));

		// book three courses for student 3
		manager.book(s3.getId(), c1.getId());
		manager.book(s3.getId(), c2.getId());
		manager.book(s3.getId(), c3.getId());

		System.out.println("\nbook three courses for student 3:");
		System.out.println(manager.find(Student.class, s3.getId()));

		// cancel course two for student 3
		manager.cancel(s3.getId(), c2.getId());

		System.out.println("\ncancel course two for student 3:");
		System.out.println(manager.find(Student.class, s3.getId()));

	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
