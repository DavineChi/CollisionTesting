package _ATest;

public class HealthPotion extends Item {

	private int capacity;
	
	public HealthPotion(int capacity, float posX, float posY) {
		
		this.capacity = capacity;
		this.position = new Position(posX, posY);
		this.boundingBox = new BoundingBox(posX, posY, Constants.WIDTH, Constants.HEIGHT);
	}

	public int getCapacity() { return capacity; }
}
