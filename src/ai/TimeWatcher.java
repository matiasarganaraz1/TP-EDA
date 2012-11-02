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
//		System.out.println("CheckTime");
		if(++currentIteration%WATCH_ITERATIONS==0){
//			System.out.println("100");
			if(System.currentTimeMillis()-initialTime>=maxTime){
				System.out.println("finished");
				timeFinished = true;
			}
		}
	}

	public boolean timeFinished() {
		return timeFinished;
	}

}
