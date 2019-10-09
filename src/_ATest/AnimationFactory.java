package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
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
		Animation result = null;
		
		int ordinal = y;
		int counter = x;
		
		for (int i = 0; i < (frames.length - 1); i++) {
			
			frames[i] = spriteSheet.getSprite(counter, ordinal).getScaledCopy(Constants.SPRITE_SCALE);
			
			counter++;
		}
		
		frames[3] = frames[1];
		result = new Animation(frames, duration);
		
		result.setLooping(true);
		
		return result;
	}
}
