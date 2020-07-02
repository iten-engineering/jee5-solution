package ch.itenengineering.timer.ejb;

import javax.ejb.Remote;

@Remote
public interface TimerRemote {

	public void start(String name, long interval);

	public void stop(String name);

} // end
