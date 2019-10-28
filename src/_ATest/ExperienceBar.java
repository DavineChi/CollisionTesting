package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class ExperienceBar extends CommonBar {
	
	private Experience experience;
	
	public ExperienceBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
		
		this.experience = new Experience();
	}
	
	public void addPoints(int points) {
		
		experience.addExperiencePoints(points);
	}
	
	public int getPoints() {
		
		return experience.getExperiencePoints();
	}
	
	@Override
	public Rectangle getFrame() {
		
		return frame;
	}
	
	@Override
	public Rectangle getFillBar() {
		
		return fillBar;
	}
}
