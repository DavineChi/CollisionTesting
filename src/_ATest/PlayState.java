package _ATest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState {
	
	private int id;
	private Player player;
	private Player obstacle;
	private int mouseX;
	private int mouseY;
	private String coords;
	private String strIntersects;
	private Image playerSprites;
	private SpriteSheet spritesheet;
	private GameMap map;
	private boolean displayMap;
	private Potion potion;
	private Input input;
	
	public PlayState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		input = container.getInput();
		
		coords = "";
		strIntersects = "FALSE";
		
		playerSprites = new Image("res/Fumiko.png");
		spritesheet = new SpriteSheet(playerSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		
		spritesheet.setFilter(Image.FILTER_NEAREST);
		
		player = new Player("Ayrn", 462.0f, 116.0f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, new Direction(180.0), spritesheet);
		obstacle = new Player("Water", 64.0f, 384.0f, 192.0f, 192.0f, new Direction(0.0));
		
		map = new GameMap("res/base_test.tmx");
		
		displayMap = true;
		
		potion = new Potion("Potion", 200.0f, 312.0f, 16.0f, 16.0f);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (input.isKeyPressed(Input.KEY_F7)) {
			
			if (displayMap) {
				
				displayMap = false;
			}
			
			else {
				
				displayMap = true;
			}
		}
		
		if (input.isKeyPressed(Input.KEY_T)) {
			
			teleport();
		}
		
		if (isValidMovementKey(input)) {
			
			float dx = 0;
			float dy = 0;
			
			if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
				
				dy = -1.0f;
				
				player.setDirection(0);
			}
			
			if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
				
				dx = -1.0f;
				
				player.setDirection(270);
			}
			
			if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
				
				dy = 1.0f;
				
				player.setDirection(180);
			}
			
			if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
				
				dx = 1.0f;
				
				player.setDirection(90);
			}
			
			if (dx != 0 || dy != 0) {
				
				// If holding the "run" key...
				if (input.isKeyDown(Input.KEY_LSHIFT)) {
					
					player.move(dx, dy, delta * 2);
				}
				
				// Otherwise, just walk.
				else {
					
					player.move(dx, dy, delta);
				}
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
	}
	
	private boolean isValidMovementKey(Input input) {
		
		return input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W) ||
		       input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A) ||
		       input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S) ||
		       input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D) ||
		       input.isKeyPressed(Input.KEY_LSHIFT);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		brush.setColor(Color.white);
		//brush.draw(player.getBoundingBox());
		brush.setColor(Color.gray.darker());
		brush.setColor(Color.white);
		//brush.draw(obstacle.getBoundingBox());
		brush.drawString(coords, 20.0f, Constants.SCREEN_HEIGHT - 30.0f);
		brush.drawString(strIntersects, 90.0f, 10.0f);
		brush.drawString("Player X: " + String.valueOf(player.getX()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 45.0f);
		brush.drawString("Player Y: " + String.valueOf(player.getY()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 25.0f);
		
		if (displayMap) {
			
			//brush.setWorldClip(12.0f, 14.0f, 815.0f, 383.0f);
			
			Camera.instance().translate(player, map, brush, (Constants.SCREEN_WIDTH / 2), (Constants.SCREEN_HEIGHT / 2));
		}
		
		if ((player.getY() + player.getHeight() >= potion.getY() + potion.getHeight()) ||
		    (player.getY() <= potion.getY() + potion.getHeight())) {
			
			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
			drawPlayer(brush);
		}
		
		if (player.getY() + player.getHeight() < potion.getY() + potion.getHeight()) {
			
			drawPlayer(brush);
			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
		}
	}
	
	private void drawPlayer(Graphics brush) {
		
		if (input.isKeyDown(Input.KEY_LSHIFT)) {
			
			if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
				
				player.getNorthAnimationRunning().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
				
				player.getEastAnimationRunning().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
				
				player.getSouthAnimationRunning().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
				
				player.getWestAnimationRunning().draw(player.getX(), player.getY());
			}
			
			else {
				
				brush.drawImage(player.getSprite(), player.getX(), player.getY());
			}
		}
		
		else if (!(input.isKeyDown(Input.KEY_LSHIFT))) {
			
			if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
				
				player.getNorthAnimationWalking().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
				
				player.getEastAnimationWalking().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
				
				player.getSouthAnimationWalking().draw(player.getX(), player.getY());
			}
			
			else if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
				
				player.getWestAnimationWalking().draw(player.getX(), player.getY());
			}
			
			else {
				
				brush.drawImage(player.getSprite(), player.getX(), player.getY());
			}
		}
	}
	
	private void teleport() {
		
		player.setX(300.0f);
		player.setY(300.0f);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
}
