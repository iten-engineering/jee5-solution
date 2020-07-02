package ch.itenengineering.timer.ejb;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.jboss.ejb3.annotation.RemoteBinding;

@Stateless
@RemoteBinding(jndiBinding = "ejb/Timer")
public class TimerBean implements TimerRemote {

	@Resource
	TimerService timerService;

	public void start(String name, long interval) {
		timerService.createTimer(0, interval, name);
	}

	public void stop(String name) {
		for (Iterator iter = timerService.getTimers().iterator(); iter
				.hasNext();) {
			Timer timer = (Timer) iter.next();
			String timerName = (String) timer.getInfo();

			if (timerName.equals(name)) {
				timer.cancel();
			}
		}
	}

	@Timeout
	public void work(Timer timer) {

		String timerName = (String) timer.getInfo();
		Date nextTimeout = timer.getNextTimeout();

		System.out.println(timerName + "(next timeout = " + nextTimeout + ")");
	}

} // end of class
