package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Player extends Actor {
	
	private static final float SPEED_MODIFIER = 0.10f;
	private static final float MULTIPLIER = 10.0f;
	
	private static final int ANIMATION_SPEED = 180;
	
	private float speedModifier;
	
	private SpriteSheet spriteSheet;
	
	private Image currentDirection;
	private Image[] playerDirections;
	
	private Animation animatePlayerNorthWalking;
	private Animation animatePlayerEastWalking;
	private Animation animatePlayerSouthWalking;
	private Animation animatePlayerWestWalking;
	
	private Animation animatePlayerNorthRunning;
	private Animation animatePlayerEastRunning;
	private Animation animatePlayerSouthRunning;
	private Animation animatePlayerWestRunning;
	
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
		
		super(name, x, y, width * Constants.SPRITE_SCALE, height * Constants.SPRITE_SCALE, heading);
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
	 * @param spriteSheet
	 *   The sprite sheet used for rendering this Player's graphics.
	 */
	public Player(String name, float x, float y, float width, float height, Direction heading, SpriteSheet spriteSheet) {
		
		super(name, x, y, width * Constants.SPRITE_SCALE, height * Constants.SPRITE_SCALE, heading);
		
		this.spriteSheet = spriteSheet;
		
		speedModifier = 0.10f;
		
		playerDirections = new Image[4];
		
		initStaticDirectionSprites();
		
		animatePlayerNorthWalking = AnimationFactory.createAnimation(spriteSheet, 0, 0, 3, ANIMATION_SPEED);
		animatePlayerEastWalking  = AnimationFactory.createAnimation(spriteSheet, 0, 1, 3, ANIMATION_SPEED);
		animatePlayerSouthWalking = AnimationFactory.createAnimation(spriteSheet, 0, 2, 3, ANIMATION_SPEED);
		animatePlayerWestWalking  = AnimationFactory.createAnimation(spriteSheet, 0, 3, 3, ANIMATION_SPEED);
		
		animatePlayerNorthRunning = AnimationFactory.createAnimation(spriteSheet, 3, 0, 3, ANIMATION_SPEED);
		animatePlayerEastRunning  = AnimationFactory.createAnimation(spriteSheet, 3, 1, 3, ANIMATION_SPEED);
		animatePlayerSouthRunning = AnimationFactory.createAnimation(spriteSheet, 3, 2, 3, ANIMATION_SPEED);
		animatePlayerWestRunning  = AnimationFactory.createAnimation(spriteSheet, 3, 3, 3, ANIMATION_SPEED);
	}
	
	public Animation getNorthAnimationWalking() { return animatePlayerNorthWalking; }
	public Animation getEastAnimationWalking()  { return animatePlayerEastWalking;  }
	public Animation getSouthAnimationWalking() { return animatePlayerSouthWalking; }
	public Animation getWestAnimationWalking()  { return animatePlayerWestWalking;  }
	
	public Animation getNorthAnimationRunning() { return animatePlayerNorthRunning; }
	public Animation getEastAnimationRunning()  { return animatePlayerEastRunning;  }
	public Animation getSouthAnimationRunning() { return animatePlayerSouthRunning; }
	public Animation getWestAnimationRunning()  { return animatePlayerWestRunning;  }
	
	// Helper method to initialize the static directional sprites for this Player.
	private void initStaticDirectionSprites() {
		
		int counter = 0;
		
		for (int i = 0; i < 4; i++) {
			
			playerDirections[i] = spriteSheet.getSprite(1, counter).getScaledCopy(Constants.SPRITE_SCALE);
			counter = counter + 1;
		}
	}
	
	public Image getSprite() {
		
		if (currentDirection == null) {
			
			currentDirection = playerDirections[2];
		}
		
		return currentDirection;
	}
	
	public void setDirection(int heading) {
		
		if (heading == 0)   { currentDirection = playerDirections[0]; }
		if (heading == 90)  { currentDirection = playerDirections[1]; }
		if (heading == 180) { currentDirection = playerDirections[2]; }
		if (heading == 270) { currentDirection = playerDirections[3]; }
	}
	
	private boolean validLocation(float width, float height, float newX, float newY) {
		
		float size = speedModifier * MULTIPLIER;
		
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
