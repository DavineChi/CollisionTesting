package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class ActionBar extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private GameButton backpackButton;
	
	public ActionBar(float x, float y, float width, float height) {
		
		super(x, y, width, height);
		
		backpackButton = new GameButton("B", width - 100.0f, y + 16, 32.0f, 32.0f);
	}
	
	public Object[] getAllComponents() {
		
		Object[] result = new Object[2];
		
		result[0] = this;
		result[1] = backpackButton;
		
		return result;
	}
}
