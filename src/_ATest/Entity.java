package _ATest;

/****************************************************************************************************************
 * Base class for all objects in the game world.
 * 
 * @author Shannon Fisher
 */
public abstract class Entity {
	
	protected String      name = "";
	protected float       x;
	protected float       y;
	protected float       width;
	protected float       height;
	protected BoundingBox boundingBox;
	protected int         hitPoints;
	protected int         maxHitPoints;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getWidth()  { return width;  }
	public float getHeight() { return height; }
	
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
