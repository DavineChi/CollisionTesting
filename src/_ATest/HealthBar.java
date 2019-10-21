package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class HealthBar extends CommonBar {
	
	public HealthBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
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
	
	public void update() {
		
		float fillFactor = (float)(Player.instance().getHitPoints()) / (float)(Player.instance().getMaxHitPoints());
		
		fillBar.setWidth(width * fillFactor);
	}
}
