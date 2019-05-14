package _ATest;

public interface IDurable {

	public abstract void addHitPoints(int addHitPointsAmount);
	public abstract void setHitPoints(int hitPoints);
	public abstract void removeHitPoints(int removeHitPointsAmount);
	public abstract void addMaxHitPoints(int addMaxHitPointsAmount);
	public abstract void setMaxHitPoints(int maxHitPoints);
}
