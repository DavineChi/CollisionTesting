package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Player extends Actor {
	
	private static final float SPEED_MODIFIER = 0.10f;
	private static final float MULTIPLIER = 10.0f;
	
	private static final int AMINATION_SPEED = 168;
	
	private static final int NORTH = 0;
	private static final int EAST  = 1;
	private static final int SOUTH = 2;
	private static final int WEST  = 3;
	
	private Image currentDirection;
	private Image[] playerImage;
	
	private Animation animatePlayerNorth;
	private Animation animatePlayerEast;
	private Animation animatePlayerSouth;
	private Animation animatePlayerWest;
	
	public Player(String name, float x, float y, Direction heading) {
		
		super(name, x, y, heading);
	}
	
	public Player(String name, float x, float y, Direction heading, SpriteSheet spritesheet) {
		
		super(name, x, y, heading);
		
		playerImage = new Image[4];
		
		initSprites(spritesheet);
	}
	
	public Player(String name, float x, float y, float width, float height, Direction heading) {
		
		super(name, x, y, width, height, heading);
	}
	
	public Player(String name, float x, float y, float width, float height, Direction heading, SpriteSheet spritesheet) {
		
		super(name, x, y, width, height, heading);
		
		playerImage = new Image[4];
		
		initSprites(spritesheet);
		
		Image[] north = initNorthAnimationSprites(spritesheet);
		Image[] east = initEastAnimationSprites(spritesheet);
		Image[] south = initSouthAnimationSprites(spritesheet);
		Image[] west = initWestAnimationSprites(spritesheet);
		
		animatePlayerNorth = new Animation(north, AMINATION_SPEED);
		animatePlayerEast = new Animation(east, AMINATION_SPEED);
		animatePlayerSouth = new Animation(south, AMINATION_SPEED);
		animatePlayerWest = new Animation(west, AMINATION_SPEED);
		
		animatePlayerNorth.setLooping(true);
		animatePlayerEast.setLooping(true);
		animatePlayerSouth.setLooping(true);
		animatePlayerWest.setLooping(true);
	}
	
	public Animation getNorthAnimation() { return animatePlayerNorth; }
	public Animation getEastAnimation()  { return animatePlayerEast;  }
	public Animation getSouthAnimation() { return animatePlayerSouth; }
	public Animation getWestAnimation()  { return animatePlayerWest;  }
	
	private Image[] initNorthAnimationSprites(SpriteSheet spritesheet) {
		
		Image[] result = new Image[4];
		
		int counter = 0;
		
		for (int i = 0; i < result.length; i++) {
			
			result[i] = spritesheet.getSprite(counter, NORTH);
			
			counter++;
		}
		
		result[3] = result[1];
		
		return result;
	}
	
	private Image[] initEastAnimationSprites(SpriteSheet spritesheet) {
		
		Image[] result = new Image[4];
		
		int counter = 0;
		
		for (int i = 0; i < result.length; i++) {
			
			result[i] = spritesheet.getSprite(counter, EAST);
			
			counter++;
		}
		
		result[3] = result[1];
		
		return result;
	}
	
	private Image[] initSouthAnimationSprites(SpriteSheet spritesheet) {
		
		Image[] result = new Image[4];
		
		int counter = 0;
		
		for (int i = 0; i < result.length; i++) {
			
			result[i] = spritesheet.getSprite(counter, SOUTH);
			
			counter++;
		}
		
		result[3] = result[1];
		
		return result;
	}
	
	private Image[] initWestAnimationSprites(SpriteSheet spritesheet) {
		
		Image[] result = new Image[4];
		
		int counter = 0;
		
		for (int i = 0; i < result.length; i++) {
			
			result[i] = spritesheet.getSprite(counter, WEST);
			
			counter++;
		}
		
		result[3] = result[1];
		
		return result;
	}
	
	private void initSprites(SpriteSheet spritesheet) {
		
		int counter = 0;
		
		for (int i = 0; i < 4; i++) {
			
			playerImage[i] = spritesheet.getSprite(1, counter);
			counter = counter + 1;
		}
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
