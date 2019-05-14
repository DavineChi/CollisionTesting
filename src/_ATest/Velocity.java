package _ATest;

public class Velocity {

	private EuclideanVector vector;
	private double speed;
	
	/************************************************************************************************************
	 * Constructor used to create a new Velocity object.
	 * <p>
	 * 
	 * @param speed
	 *   This Velocity's given speed.
	 * 
	 * @param vector
	 *   This Velocity's given vector.
	 * 
	 * @postcondition
	 *   A new Velocity object has been created with a specified speed and vector.
	 */
	public Velocity(double speed, EuclideanVector vector) {
		
		this.speed = speed;
		this.vector = vector;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Velocity's speed.
	 * <p>
	 * 
	 * @return
	 *   This Velocity's speed.
	 */
	public double getSpeed() {
		
		return speed;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this Velocity's vector.
	 * <p>
	 * 
	 * @return
	 *   This Velocity's vector.
	 */
	public EuclideanVector getVector() {
		
		return vector;
	}
}
