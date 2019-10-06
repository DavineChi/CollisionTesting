package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Potion extends Entity {

	private static final int CAPACITY = 10;
	
	private Image potionSprites;
	private SpriteSheet spritesheet;
	private Image potionImage;
	
	public Potion(String name, float x, float y, float width, float height) {
		
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		initSprite();
	}
	
	private void initSprite() {
		
		try {
			
			potionSprites = new Image("res/potions.png");
			//sprites = sprites.getScaledCopy(1.5f);
			spritesheet = new SpriteSheet(potionSprites, (int)width, (int)height);
			spritesheet.setFilter(Image.FILTER_NEAREST);
			potionImage = spritesheet.getSprite(1, 1).getScaledCopy(2f);
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
	
	public Image getSprite() {
		
		return potionImage;
	}
	
	public void activate(Player player) {
		
		player.addHitPoints(CAPACITY);
	}
}
