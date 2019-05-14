package _ATest;

import org.newdawn.slick.geom.Rectangle;

public abstract class Actor extends Entity implements IDurable {
	
	private Direction heading;
	private boolean alive;
	
	protected int level;
	
	public Actor(String name, float positionX, float positionY, Direction heading) {
		
		this.level = 1;
		this.name = name;
		this.position = new Position(positionX, positionY);
		this.slickBounds = new Rectangle(positionX, positionY, Constants.WIDTH, Constants.HEIGHT);
		this.boundingBox = new BoundingBox(positionX, positionY, Constants.WIDTH, Constants.HEIGHT);
		this.hitPoints = 80;
		this.maxHitPoints = hitPoints;
		this.heading = heading;
		this.alive = true;
	}
 
	public Actor(String name) {

		this(name, 1.0f, 1.0f, new Direction(180.0));
	}
	
	public Actor(String name, float positionX, float positionY) {

		this(name, positionX, positionY, new Direction(180.0));
	}
	
	public Actor(String name, float positionX, float positionY, double heading) {

		this(name, positionX, positionY, new Direction(heading));
	}
	
	public abstract boolean move(float newX, float newY);

	public boolean intersects(BoundingBox other) {
		
		return boundingBox.intersects(other);
	}
	
	public void setHeading(double heading) {
		
		this.heading.setHeading(heading);
	}
	
	public void setX(float xPosition) {
		
		position.setX(xPosition);
		slickBounds.setX(xPosition);
		boundingBox.setX(xPosition);
	}
	
	public void setY(float yPosition) {
		
		position.setY(yPosition);
		slickBounds.setY(yPosition);
		boundingBox.setY(yPosition);
	}
	
	public Direction getHeading() { return heading; }
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Actor's level.
	 * <p>
	 * 
	 * @return This Actor's level.
	 */
	public int getLevel() { return level; }

	/************************************************************************************************************
	 * A modifier method to set this Actor's level.
	 * <p>
	 * 
	 * @param level
	 *        The new level of this Actor.
	 * 
	 * @precondition The parameter level is greater than or equal to 1.
	 * 
	 * @postcondition This Actor's level has been modified.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter level is less than 1.
	 */
	public void setLevel(int level) {

		if (level >= 1) {
			
			this.level = level;
		}

		else {
			
			throw new IllegalArgumentException("Input parameter must not be less than 1: " + level);
		}
	}
	
	/************************************************************************************************************
	 * A modifier method to add a specified amount of hit points to this Actor's available hit points.
	 * <p>
	 * <CODE>public void addHitPoints(int addHitPointsAmount)</CODE>
	 * 
	 * @param addHitPointsAmount
	 *        the amount of hit points to add to this Actor's available hit points
	 * 
	 * @precondition The parameter addHitPointsAmount is greater than or equal to zero.
	 * 
	 * @postcondition This Actor's hit points have been increased by addHitPointsAmount.
	 */
	@Override
	public void addHitPoints(int addHitPointsAmount) {

		if ((hitPoints + addHitPointsAmount) > maxHitPoints) {
			
			hitPoints = maxHitPoints;
		}

		else {
			
			hitPoints = hitPoints + addHitPointsAmount;
		}
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Actor's available hit points.
	 * 
	 * @return This Actor's available hit points.
	 */
	public int getHitPoints() { return hitPoints; }
	
	/************************************************************************************************************
	 * A modifier method to set this Actor's available hit points (health).
	 * <p>
	 * <CODE>public void setHitPoints(int hitPoints)</CODE>
	 * 
	 * @param hitPoints
	 *        the amount of points by which to change this Actor's available hit points
	 * 
	 * @precondition The parameter hitPoints is greater than or equal to zero.
	 * 
	 * @postcondition This Actor's available hit points have been modified.
	 * 
	 * @throws ArithmeticException
	 *         ArithmeticException is thrown if the parameter hitPoints is greater than this Actor's maximum
	 *         available hit points.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter hitPoints is less than zero.
	 * 
	 */
	@Override
	public void setHitPoints(int hitPoints) {

		// qualify the hitPoints parameter, ensuring that the value is non-negative and that
		// it does not cause this Actor's hit points to become greater than the maximum
		if ((hitPoints >= 0) && (hitPoints <= maxHitPoints)) {

			this.hitPoints = hitPoints;

			if (hitPoints > 0) {
				
				alive = true;
			}

			else {
				
				alive = false;
			}
		}

		else {

			if (hitPoints > maxHitPoints) {
				
				throw new ArithmeticException(
						"Hit Points (" + hitPoints + ") cannot exceed maximum: " + maxHitPoints);
			}
			
			else {
				
				throw new IllegalArgumentException("Input parameter must not be less than zero: " + hitPoints);
			}
		}
	}
	
	/************************************************************************************************************
	 * A modifier method to remove a specified amount of hit points from this Actor's available hit points.
	 * <p>
	 * <CODE>public void removeHitPoints(int removeHitPointsAmount)</CODE>
	 * 
	 * @param removeHitPointsAmount
	 *        the amount of hit points to remove from this Actor's available hit points
	 * 
	 * @precondition The parameter removeHitPointsAmount is greater than or equal to zero.
	 * 
	 * @postcondition This Actor's hit points have been decreased by removeHitPointsAmount.
	 *                <p>
	 *                Notes: If removeHitPointsAmount is greater than this Actor's available hit points, then this
	 *                Actor's hit points have been set to zero, and this Actor has died. Also, the
	 *                removeHitPointsAmount parameter can be greater than this Actor's available hit points, and
	 *                future implementation will provide this difference as an overkill value.
	 */
	@Override
	public void removeHitPoints(int removeHitPointsAmount) {

		// still need to determine how to handle overkill cases
		// int overkillAmount;
		if (removeHitPointsAmount >= hitPoints) {
			
			// overkillAmount = removeHitPointsAmount - hitPoints;
			hitPoints = 0;
			// death has occurred!
			alive = false;
		}

		else {
			
			hitPoints = hitPoints - removeHitPointsAmount;
		}
		// return overkillAmount;
	}
	
	/************************************************************************************************************
	 * A modifier method to add a specified amount of hit points to this Actor's maximum available hit points.
	 * <p>
	 * <CODE>public void addMaxHitPoints(int addMaxHitPointsAmount)</CODE>
	 * 
	 * @param addMaxHitPointsAmount
	 *        the amount of additional hit points to add to this Actor's maximum available hit points
	 * 
	 * @precondition The parameter addMaxHitPointsAmount is greater than or equal to zero.
	 * 
	 * @postcondition This Actor's maximum available hit points have been increased by addMaxHitPointsAmount.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter addMaxHitPointsAmount is less than zero.
	 */
	@Override
	public void addMaxHitPoints(int addMaxHitPointsAmount) {

		if (addMaxHitPointsAmount >= 0) {
			
			maxHitPoints = maxHitPoints + addMaxHitPointsAmount;
		}

		else
			throw new IllegalArgumentException(
					"Input parameter must not be less than zero: " + addMaxHitPointsAmount);
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Actor's maximum available hit points.
	 * 
	 * @return This Actor's maximum available hit points.
	 */
	public int getMaxHitPoints() { return maxHitPoints; }
	
	/************************************************************************************************************
	 * A modifier method to set this Actor's maximum available hit points (health).
	 * <p>
	 * <CODE>public void setMaxHitPoints(int maxHitPoints)</CODE>
	 * 
	 * @param maxHitPoints
	 *        the amount of points by which to change this Actor's maximum available hit points
	 * 
	 * @precondition The parameter maxHitPoints is greater than or equal to 1.
	 * 
	 * @postcondition This Actor's maximum available hit points have been modified.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter maxHitPoints is less than 1.
	 */
	@Override
	public void setMaxHitPoints(int maxHitPoints) {

		if (maxHitPoints >= 1) {
			
			this.maxHitPoints = maxHitPoints;
		}

		else {
			
			throw new IllegalArgumentException("Input parameter must not be less than 1: " + maxHitPoints);
		}

		if (hitPoints > maxHitPoints) {
			
			hitPoints = maxHitPoints;
		}
	}
	
	/************************************************************************************************************
	 * A boolean accessor method to return this Actor's life state.
	 * <p>
	 * <CODE>public boolean isAlive()</CODE>
	 * 
	 * @return True if this Actor is still alive, false otherwise.
	 */
	public boolean isAlive() { return alive; }
	
	/************************************************************************************************************
	 * A method to immediately kill this Actor.
	 * <p>
	 * <CODE>public void kill()</CODE>
	 */
	public void kill() {
		
		hitPoints = 0;
		alive = false;
	}
	
	/************************************************************************************************************
	 * A method to revive this Actor's life state.
	 * <p>
	 * <CODE>public void revive()</CODE>
	 */
	public void revive() {
		
		hitPoints = (int)(Math.ceil(maxHitPoints * 0.25));
		alive = true;
	}
}
