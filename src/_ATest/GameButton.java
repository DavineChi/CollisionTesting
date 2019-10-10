package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class GameButton extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public GameButton(String text, float posX, float posY, float width, float height) {
		
		super(posX, posY, width, height);
		
		this.text = text;
	}
	
	public String getText() {
		
		return text;
	}
	
	public void setText(String text) {
		
		this.text = text;
	}
}
