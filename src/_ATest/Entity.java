package _ATest;

public abstract class Entity {
	
	protected String      name = "";
	protected float       x;
	protected float       y;
	protected BoundingBox boundingBox;
	protected int         hitPoints;
	protected int         maxHitPoints;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public float getX() { return x; }
	public float getY() { return y; }
	
	public void setX(float x) {
		
		this.x = x;
		boundingBox.setX(x);
	}
	
	public void setY(float y) {
		
		this.y = y;
		boundingBox.setY(y);
	}
	
	public BoundingBox getBoundingBox() { return boundingBox; }
	public void setBoundingBox(BoundingBox boundingBox) { this.boundingBox = boundingBox; }
}
