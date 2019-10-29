package _ATest;

import org.lwjgl.util.Timer;
import org.newdawn.slick.geom.Rectangle;

public class HealthBar extends CommonBar {
	
	public static enum State { EMPTY, FULL, COMBAT, REGEN };
	
	private static final float REGEN_DELAY = 2.0f;
	
	private static final float STEP_FN_LEVEL_01_TO_04  = 0.2f;
	private static final float STEP_FN_LEVEL_05_TO_07  = 0.065f;
	private static final float STEP_FN_LEVEL_08_TO_09  = 0.025f;
	private static final float STEP_FN_LEVEL_10_AND_UP = 0.005f;
	
	private HealthBar.State state;
	
	private Timer timer;
	private Timer cooldownTimer;
	
	private boolean cooldown;
	
	public HealthBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
		
		state = State.FULL;
		timer = new Timer();
		cooldownTimer = new Timer();
		
		cooldown = false;
	}
	
	private void queryState() {
		
		float fillFactor = (float)(Player.instance().getHitPoints()) / (float)(Player.instance().getMaxHitPoints());
		
		if ((float)(Player.instance().getHitPoints()) < (float)(Player.instance().getMaxHitPoints())) {
			
			state = State.REGEN;
		}
		
		if ((float)(Player.instance().getHitPoints()) == (float)(Player.instance().getMaxHitPoints())) {
			
			state = State.FULL;
		}
		
		if (Player.instance().getState() == Player.State.IN_COMBAT) {
			
			state = State.COMBAT;
		}
		
		fillBar.setWidth(width * fillFactor);
	}
	
	public HealthBar.State getState() {
		
		return state;
	}
	
	public void setState(HealthBar.State state) {
		
		this.state = state;
	}
	
	public int getTime() {
		
		return (int)timer.getTime();
	}
	
	public Timer getTimer() {
		
		return timer;
	}
	
	public Timer getCooldownTimer() {
		
		return cooldownTimer;
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
	
	@Override
	public void update() {
		
		queryState();
		
		if (state == State.REGEN) {
			
			if (cooldownTimer.getTime() < 3) {
				
				cooldown = true;
			}
			
			else {
				
				cooldown = false;
			}
			
			if (!cooldown && timer.getTime() > REGEN_DELAY) {
				
				timer.reset();
				
				int level = Player.instance().getLevel();
				int fillValue = 0;
				
				if (level >= 1 && level <= 4) {
					
					fillValue = (int)(Player.instance().getMaxHitPoints() * STEP_FN_LEVEL_01_TO_04);
				}
				
				else if (level >= 5 && level <= 7) {
					
					fillValue = (int)(Player.instance().getMaxHitPoints() * STEP_FN_LEVEL_05_TO_07);
				}
				
				else if (level >= 8 && level <= 9) {
					
					fillValue = (int)(Player.instance().getMaxHitPoints() * STEP_FN_LEVEL_08_TO_09);
				}
				
				else if (level >= 10) {
					
					fillValue = (int)(Player.instance().getMaxHitPoints() * STEP_FN_LEVEL_10_AND_UP);
					//fillValue = 1;
				}
				
				Player.instance().addHitPoints(fillValue);
				
				float fillFactor = (float)(Player.instance().getHitPoints()) / (float)(Player.instance().getMaxHitPoints());
				
				fillBar.setWidth(width * fillFactor);
			}
		}
	}
}
