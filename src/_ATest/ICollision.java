package _ATest;

import org.newdawn.slick.geom.Shape;

public interface ICollision {

	public abstract boolean intersects(Shape other);
	public abstract boolean willIntersect(Shape other, float deltaFactor);
}
