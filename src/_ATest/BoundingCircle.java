package _ATest;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class BoundingCircle extends Circle implements ICollision {
	
	private static final long serialVersionUID = 1L;
	
	private float posX;
	private float posY;
	private float radius;
	
	public BoundingCircle(float posX, float posY, float radius) {
		
		super(posX, posY, radius);
		this.posX = posX;
		this.posY = posY;
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
	public float getRadius() {
		
		return radius;
	}
	
	@Override
	public void setRadius(float radius) {
		
		this.radius = radius;
	}
	
	@Override
	public boolean intersects(Shape other) {
		
		// TODO: implementation
		return false;
	}
	
	@Override
	public boolean willIntersect(Shape other, float deltaFactor) {

		// TODO: implementation
		return false;
	}
}
