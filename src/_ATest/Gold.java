package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Gold extends Entity {
	
	private int value;
	
	private Image goldSprites;
	private SpriteSheet spritesheet;
	private Image goldImage;
	
	public Gold(int value, float x, float y, float width, float height) {
		
		this.name = "gold";
		this.value = value;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new BoundingBox(x, y, width * Constants.SPRITE_SCALE, height * Constants.SPRITE_SCALE);
		
		init();
	}
	
	private void init() {
		
		try {
			
			goldSprites = new Image("res/drawable-assets/gold_treasure_icons_16x16/gold.png");
			spritesheet = new SpriteSheet(goldSprites, (int)width, (int)height);
			spritesheet.setFilter(Image.FILTER_NEAREST);
			goldImage = spritesheet.getSprite(3, 0).getScaledCopy(2f);
			
			this.width = this.width * 2;
			this.height = this.height * 2;
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
	
	public Image getSprite() {
		
		return goldImage;
	}
	
	public int getValue() {
		
		return value;
	}
}
