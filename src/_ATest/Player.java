package _ATest;

public class Player extends Actor {
	
	private static final float SPEED_MODIFIER = 0.15f;
	
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
		
		float size = 0.1f;
		
		newX = (int)Math.floor(newX);
		newY = (int)Math.floor(newY);
		
		if (!(newX + width + size >= 180 && newX - size <= 211 &&
			  newY + height + size >= 150 && newY - size <= 181)) {
			
			return true;
		}
		
		//return !(width >= 180.0f && newX <= 211.0f && height >= 150.0f && newY <= 181.0f);
		return false;
	}
	
	public void move(float dx, float dy, long delta) {
		
		float newX = x + dx;
		float newY = y + dy;
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (validLocation(width, height, newX, newY)) {
			
			this.setX(x + (delta * dx) * SPEED_MODIFIER);
			this.setY(y + (delta * dy) * SPEED_MODIFIER);
		}
	}
	
	@Override
	public boolean moveX(float dx, float dy, long delta) {
		
		float newX = x + dx;
		float newY = y + dy;
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (!validLocation(width, height, newX, newY)) {
			
			return false;
		}
		
		else {
			
			x = x + (delta * dx) * SPEED_MODIFIER;
			return true;
		}
	}

	@Override
	public boolean moveY(float dx, float dy, long delta) {
		
		float width = dx + boundingBox.getWidth();
		float height = dy + boundingBox.getHeight();
		
		if (!validLocation(width, height, dx, dy)) {
			
			return false;
		}
		
		else {
			
			y = y + (delta * dy) * SPEED_MODIFIER;
			return true;
		}
	}
}
