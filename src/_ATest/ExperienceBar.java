package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class ExperienceBar extends CommonBar {
	
	private int experiencePoints;
	
	public ExperienceBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
		
		this.experiencePoints = 0;
	}
	
	public void addPoints(int points) {
		
		experiencePoints = experiencePoints + points;
	}
	
	public int getPoints() {
		
		return experiencePoints;
	}
	
	@Override
	public Rectangle getFrame() {
		
		return frame;
	}
	
	@Override
	public Rectangle getFillBar() {
		
		return fillBar;
	}
	
	@Override
	public void update() {
		
		float fillFactor = (float)(Player.instance().getExperiencePoints()) / (float)(Player.instance().getMaxExperiencePoints());
		
		fillBar.setWidth(width * fillFactor);
	}
}
