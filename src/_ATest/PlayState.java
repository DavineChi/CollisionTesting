package _ATest;

import org.newdawn.slick.Color;
import org.newdawn.slick.ControllerListener;
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
	private Image sprites;
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
		
		sprites = new Image("res/Fumiko.png");
		
		sprites = sprites.getScaledCopy(1.5f);
		
		spritesheet = new SpriteSheet(sprites, 36, 48);
		
		player = new Player("Ayrn", 462.0f, 116.0f, Constants.WIDTH, Constants.HEIGHT, new Direction(180.0), spritesheet);
		obstacle = new Player("Water", 64.0f, 384.0f, 192.0f, 192.0f, new Direction(0.0));
		
		map = new GameMap("res/base_test.tmx");
		
		displayMap = true;
		
		potion = new Potion("Potion", 200.0f, 312.0f, 24.0f, 24.0f);
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
		//brush.draw(player.getBoundingBox());
		brush.setColor(Color.gray.darker());
		brush.setColor(Color.white);
		brush.draw(obstacle.getBoundingBox());
		brush.drawString(coords, 20.0f, Constants.SCREEN_HEIGHT - 30.0f);
		brush.drawString(strIntersects, 90.0f, 10.0f);
		brush.drawString("Player X: " + String.valueOf(player.getX()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 45.0f);
		brush.drawString("Player Y: " + String.valueOf(player.getY()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 25.0f);
		
		if (displayMap) {
			
			float x = player.getX() - 250.0f;
			float y = player.getY() - 150.0f;
			float w = 500.0f;
			float h = 300.0f;
			
			//brush.setWorldClip(12.0f, 14.0f, 815.0f, 383.0f);
			brush.translate(-50, -50);
			
			map.render(0, 0);
			//map.render((int)player.getX()*-1, (int)player.getY()*-1);
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
		
		if (input.isKeyPressed(Input.KEY_W) || input.isKeyDown(Input.KEY_W)) {
			
			player.getNorthAnimation().draw(player.getX(), player.getY());
		}
		
		else if (input.isKeyPressed(Input.KEY_D) || input.isKeyDown(Input.KEY_D)) {
			
			player.getEastAnimation().draw(player.getX(), player.getY());
		}
		
		else if (input.isKeyPressed(Input.KEY_S) || input.isKeyDown(Input.KEY_S)) {
			
			player.getSouthAnimation().draw(player.getX(), player.getY());
		}
		
		else if (input.isKeyPressed(Input.KEY_A) || input.isKeyDown(Input.KEY_A)) {
			
			player.getWestAnimation().draw(player.getX(), player.getY());
		}
		
		else {
			
			brush.drawImage(player.getSprite(), player.getX(), player.getY());
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
