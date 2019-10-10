package _ATest;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Rectangle;

public class Backpack extends Rectangle implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private boolean displayed;
	
	public Backpack(float x, float y, float width, float height) {
		
		super(x, y, width, height);
		
		displayed = false;
	}
	
	public boolean isDisplayed() {
		
		return displayed;
	}
	
	public void setDisplayed(boolean displayed) {
		
		this.displayed = displayed;
	}
	
	@Override
	public void setInput(Input input) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isAcceptingInput() {
		
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void inputEnded() {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void inputStarted() {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		
		// TODO Auto-generated method stub
	}
}
