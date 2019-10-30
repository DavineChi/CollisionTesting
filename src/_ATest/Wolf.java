package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Wolf extends Actor {
	
	public static enum State { WANDERING, SLEEPING, IN_COMBAT, DEAD };
	
	private static final int ANIMATION_SPEED_WALKING = 180;
	
	private int experienceReward;
	
	private Image wolfSprites;
	private SpriteSheet spriteSheet;
	
	private Image currentDirection;
	private Image[] directions;
	
	private Animation animateWolfNorthWalking;
	private Animation animateWolfEastWalking;
	private Animation animateWolfSouthWalking;
	private Animation animateWolfWestWalking;
	
	private State state;
	
	public Wolf(String name, float x, float y, float width, float height, Direction heading) {
		
		super(name, x, y, width, height, heading);
		
		try {
			
			wolfSprites = new Image("res/drawable-assets/wolf/wolfsheet5.png");
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
		
		this.state = State.WANDERING;
		this.spriteSheet = new SpriteSheet(wolfSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		this.directions = new Image[4];
		
		initStaticDirectionSprites();
		
		animateWolfNorthWalking = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 0, 3, ANIMATION_SPEED_WALKING);
		animateWolfEastWalking  = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 1, 3, ANIMATION_SPEED_WALKING);
		animateWolfSouthWalking = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 2, 3, ANIMATION_SPEED_WALKING);
		animateWolfWestWalking  = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 3, 3, ANIMATION_SPEED_WALKING);
		
		this.setLevel(2);
		
		this.experienceReward = 55;
	}
	
	public Animation getNorthAnimationWalking() { return animateWolfNorthWalking; }
	public Animation getEastAnimationWalking()  { return animateWolfEastWalking;  }
	public Animation getSouthAnimationWalking() { return animateWolfSouthWalking; }
	public Animation getWestAnimationWalking()  { return animateWolfWestWalking;  }
	
	// Helper method to initialize the static directional sprites for this Actor.
	private void initStaticDirectionSprites() {
		
		int counter = 0;
		
		for (int i = 0; i < 4; i++) {
			
			directions[i] = spriteSheet.getSprite(1, counter).getScaledCopy(Constants.SPRITE_SCALE);
			counter = counter + 1;
		}
	}
	
	public void wander() {
		
		
	}
	
	public void attack() {
		
		
	}
	
	public int getExperienceReward() {
		
		return experienceReward;
	}
	
	public State getState() {
		
		return state;
	}
	
	public void setState(Wolf.State state) {
		
		this.state = state;
	}
	
	public Image getSprite() {
		
		if (currentDirection == null) {
			
			currentDirection = directions[2];
		}
		
		return currentDirection;
	}
	
	public void setDirection(int heading) {
		
		this.setHeading(heading);
		
		if (heading == 0)   { currentDirection = directions[0]; }
		if (heading == 90)  { currentDirection = directions[1]; }
		if (heading == 180) { currentDirection = directions[2]; }
		if (heading == 270) { currentDirection = directions[3]; }
	}
	
	
	@Override
	public boolean moveX(float dx, float dy, long delta) {
		
		return false;
	}
	
	@Override
	public boolean moveY(float dx, float dy, long delta) {
		
		return false;
	}
}
