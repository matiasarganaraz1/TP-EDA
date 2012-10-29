package ai;

public class TimeWatcher {
	private final static int WATCH_ITERATIONS=100;

	private boolean timeFinished;
	private int currentIteration;
	private long initialTime;
	private long maxTime;

	public TimeWatcher(long maxTime){
		this.maxTime=maxTime*1000;
		initialTime=System.currentTimeMillis();
	}

	public void checkTime(){
		if(++currentIteration%WATCH_ITERATIONS==0){
			if(System.currentTimeMillis()-initialTime>=maxTime){
				timeFinished = true;
			}
		}
	}

	public boolean timeFinished() {
		return timeFinished;
	}

}
