package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Potion extends Entity {

	private static final int CAPACITY = 10;
	
	private Image sprites;
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
			
			sprites = new Image("res/potions.png");
			sprites = sprites.getScaledCopy(1.5f);
			spritesheet = new SpriteSheet(sprites, (int)width, (int)height);
			potionImage = spritesheet.getSprite(1, 1);
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
