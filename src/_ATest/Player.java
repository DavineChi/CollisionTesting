package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Player extends Actor {
	
	private static final float SPEED_MODIFIER = 0.10f;
	private static final float MULTIPLIER = 10.0f;
	
	private static final int ANIMATION_SPEED = 168;
	
	private enum Carindal {
		
		NORTH,
		EAST,
		SOUTH,
		WEST
	}
	
	private Image currentDirection;
	private Image[] playerImage;
	
	private Image[] northSprites;
	private Image[] eastSprites;
	private Image[] southSprites;
	private Image[] westSprites;
	
	private Animation animatePlayerNorth;
	private Animation animatePlayerEast;
	private Animation animatePlayerSouth;
	private Animation animatePlayerWest;
	
	/************************************************************************************************************
	 * Constructor used to create a new Player.
	 * <p>
	 * 
	 * @param name
	 *   The name for this Player.
	 * 
	 * @param x
	 *   The Cartesian x-coordinate for this Player.
	 * 
	 * @param y
	 *   The Cartesian y-coordinate for this Player.
	 * 
	 * @param width
	 *   The width of this Player.
	 * 
	 * @param height
	 *   The height of this Player.
	 * 
	 * @param heading
	 *   The heading of this Player.
	 */
	public Player(String name, float x, float y, float width, float height, Direction heading) {
		
		super(name, x, y, width, height, heading);
	}
	
	/************************************************************************************************************
	 * Constructor used to create a new Player.
	 * <p>
	 * 
	 * @param name
	 *   The name for this Player.
	 * 
	 * @param x
	 *   The Cartesian x-coordinate for this Player.
	 * 
	 * @param y
	 *   The Cartesian y-coordinate for this Player.
	 * 
	 * @param width
	 *   The width of this Player.
	 * 
	 * @param height
	 *   The height of this Player.
	 * 
	 * @param heading
	 *   The heading of this Player.
	 * 
	 * @param spritesheet
	 *   The spritesheet used for rendering this Player's graphics.
	 */
	public Player(String name, float x, float y, float width, float height, Direction heading, SpriteSheet spritesheet) {
		
		super(name, x, y, width, height, heading);
		
		playerImage = new Image[4];
		
		initSprites(spritesheet);
		initAnimationSprites(spritesheet);
		
		animatePlayerNorth = new Animation(northSprites, ANIMATION_SPEED);
		animatePlayerEast = new Animation(eastSprites, ANIMATION_SPEED);
		animatePlayerSouth = new Animation(southSprites, ANIMATION_SPEED);
		animatePlayerWest = new Animation(westSprites, ANIMATION_SPEED);
		
		animatePlayerNorth.setLooping(true);
		animatePlayerEast.setLooping(true);
		animatePlayerSouth.setLooping(true);
		animatePlayerWest.setLooping(true);
	}
	
	public Animation getNorthAnimation() { return animatePlayerNorth; }
	public Animation getEastAnimation()  { return animatePlayerEast;  }
	public Animation getSouthAnimation() { return animatePlayerSouth; }
	public Animation getWestAnimation()  { return animatePlayerWest;  }
	
	// Helper method to initialize the sprites for this Player.
	private void initSprites(SpriteSheet spritesheet) {
		
		int counter = 0;
		
		for (int i = 0; i < 4; i++) {
			
			playerImage[i] = spritesheet.getSprite(1, counter);
			counter = counter + 1;
		}
	}
	
	// Helper method to initialize the animation sprites for this Player.
	private void initAnimationSprites(SpriteSheet spritesheet) {
		
		Image[] result = null;
		Image[][] imageList = new Image[4][1];
		
		for (Carindal cardinal : Carindal.values()) {

			int ordinal = cardinal.ordinal();
			
			result = new Image[4];
			
			for (int i = 0; i < result.length; i++) {
				
				result[i] = spritesheet.getSprite(i, ordinal);
			}
			
			result[3] = result[1];
			imageList[ordinal] = result;
		}
		
		northSprites = imageList[0];
		eastSprites  = imageList[1];
		southSprites = imageList[2];
		westSprites  = imageList[3];
	}
	
	public Image getSprite() {
		
		if (currentDirection == null) {
			
			currentDirection = playerImage[2];
		}
		
		return currentDirection;
	}
	
	public void setDirection(int heading) {
		
		if (heading == 0)   { currentDirection = playerImage[0]; }
		if (heading == 90)  { currentDirection = playerImage[1]; }
		if (heading == 180) { currentDirection = playerImage[2]; }
		if (heading == 270) { currentDirection = playerImage[3]; }
	}
	
	private boolean validLocation(float width, float height, float newX, float newY) {
		
		float size = SPEED_MODIFIER * MULTIPLIER;
		
		newX = (int)Math.floor(newX);
		newY = (int)Math.floor(newY);
		
		return !(newX + width + size >= 64 && newX - size <= 256 &&
				 newY + height + size >= 384 && newY - size <= 576) &&
				 (newY + size > 40) && (newX - size > 64);
	}
	
	public void move(float dx, float dy, long delta) {
		
		float newX = x + dx;
		float newY = y + dy;
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (validLocation(width, height, newX, newY)) {
			
			this.setX(x + (delta * dx) * SPEED_MODIFIER);
			this.setY(y + (delta * dy) * SPEED_MODIFIER);
		}
	}
	
	@Override
	public boolean moveX(float dx, float dy, long delta) {
		
		float newX = x + dx;
		float newY = y + dy;
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (!validLocation(width, height, newX, newY)) {
			
			return false;
		}
		
		else {
			
			x = x + (delta * dx) * SPEED_MODIFIER;
			return true;
		}
	}

	@Override
	public boolean moveY(float dx, float dy, long delta) {
		
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (!validLocation(width, height, dx, dy)) {
			
			return false;
		}
		
		else {
			
			y = y + (delta * dy) * SPEED_MODIFIER;
			return true;
		}
	}
}
