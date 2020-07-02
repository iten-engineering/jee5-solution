package ch.itenengineering.relations.r7.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r7.domain.R7Course;
import ch.itenengineering.relations.r7.domain.R7Student;
import ch.itenengineering.relations.r7.ejb.R7ManagerRemote;

public class R7Client {

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
		R7Course c1 = new R7Course(1, "Mathematik");
		R7Course c2 = new R7Course(2, "Englisch");
		R7Course c3 = new R7Course(3, "Sport");

		R7Student s1 = new R7Student(1, "Michael Reber");
		R7Student s2 = new R7Student(2, "Claudia Nacht");
		R7Student s3 = new R7Student(3, "Jaqueline Müller");

		//
		// init
		//
		Context ctx = getInitialContext();
		R7ManagerRemote manager = (R7ManagerRemote) ctx.lookup("ejb/R7Manager");

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
		manager.book(s1.getId(), c1.getId());

		System.out.println("book one course for student 1:");
		System.out.println(manager.find(R7Student.class, s1.getId()));

		// book two course for student 2
		manager.book(s2.getId(), c1.getId());
		manager.book(s2.getId(), c2.getId());

		System.out.println("\nbook two course for student 2:");
		System.out.println(manager.find(R7Student.class, s2.getId()));

		// book three courses for student 3
		manager.book(s3.getId(), c1.getId());
		manager.book(s3.getId(), c2.getId());
		manager.book(s3.getId(), c3.getId());

		System.out.println("\nbook three courses for student 3:");
		System.out.println(manager.find(R7Student.class, s3.getId()));

		//
		// show all courses and their bookings (i.e. the booked students)
		//
		System.out.println("\n\nshow all courses and their bookings (i.e. the booked students):");
		System.out.println(manager.find(R7Course.class, c1.getId()));
		System.out.println(manager.find(R7Course.class, c2.getId()));
		System.out.println(manager.find(R7Course.class, c3.getId()));
		
	}

	public static void main(String[] args) {

		try {
			// configure log4j (used by jboss classes)
			// BasicConfigurator.configure();

			// run the application
			new R7Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
