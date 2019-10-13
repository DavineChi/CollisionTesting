package _ATest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/****************************************************************************************************************
 * Factory class for ease of Animation creation and management.
 * 
 * @author Shannon Fisher
 */
public class AnimationFactory {
	
	/************************************************************************************************************
	 * Factory method to create a looping animation from the specified SpriteSheet using horizontal offset.
	 * <p>
	 * 
	 * @param spriteSheet
	 *   The spritesheet used for the requested Animation.
	 * 
	 * @param x
	 *   The X-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param y
	 *   The Y-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param nSprites
	 *   The number of unique/distinct sprites for the Animation.
	 * 
	 * @param duration
	 *   The duration to show each frame.
	 * 
	 * @return The requested Animation.
	 */
	public static Animation createAnimationHorizontal(SpriteSheet spriteSheet, int x, int y, int nSprites, int duration) {
		
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
	
	/************************************************************************************************************
	 * Factory method to create a looping animation from the specified SpriteSheet using vertical offset.
	 * <p>
	 * 
	 * @param spriteSheet
	 *   The spritesheet used for the requested Animation.
	 * 
	 * @param x
	 *   The X-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param y
	 *   The Y-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param nSprites
	 *   The number of unique/distinct sprites for the Animation.
	 * 
	 * @param duration
	 *   The duration to show each frame.
	 * 
	 * @return The requested Animation.
	 */
	public static Animation createAnimationVertical(SpriteSheet spriteSheet, int x, int y, int nSprites, int duration) {
		
		Image[] frames = new Image[nSprites + 1];
		Animation result = null;
		
		for (int i = 0; i < (frames.length - 1); i++) {
			
			frames[i] = spriteSheet.getSprite(x, y).getScaledCopy(Constants.SPRITE_SCALE);
			
			y++;
		}
		
		frames[3] = frames[1];
		result = new Animation(frames, duration);
		
		result.setLooping(true);
		
		return result;
	}
	
	/************************************************************************************************************
	 * Factory method to create a looping idle Player animation from the specified SpriteSheet.
	 * <p>
	 * 
	 * @param spriteSheet
	 *   The spritesheet used for the requested Animation.
	 * 
	 * @param x
	 *   The X-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param y
	 *   The Y-location of the starting sprite on the spritesheet (0-based indexing).
	 * 
	 * @param duration
	 *   The duration to show each frame.
	 * 
	 * @return The requested Animation.
	 */
	public static Animation createAnimationIdlePlayer(SpriteSheet spriteSheet, int x, int y, int duration) {
		
		int additionalFrames = 36;
		int[] baseSequence = { 1, 2, 0, 2, 1 };
		int[] finalSequence = new int[baseSequence.length + additionalFrames];
		
		for (int k = 0; k < baseSequence.length + additionalFrames; k++) {
			
			if (k < baseSequence.length) {
				
				finalSequence[k] = baseSequence[k];
			}
			
			else {
				
				finalSequence[k] = 1;
			}
		}
		
		Image[] frames = new Image[finalSequence.length];
		Animation result = null;
		
		for (int i = 0; i < finalSequence.length; i++) {
			
			int yOffset = finalSequence[i];
			
			frames[i] = spriteSheet.getSprite(x, yOffset).getScaledCopy(Constants.SPRITE_SCALE);
		}
		
		result = new Animation(frames, duration);
		
		result.setLooping(true);
		
		return result;
	}
}
