package _ATest;

import org.newdawn.slick.geom.Rectangle;

public abstract class CommonBar {

	private static final float FRAME_THICKNESS = 0.5f;
	
	protected float width;
	protected Rectangle frame;
	protected Rectangle fillBar;
	
	public CommonBar(float xPosition, float yPosition, float width, float height) {
		
		this.frame = new Rectangle((xPosition - FRAME_THICKNESS), (yPosition - FRAME_THICKNESS), (width + (FRAME_THICKNESS * 2)), (height + (FRAME_THICKNESS * 2)));
		this.fillBar = new Rectangle(xPosition, yPosition, width, height);
		this.width = width;
	}
	
	public abstract Rectangle getFrame();
	public abstract Rectangle getFillBar();
}
