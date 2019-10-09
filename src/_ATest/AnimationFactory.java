package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationFactory {
	
	/************************************************************************************************************
	 * Factory method to create a looping animation from the specified SpriteSheet.
	 * <p>
	 * 
	 * @param spriteSheet
	 *   The spritesheet used for the requested Animation.
	 * 
	 * @param x
	 *   The X-location of the starting sprite on the spritesheet.
	 * 
	 * @param y
	 *   The Y-location of the starting sprite on the spritesheet.
	 * 
	 * @param nSprites
	 *   The number of unique/distinct sprites for the Animation.
	 * 
	 * @param duration
	 *   The duration to show each frame.
	 * 
	 * @return
	 *   The requested Animation.
	 */
	public static Animation createAnimation(SpriteSheet spriteSheet, int x, int y, int nSprites, int duration) {
		
		Image[] frames = new Image[nSprites + 1];
		Animation result = new Animation(frames, duration);
		
		// TODO: implementation
		
		return result;
	}
	
	public static void main(String[] args) {
		
		Image playerSprites = null;
		SpriteSheet spritesheet = null;
		Animation animatePlayerSouthWalking = null;
		
		try {
			
			playerSprites = new Image("res/Fumiko.png");
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
		
		spritesheet = new SpriteSheet(playerSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		animatePlayerSouthWalking = AnimationFactory.createAnimation(spritesheet, 0, 2, 3, 180);
	}
}
