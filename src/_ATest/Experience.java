package _ATest;

public class Experience {
	
	private int experiencePoints;
	
	public Experience() {
		
		this.experiencePoints = 0;
	}
	
	public int getExperiencePoints() {
		
		return experiencePoints;
	}
	
	public void addExperiencePoints(int points) {
		
		experiencePoints = experiencePoints + points;
	}
}
