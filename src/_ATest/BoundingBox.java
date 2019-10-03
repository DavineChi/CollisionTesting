package _ATest;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class BoundingBox extends Rectangle implements ICollision {
	
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
	
	@Override
	public boolean intersects(Shape other) {
		
		BoundingBox object = (BoundingBox)other;
		
		return (posX + width >= object.posX) &&
               (posX <= object.posX + object.width) &&
               (posY + height >= object.posY) &&
               (posY <= object.posY + object.height);
	}
	
	@Override
	public boolean willIntersect(Shape other, float deltaFactor) {
		
		BoundingBox object = (BoundingBox)other;
		
		return ((posX + width) * deltaFactor >= object.posX) &&
	               (posX * deltaFactor <= object.posX + object.width) &&
	               ((posY + height) * deltaFactor >= object.posY) &&
	               (posY * deltaFactor <= object.posY + object.height);
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
	
	@Override
	public float getWidth() {
		
		return width;
	}
	
	@Override
	public void setWidth(float width) {
		
		this.width = width;
	}
	@Override
	
	public float getHeight() {
		
		return height;
	}
	
	@Override
	public void setHeight(float height) {
		
		this.height = height;
	}
	
	@Override
	public String toString() {
		
		return "";
	}
}
