package _ATest;

public class Player extends Actor {
	
	public Player(String name) {
		
		super(name);
	}
	
	public Player(String name, float playerPosX, float playerPosY) {
		
		super(name, playerPosX, playerPosY);
	}
	
	public Player(String name, float playerPosX, float playerPosY, double heading) {
		
		super(name, playerPosX, playerPosY, heading);
	}
	
	public Player(String name, float playerPosX, float playerPosY, Direction heading) {
		
		super(name, playerPosX, playerPosY, heading);
	}
	
	private boolean validLocation(float width, float height, float newX, float newY) {
		
		return !(width >= 180.0f && newX <= 211.0f && height >= 150.0f && newY <= 181.0f);
	}
	
	@Override
	public boolean moveX(float newX, float newY) {
		
		float width = newX + boundingBox.getWidth();
		float height = newY + boundingBox.getHeight();
		
		if (!validLocation(width, height, newX, newY)) {
			
			return false;
		}
		
		else {
			
			setX(newX);
			return true;
		}
	}

	@Override
	public boolean moveY(float newX, float newY) {
		
		float width = newX + boundingBox.getWidth();
		float height = newY + boundingBox.getHeight();
		
		if (!validLocation(width, height, newX, newY)) {
			
			return false;
		}
		
		else {
			
			setY(newY);
			return true;
		}
	}
}
