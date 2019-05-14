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
	public boolean move(float newX, float newY) {
		
		float xCoord = newX + this.boundingBox.getWidth();
		float yCoord = newY + this.boundingBox.getHeight();
		
		if (xCoord < 180.0f) {
			
			setX(newX);
			setY(newY);
			
			return true;
		}
		
		if (yCoord < 150.0f) {
			
			setX(newX);
			setY(newY);
			
			return true;
		}
		
//		if (xCoord + 32.0f > 212.0f) {
//			
//			setX(newX);
//			setY(newY);
//			
//			return true;
//		}
//		
//		if (yCoord + 32.0f > 182.0f) {
//			
//			setX(newX);
//			setY(newY);
//			
//			return true;
//		}
		
		return false;
	}
}
