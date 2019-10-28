package _ATest;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bank extends Entity {
	
	private Image bankSprite;
	private Currency currency;
	
	public Bank(float x, float y, float width, float height) {
		
		this.name = "Bank";
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new BoundingBox(x, y, width, height);
		this.currency = new Currency();
		
		init();
	}
	
	private void init() {
		
		try {
			
			bankSprite = new Image("res/bank.png");
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
}
