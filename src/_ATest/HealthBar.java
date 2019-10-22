package _ATest;

import org.lwjgl.util.Timer;
import org.newdawn.slick.geom.Rectangle;

public class HealthBar extends CommonBar {
	
	public static enum State { EMPTY, FULL, COMBAT, REGEN };
	
	private HealthBar.State state;
	
	private Timer timer;
	
	public HealthBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
		
		state = State.FULL;
		timer = new Timer();
	}
	
	@Override
	public Rectangle getFrame() {
		
		return frame;
	}
	
	@Override
	public Rectangle getFillBar() {
		
		update();
		
		return fillBar;
	}
	
	private void queryFillLevel() {
		
		if ((float)(Player.instance().getHitPoints()) < (float)(Player.instance().getMaxHitPoints())) {
			
			state = State.REGEN;
		}
	}
	
	public HealthBar.State getState() {
		
		return state;
	}
	
	public void setState(HealthBar.State state) {
		
		this.state = state;
	}
	
	public void update() {
		
		queryFillLevel();
		
		if (state == State.REGEN) {
			
			Timer.tick();
			
			if (timer.getTime() / 1000 > 3) {
				
				timer.reset();
				
				float fillFactor = (float)(Player.instance().getHitPoints()) / (float)(Player.instance().getMaxHitPoints());
				
				fillBar.setWidth(width * fillFactor);
			}
		}
	}
}
