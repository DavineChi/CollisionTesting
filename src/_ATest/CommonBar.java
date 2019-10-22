package _ATest;

import org.newdawn.slick.geom.Rectangle;

/****************************************************************************************************************
 * Base class for all metered and/or progress indicator bars.
 * 
 * @author Shannon Fisher
 */
public abstract class CommonBar {

	private static final float FRAME_THICKNESS = 0.5f;
	
	protected float width;
	protected Rectangle frame;
	protected Rectangle fillBar;
	
	/************************************************************************************************************
	 * Constructor used to create a new EuclideanVector object.
	 * <p>
	 * 
	 * @param xPosition
	 *   The x-coordinate of the left side of this CommonBar.
	 * 
	 * @param yPosition
	 *   The y-coordinate of the top side of this CommonBar.
	 * 
	 * @param width
	 *   The width of this CommonBar.
	 * 
	 * @param height
	 *   The height of this CommonBar.
	 */
	public CommonBar(float xPosition, float yPosition, float width, float height) {
		
		this.frame = new Rectangle((xPosition - FRAME_THICKNESS), (yPosition - FRAME_THICKNESS), (width + (FRAME_THICKNESS * 2)), (height + (FRAME_THICKNESS * 2)));
		this.fillBar = new Rectangle(xPosition, yPosition, width, height);
		this.width = width;
	}
	
	/************************************************************************************************************
	 * Accessor method to retrieve the outer frame Rectangle of this CommonBar.
	 * <p>
	 * 
	 * @return
	 *   This CommonBar's outer frame Rectangle object.
	 */
	public abstract Rectangle getFrame();
	
	/************************************************************************************************************
	 * Accessor method to retrieve the fill Rectangle of this CommonBar.
	 * <p>
	 * 
	 * @return
	 *   This CommonBar's fill Rectangle object.
	 */
	public abstract Rectangle getFillBar();
}
