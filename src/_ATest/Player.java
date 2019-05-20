package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Player extends Actor {
	
	private static final float SPEED_MODIFIER = 0.1f;
	private static final float MULTIPLIER = 10.0f;
	
	private Image currentDirection;
	private Image[] playerImage;
	
	public Player(String name, float x, float y, Direction heading) {
		
		super(name, x, y, heading);
	}
	
	public Player(String name, float x, float y, Direction heading, SpriteSheet spritesheet) {
		
		super(name, x, y, heading);
		
		playerImage = new Image[4];
		
		initSprites(spritesheet);
	}
	
	public Player(String name, float x, float y, float width, float height, Direction heading, SpriteSheet spritesheet) {
		
		super(name, x, y, width, height, heading);
		
		playerImage = new Image[4];
		
		initSprites(spritesheet);
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
		
		return !(newX + width + size >= 180 && newX - size <= 213 &&
				 newY + height + size >= 151 && newY - size <= 182);
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
