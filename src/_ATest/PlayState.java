package _ATest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState {

	private static final float MOVEMENT_FACTOR = 2.5f;
	
	private int id;
	
	private Player player;
	//private Shape obstacle;
	private Player obstacle;
	
//	private float playerPosX;
//	private float playerPosY;
	
	private int mouseX;
	private int mouseY;
	
	private String coords;
	private String strIntersects;
	
	//private Velocity velocity;
	
	private double rise;
	private double run;
	private double angleInDegrees;
	
	private int counter = 0;
	
	public PlayState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
//		playerPosX = 32.0f;
//		playerPosY = 32.0f;
		
		coords = "";
		strIntersects = "FALSE";
		
		player = new Player("Ayrn", 32.0f, 32.0f, new Direction(180.0));
		//obstacle = new Rectangle(180.0f, 150.0f, 192.0f, 96.0f);
		obstacle = new Player("Obstacle", 180.0f, 150.0f, new Direction(180.0));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Input input = container.getInput();
		
		//float deltaFactor = MOVEMENT_FACTOR * 0.0625f * delta;
		
		//float newPlayerX = player.getX();
		//float newPlayerY = player.getY();
		
		if (input.isKeyPressed(Input.KEY_T)) {
			
			teleport();
		}
		
		if (isValidMovementKey(input)) {
			
			float dx = 0;
			float dy = 0;
			
			if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
				
				dy = -1.0f;
				//newPlayerY = player.getY() - deltaFactor * dy;
			}
			
			if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
				
				dx = -1.0f;
				//newPlayerX = player.getX() - deltaFactor * dx;
			}
			
			if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
				
				dy = 1.0f;
				//newPlayerY = player.getY() + deltaFactor * dy;
				
			}
			
			if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
				
				dx = 1.0f;
				//newPlayerX = player.getX() + deltaFactor * dx;
				
			}
			
			if (dx != 0 || dy != 0) {
				
				player.move(dx, dy, delta);
			}
		}
		
		boolean intersects = player.getBoundingBox().intersects(obstacle.getBoundingBox());
		
		if (intersects) {
			
			strIntersects = "Intersects: TRUE";
		}
		
		else if (!intersects) {
			
			strIntersects = "Intersects: FALSE";
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
			
			coords = "x: " + mouseX + ", y: " + mouseY;
		}
		
		counter++;
		
		if (counter > 60) {
			
			counter = 0;
			System.out.println(player.getX() + ", " + player.getY());
		}
		
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
	}
	
	private boolean isValidMovementKey(Input input) {
		
		return input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W) ||
		       input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A) ||
		       input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S) ||
		       input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		brush.setColor(Color.white);
		brush.draw(player.getBoundingBox());
		brush.setColor(Color.gray.darker());
		brush.setColor(Color.white);
		brush.draw(obstacle.getBoundingBox());
		brush.drawString(coords, 20.0f, 420.0f);
		brush.drawString(strIntersects, 90.0f, 10.0f);
		brush.drawString("[Rise: " + String.valueOf(rise), 270.0f, 10.0f);
		brush.drawString("Run: " + String.valueOf(run) + "]", 390.0f, 10.0f);
		brush.drawString("Angle: " + String.valueOf(angleInDegrees), 520.0f, 10.0f);
		brush.drawString("Player X: " + String.valueOf(player.getX()), 653.0f, 405.0f);
		brush.drawString("Player Y: " + String.valueOf(player.getY()), 653.0f, 425.0f);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
	
	private void teleport() {
		
		player.setX(300.0f);
		player.setY(300.0f);
	}
}
