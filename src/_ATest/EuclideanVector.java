package _ATest;

public class EuclideanVector {

	private Position point1;
	private Position point2;
	private double heading;
	
	/************************************************************************************************************
	 * Constructor used to create a new EuclideanVector object.
	 * <p>
	 * 
	 * @param point1
	 *   The first coorinate point for this EuclideanVector.
	 * 
	 * @param point2
	 *   The second coorinate point for this EuclideanVector.
	 * 
	 * @param heading
	 *   This EuclideanVector's given heading in degrees.
	 * 
	 * @postcondition
	 *   A new EuclideanVector object has been created with two specified points and a given heading in degrees.
	 */
	public EuclideanVector(Position point1, Position point2, double heading) {
		
		this.point1 = point1;
		this.point2 = point2;
		this.heading = heading;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this EuclideanVector's first coorinate point.
	 * <p>
	 * 
	 * @return
	 *   This EuclideanVector's first coorinate point.
	 */
	public Position getP1() {
		
		return point1;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this EuclideanVector's second coorinate point.
	 * <p>
	 * 
	 * @return
	 *   This EuclideanVector's second coorinate point.
	 */
	public Position getP2() {
		
		return point2;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve this EuclideanVector's heading.
	 * <p>
	 * 
	 * @return
	 *   This EuclideanVector's heading in degrees.
	 */
	public double getHeading() {
		
		return heading;
	}
	
	/************************************************************************************************************
	 * An accessor method to retrieve the distance between the two coordinate points of this EuclideanVector.
	 * <p>
	 * 
	 * @return
	 *   The distance between the two coordinate points of this EuclideanVector.
	 */
	public double getDistance() {
		
		return calculateHypontenuse();
	}
	
	private double calculateHypontenuse() {
		
		double p1x = point1.getX();
		double p2x = point2.getX();
		double p1y = point1.getY();
		double p2y = point2.getY();
		
		return Math.sqrt(Math.pow(Math.abs(p2x - p1x), 2) + Math.pow(Math.abs(p2y - p1y), 2));
	}
}
