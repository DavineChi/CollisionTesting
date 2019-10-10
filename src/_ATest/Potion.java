package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Potion extends Entity {
	
	private int capacity;
	
	private Image potionSprites;
	private SpriteSheet spritesheet;
	private Image potionImage;
	
	public Potion(String name, int capacity, float x, float y, float width, float height) {
		
		this.name = name;
		this.capacity = capacity;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new BoundingBox(x, y, width * Constants.SPRITE_SCALE, height * Constants.SPRITE_SCALE);
		
		init();
	}
	
	private void init() {
		
		try {
			
			potionSprites = new Image("res/potions.png");
			//sprites = sprites.getScaledCopy(1.5f);
			spritesheet = new SpriteSheet(potionSprites, (int)width, (int)height);
			spritesheet.setFilter(Image.FILTER_NEAREST);
			potionImage = spritesheet.getSprite(1, 1).getScaledCopy(2f);
			
			this.width = this.width * 2;
			this.height = this.height * 2;
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
	
	public Image getSprite() {
		
		return potionImage;
	}
	
	public int consume() {
		
		int result = capacity;
		
		capacity = 0;
		
		return result;
	}
}
