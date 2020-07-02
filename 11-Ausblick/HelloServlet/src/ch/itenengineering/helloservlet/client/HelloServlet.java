package ch.itenengineering.helloservlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.itenengineering.helloservlet.ejb.HelloServletRemote;

/**
 * Einfaches Servlet Beispiel mit Aufruf eines EJBs.
 * Start Servlet mit URL: http://localhost:8080/HelloServlet/
 *
 * Hinweis:
 * 
 * Beim JBoss 5.0.1.GA befinden sich die Klassen aus dem Package javax.servlet 
 * nicht mehr im jboss-javaee.jar Archiv. Damit das Programm läuft, muss daher 
 * die folgende Library vom Server Verzeichnis hinzugefügt werden:
 * C:\AspectOne\Server\jboss-5\common\lib\servlet-api.jar
 * 
 * Mit EJB 3 ist es auch möglich innerhalb von Servlets und JSPs mit der EJB
 * Annotation oder via XML Deskriptor Referenzen auf Beans zu injizieren. Diese
 * Option wird vom JBoss Server erst ab der Version 5.0.0 unterstützt. 
 * 
 * Bei den früheren Versionen kann die Referenz via JNDI Lookup bezogen werden. 
 * Im Gegensatz zu einem Stand-alone Client ist es hier beim Erzeugen des
 * InitialContext nicht notwendig die Provider Url und anderen Angaben mitzugeben.
 * 
 * Der Code dazu sieht folgendermassen aus:
 * 
 * <code>
 *     InitialContext ctx = new InitialContext(); <br />
 *     HelloServletRemote remote = (HelloServletRemote) ctx.lookup("ejb/HelloServlet");
 * </code>
 */
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	HelloServletRemote remote;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter pw = null;

		try {

			resp.setContentType("text/html");
			pw = resp.getWriter();

			pw.write("<html>\n");
			pw.write("<head><title>Hello Servlet</title></head>\n");
			pw.write("<body><h1>");
			// usage of remote reference to ejb
			pw.write(remote.echo("Hello Servlet World!"));
			pw.write("</body></h1>");
			pw.write("</html>\n");
			pw.flush();

		} catch (Exception e) {

			if (pw != null) {
				pw.println("<h1>Servlet failed with exception:</h1>");
				e.printStackTrace(pw);
			} else {
				e.printStackTrace();
			}
		}
	}

} // end of class
