package _ATest;

public class GameTimer {
	
	private int time;
	
	public GameTimer() {
		
		this.time = 0;
	}
	
	public int getTime() {
		
		return time;
	}
	
	public void tick(int delta) {
		
		time = time + delta;
	}
	
	public void reset() {
		
		time = 0;
	}
}
