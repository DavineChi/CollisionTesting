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
	
	@Override
	public boolean moveX(float newX, float newY) {
		
		float width = newX + boundingBox.getWidth();
		float height = newY + boundingBox.getHeight();
		
		if (width >= 180.0f && newX <= 212.0f && height >= 151.0f && newY <= 182.0f) {
			
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
		
		if (width >= 180.0f && newX <= 212.0f && height >= 151.0f && newY <= 182.0f) {
			
			return false;
		}
		
		else {
			
			setY(newY);
			return true;
		}
	}
}
