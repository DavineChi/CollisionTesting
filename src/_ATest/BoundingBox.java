package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class BoundingBox extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private float posX;
	private float posY;
	private float width;
	private float height;
	
	public BoundingBox(float posX, float posY, float width, float height) {
		
		super(posX, posY, width, height);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(BoundingBox other) {
		
		return (posX + width >= other.posX) &&
               (posX <= other.posX + other.width) &&
               (posY + height >= other.posY) &&
               (posY <= other.posY + other.height);
	}
	
	public boolean willIntersect(BoundingBox other, float deltaFactor) {
		
		return ((posX + width) * deltaFactor >= other.posX) &&
               (posX * deltaFactor <= other.posX + other.width) &&
               ((posY + height) * deltaFactor >= other.posY) &&
               (posY * deltaFactor <= other.posY + other.height);
	}
	
	public float getPosX() {
		
		return posX;
	}

	public void setPosX(float posX) {
		
		this.posX = posX;
	}

	public float getPosY() {
		
		return posY;
	}

	public void setPosY(float posY) {
		
		this.posY = posY;
	}

	public float getWidth() {
		
		return width;
	}

	public void setWidth(float width) {
		
		this.width = width;
	}

	public float getHeight() {
		
		return height;
	}

	public void setHeight(float height) {
		
		this.height = height;
	}

	@Override
	public String toString() {
		
		return "";
	}
}
