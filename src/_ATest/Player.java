package _ATest;

import org.lwjgl.util.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.particles.ParticleSystem;

public class Player extends Actor {
	
	public static enum State { NORMAL, RESTED, IN_COMBAT, DEAD };
	
	protected static Player player = null;
	
	private static Timer levelUpParticleTimer;
	private static Sound levelUpSound;
	private static boolean isLevelingUp;
	
	private static final float SPEED_MODIFIER = 0.10f;
	private static final float MULTIPLIER = 10.0f;
	
	private static final int ANIMATION_SPEED_WALKING = 180;
	private static final int ANIMATION_SPEED_RUNNING = 130;
	private static final int ANIMATION_SPEED_IDLE = 80;
	
	private float speedModifier;
	
	private Image playerSprites;
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
	
	private Animation animatePlayerIdle;
	
	private State state;
	
	private Currency currency;
	
	private int experiencePoints;
	private int maxExperiencePoints;
	
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
	protected Player(String name, float x, float y, float width, float height, Direction heading) {
		
		super(name, x, y, width * Constants.SPRITE_SCALE, height * Constants.SPRITE_SCALE, heading);
		
		try {
			
			playerSprites = new Image("res/Fumiko.png");
			levelUpSound = new Sound("res/audio/effects/levelup.ogg");
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
		
		this.state = State.NORMAL;
		
		this.spriteSheet = new SpriteSheet(playerSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		
		speedModifier = 0.10f;
		
		playerDirections = new Image[4];
		
		initStaticDirectionSprites();
		
		animatePlayerNorthWalking = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 0, 3, ANIMATION_SPEED_WALKING);
		animatePlayerEastWalking  = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 1, 3, ANIMATION_SPEED_WALKING);
		animatePlayerSouthWalking = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 2, 3, ANIMATION_SPEED_WALKING);
		animatePlayerWestWalking  = AnimationFactory.createAnimationHorizontal(spriteSheet, 0, 3, 3, ANIMATION_SPEED_WALKING);
		
		animatePlayerNorthRunning = AnimationFactory.createAnimationHorizontal(spriteSheet, 3, 0, 3, ANIMATION_SPEED_RUNNING);
		animatePlayerEastRunning  = AnimationFactory.createAnimationHorizontal(spriteSheet, 3, 1, 3, ANIMATION_SPEED_RUNNING);
		animatePlayerSouthRunning = AnimationFactory.createAnimationHorizontal(spriteSheet, 3, 2, 3, ANIMATION_SPEED_RUNNING);
		animatePlayerWestRunning  = AnimationFactory.createAnimationHorizontal(spriteSheet, 3, 3, 3, ANIMATION_SPEED_RUNNING);
		
		animatePlayerIdle = AnimationFactory.createAnimationIdlePlayer(spriteSheet, 16, 0, ANIMATION_SPEED_IDLE);
		
		currency = new Currency();
		
		experiencePoints = 0;
		maxExperiencePoints = ExperienceSystem.getExperienceForNextLevel(level);
		
		levelUpParticleTimer = new Timer();
		isLevelingUp = false;
	}
	
	public static Player instance() {

		if (player == null) {
			
			player = new Player("Ayrn", 340.0f, 280.0f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, new Direction(180.0));
		}

		return player;
	}
	
	public Animation getNorthAnimationWalking() { return animatePlayerNorthWalking; }
	public Animation getEastAnimationWalking()  { return animatePlayerEastWalking;  }
	public Animation getSouthAnimationWalking() { return animatePlayerSouthWalking; }
	public Animation getWestAnimationWalking()  { return animatePlayerWestWalking;  }
	
	public Animation getNorthAnimationRunning() { return animatePlayerNorthRunning; }
	public Animation getEastAnimationRunning()  { return animatePlayerEastRunning;  }
	public Animation getSouthAnimationRunning() { return animatePlayerSouthRunning; }
	public Animation getWestAnimationRunning()  { return animatePlayerWestRunning;  }
	
	public Animation getPlayerIdleAnimation()   { return animatePlayerIdle;         }
	
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
		
		this.setHeading(heading);
		
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
	
	public State getState() {
		
		return state;
	}
	
	public void setState(Player.State state) {
		
		this.state = state;
	}
	
	/************************************************************************************************************
	 * Modifier method to increment the Player's level by 1.
	 * <p>
	 * 
	 * @postcondition The Player's level has been increased by 1.
	 * 
	 * @throws NullPointerException
	 *         Thrown if there was a problem with the instance() method.
	 * 
	 * @throws IllegalArgumentException
	 *         Thrown if the Player is dead or is level 60.
	 */
	public static void addLevel() {

		if (player == null) {
			
			throw new NullPointerException("Check the instance() method for errors.");
		}

		if (!player.isAlive()) {
			
			throw new IllegalArgumentException("Cannot level up a dead player.");
		}
		
		if ((player.getLevel() + 1) <= Constants.MAXIMUM_PLAYER_LEVEL) {
			
			isLevelingUp = true;
			
			player.setLevel(player.getLevel() + 1);
			levelUpParticleTimer.reset();
			levelUpSound.play(1.0f, 0.375f);
			
			updateAttributes();
		}
	}
	
	private static void updateAttributes() {
		
		player.setMaxHitPoints(HitPoints.calculate(player));
		player.setHitPoints(player.getMaxHitPoints());
		player.setMaxExperiencePoints(ExperienceSystem.getExperienceForNextLevel(Player.instance().getLevel()));
	}
	
	public void addCurrency(int value) {
		
		currency.addValue(value);
	}
	
	public void removeCurrency(int value) {
		
		currency.removeValue(value);
	}
	
	public Currency getCurrency() {
		
		return currency;
	}
	
	public int getExperiencePoints() {
		
		return experiencePoints;
	}
	
	public void addXP(int points) {
		
		if (experiencePoints + points >= maxExperiencePoints) {
			
			int diff = (experiencePoints + points) - maxExperiencePoints;
			
			Player.addLevel();
			
			experiencePoints = diff;
		}
		
		else {
			
			experiencePoints = experiencePoints + points;
		}
	}
	
	public int getMaxExperiencePoints() {
		
		return maxExperiencePoints;
	}
	
	public void setMaxExperiencePoints(int maxExperiencePoints) {
		
		this.maxExperiencePoints = maxExperiencePoints;
	}
	
	public static Timer getLevelUpParticleTimer() {
		
		return levelUpParticleTimer;
	}
	
	public static boolean isLevelingUp() {
		
		return isLevelingUp;
	}
	
	public static void update(ParticleSystem particleSystem) {
		
		// Level-up particle rendering...
		if (isLevelingUp && levelUpParticleTimer.getTime() < 3.0f) {
			
			//float size = 1.0f;
			
			for (int i = 0; i < 20; i++) {
				
				particleSystem.render(player.getX() + (player.getWidth() / 2), player.getY() + player.getHeight());
			}
		}
		
		if (levelUpParticleTimer.getTime() >= 3.0f) {
			
			isLevelingUp = false;
			levelUpParticleTimer.reset();
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
