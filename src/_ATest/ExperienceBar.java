package _ATest;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public class ExperienceBar extends CommonBar {
	
	private Line[] measureMarkers;
	private int experiencePoints;
	
	public ExperienceBar(float xPosition, float yPosition, float width, float height) {
		
		super(xPosition, yPosition, width, height);
		
		this.measureMarkers = new Line[19];
		
		float x1 = (width / 20) + xPosition;
		float y1 = yPosition;
		float x2 = x1;
		float y2 = y1 + height;
		
		for (int i = 0; i < measureMarkers.length; i++) {
			
			System.out.println("x1 = " + x1);
			System.out.println("x2 = " + x2);
			
			measureMarkers[i] = new Line(x1, y1, x2, y2);
			
			x1 = x1 + (width / 20);
			x2 = x2 + (width / 20);
		}
		
		this.experiencePoints = 0;
		
		this.fillBar.setWidth(0);
	}
	
	public void addPoints(int points) {
		
		experiencePoints = experiencePoints + points;
	}
	
	public int getPoints() {
		
		return experiencePoints;
	}
	
	public Line[] getMeasureMarkers() {
		
		return measureMarkers;
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
