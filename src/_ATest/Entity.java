package _ATest;

import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {
	
	protected String	  name = "";
	protected Position	  position;
	protected Rectangle   slickBounds;
	protected BoundingBox boundingBox;
	protected int         hitPoints;
	protected int         maxHitPoints;
	
	public Rectangle getSlickBounds() { return slickBounds; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Position getPosition() { return position; }
	public void setPosition(Position position) { this.position = position; }
	
	public BoundingBox getBoundingBox() { return boundingBox; }
	public void setBoundingBox(BoundingBox boundingBox) { this.boundingBox = boundingBox; }
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Entity's available hit points.
	 * 
	 * @return This Entity's available hit points.
	 */
	public int getHitPoints() { return hitPoints; }
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Entity's maximum available hit points.
	 * 
	 * @return This Entity's maximum available hit points.
	 */
	public int getMaxHitPoints() { return maxHitPoints; }
}
