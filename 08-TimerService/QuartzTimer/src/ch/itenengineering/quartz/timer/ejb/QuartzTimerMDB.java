package ch.itenengineering.quartz.timer.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Beispiel Message Driven Bean mit Quartz Enterprise Job Scheduler. </p>
 * 
 * JBoss verwendet intern den Quartz Enterprise Job Scheduler. Dieser ist als
 * JCA Resource Adapeter (quartz-ra.rar) integriert. <br/> 
 * Mit Hilfe der MessageDriven Annotation können damit Stateful und Stateless 
 * Session Beanmit dem Cron Scheduler periodisch ausgeführt werden.<br />
 * Nach dem Deploy (oder Neustart des Servers) wird das MDB automatisch gemäss
 * Cron Expression aufgerufen. </p>
 * 
 * <b>Cron Expression:</b><br/> Für die Konfiguration des Scheduler wird die
 * sogenannte Cron Expression verwendet. <br/>Dies ist ein String unterteilt 
 * in die folgenden sieben Bereiche:
 * <ol>
 * 		<li>Sekunden 		[0-59 , - * /]</li>
 * 		<li>Minuten 		[0-59 , - * /]</li>
 * 		<li>Stunden 		[0-23 , - * /]</li>
 * 		<li>Tag des Monats 	[1-31 , - * ? / L W]</li>
 * 		<li>Monat 			[1-12 JAN-DEC , - * /]</li>
 * 		<li>Tag der Woche 	[1-7 SUN-SAT , - * ? / L #]</li>
 * 		<li>Jahr (optional)	[leer 1970-2099 , - * /]</li>
 * </ol>
 * 
 * Beispiele: <br/>
 * <ol>
 * 		<li><b>"0 0/5 * * * ?"</b> alle 5 Minuten</li>
 * 		<li><b>"10 0/5 * * * ?"</b> alle 5 Minuten, 10 Sekunden nach der Minute (9:00:10, 9:05:10, etc.)</li>
 * 		<li><b>"0 0 12 ? * WED"</b> bedeutet jeden Mittwoch um 12.00h</li>
 * 		<li><b>"0 30 10-13 ? * WED,FRI"</b> jeden Mittwoch und Freitag um 10:30, 11:30, 12:30 und 13:30</li>
 * 		<li><b>"0 0/30 8-9 5,20 * ?"</b> jeden halbe Stunde zwischen 8h und 9h (8:00, 8:30, 9:00, 9:30) am 5. und 20. des Monates</li>
 * </ol></p>
 * 
 * <b>Hinweis:</b><br/> 
 * Um das Beispiel ausführen zu können muss die Datei <b>quartz.jar</b> aus dem JBoss Verzeichnis<br/> 
 * in den Klassenpfad aufgenommen werden. Dazu gibt es die vordefinierte User Library jboss-quartz.</p>
 * 
 * @see <a href="http://www.jboss.org/community/docs/DOC-11724">JBoss Community Wiki</a>
 * @see <a href="http://viewvc.jboss.org/cgi-bin/viewvc.cgi/jbossas/tags/JBoss_5_0_1_GA/connector/src/main/org/jboss/resource/adapter/quartz/inflow/">JBoss Source</a>
 * @see <a href="http://www.opensymphony.com/quartz/">Quartz Enterprise Job Scheduler</a>
 * @see <a href="http://www.opensymphony.com/quartz/api/">Quartz Enterprise Job Scheduler API</a>
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "0/5 * * * * ?") 
		})
@ResourceAdapter("quartz-ra.rar")
public class QuartzTimerMDB implements Job {
	
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		System.out.println("execute called");
	
	}

} // end of class
