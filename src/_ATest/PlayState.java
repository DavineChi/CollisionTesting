package _ATest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState {

	private static final float MOVEMENT_FACTOR = 2.5f;
	
	private int id;
	
	private Player player;
	private Shape obstacle;
	//private Player obstacle;
	
	private float playerPosX;
	private float playerPosY;
	
	private int mouseX;
	private int mouseY;
	
	private String coords;
	private String strIntersects;
	
	//private Velocity velocity;
	
	private double rise;
	private double run;
	private double angleInDegrees;
	
	public PlayState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		playerPosX = 32.0f;
		playerPosY = 32.0f;
		
		coords = "";
		strIntersects = "FALSE";
		
		player = new Player("Ayrn", playerPosX, playerPosY, new Direction(180.0));
		obstacle = new Rectangle(180.0f, 150.0f, 192.0f, 96.0f);
		//obstacle = new Player("Obstacle", 180.0f, 150.0f, new Direction(180.0));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Input input = container.getInput();
		
		float deltaFactor = MOVEMENT_FACTOR * 0.0625f * delta;
		
		float newPlayerX = player.getSquareBounds().getX();
		float newPlayerY = player.getSquareBounds().getY();
		
//		boolean intersects = player.intersects(obstacle);
//		
//		if (intersects) {
//			
//			strIntersects = "Intersects: TRUE";
//		}
//		
//		else if (!intersects) {
//			
//			strIntersects = "Intersects: FALSE";
//		}
//		
//		if (input.isKeyPressed(Input.KEY_T)) {
//			
//			teleport();
//		}
		
		if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
			
			newPlayerY = playerPosY - deltaFactor;
			
			player.setHeading(0.0);
		}
		
		if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
			
			newPlayerX = playerPosX - deltaFactor;
			
			player.setHeading(270.0);
		}
		
		if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
			
			newPlayerY = playerPosY + deltaFactor;
			
			player.setHeading(180.0);
		}
		
		if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
			
			newPlayerX = playerPosX + deltaFactor;
			
			player.setHeading(90.0);
		}
		
//		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
//			
//			mouseX = input.getMouseX();
//			mouseY = input.getMouseY();
//			
//			coords = "x: " + mouseX + ", y: " + mouseY;
//		}
//		
//		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
//			
//			double currentX = (double)playerPosX;
//			double currentY = (double)playerPosY;
//			
//			mouseX = input.getMouseX();
//			mouseY = input.getMouseY();
//			
//			//Position currentPoint = new Position(currentX, currentY);
//			//Position targetPoint = new Position(mouseX, mouseY);
//			
//			//velocity = new Velocity(deltaFactor, new EuclideanVector(currentPoint, targetPoint, 90.0));
//			
//			coords = "x: " + mouseX + ", y: " + mouseY;
//			
//			rise = mouseY - currentY; // Math.abs(currentY - mouseY);
//			run = mouseX - currentX;  // Math.abs(currentX - mouseX);
//			
//			double slope = rise / run;
//			double arcTan = Math.atan(slope);
//			
//			angleInDegrees = arcTan * (180.0 / Math.PI);
//			
//			playerPosX = input.getMouseX();
//			playerPosY = input.getMouseY();
//			
//			player.setX(playerPosX);
//			player.setY(playerPosY);
//		}
		
		// TODO: Validation pass is here
		// Basically, we set the new player position if validation passes,
		// otherwise we do nothing.
		
		
		// TODO:
		// Notes: After investigation, I believe the root cause of the collision
		// detection problem is that I need to store data about which coordinate
		// sets/subsets are "blocked" and flag them as such. In this demo, this
		// would be the entire extents of the obstacle rectangle. The algorithm
		// must be able to infer that these coordinates cannot be breached by the
		// player. Right now, it does not have this information.
		if (isValidLocation(newPlayerX, newPlayerY)) {
			
			player.move(newPlayerX, newPlayerY);
		}
	}
	
	private boolean isValidLocation(float x, float y) {
		
		boolean result = true;
		
		if (x + Constants.WIDTH >= obstacle.getX()) {
			
			result = false;
		}
		
		if (x <= obstacle.getX() + obstacle.getWidth()) {
			
			result = false;
		}
		
		if (y + Constants.HEIGHT >= obstacle.getY()) {
			
			result = false;
		}
		
		if (y <= obstacle.getY() + obstacle.getHeight()) {
			
			result = false;
		}
		
		return result;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		brush.draw(player.getSquareBounds());
		brush.draw(player.getCircularBounds());
		brush.draw(obstacle);
		brush.drawString(coords, 20.0f, 420.0f);
		brush.drawString(strIntersects, 90.0f, 10.0f);
		brush.drawString("[Rise: " + String.valueOf(rise), 270.0f, 10.0f);
		brush.drawString("Run: " + String.valueOf(run) + "]", 390.0f, 10.0f);
		brush.drawString("Angle: " + String.valueOf(angleInDegrees), 520.0f, 10.0f);
		brush.drawString("Player X: " + String.valueOf(playerPosX), 653.0f, 405.0f);
		brush.drawString("Player Y: " + String.valueOf(playerPosY), 653.0f, 425.0f);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
	
	private void teleport() {
		
		playerPosX = 300.0f;
		playerPosY = 300.0f;
		
		player.setX(300.0f);
		player.setY(300.0f);
	}
}
