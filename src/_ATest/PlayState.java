package _ATest;

import java.util.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
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
	private SpriteSheet playerSpriteSheet;
	private GameMap map;
	private boolean displayMap;
	private Potion potion;
	private Gold gold;
	private Input input;
	
	private Backpack backpack;
	private ActionBar actionBar;
	private HealthBar healthBar;
	
	private Music music;
	
	public PlayState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		
		music.play(1.0f, 0.3f);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
		music.stop();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		music = new Music(Constants.MUSIC_PATH + "dayforest01.ogg");
		healthBar = new HealthBar(70.0f, 70.0f, 120.0f, 10.0f);
		
		input = container.getInput();
		
		coords = "";
		strIntersects = "FALSE";
		
		playerSprites = new Image("res/Fumiko.png");
		playerSpriteSheet = new SpriteSheet(playerSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		
		playerSpriteSheet.setFilter(Image.FILTER_NEAREST);
		
		//player = new Player("Ayrn", 340.0f, 280.0f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, new Direction(180.0), playerSpriteSheet);
		player = Player.instance();
		obstacle = new Player("Water", 64.0f, 384.0f, 192.0f, 192.0f, new Direction(0.0));
		
		GameMap.init();
		
		map = new GameMap("res/base_test.tmx");
		
		displayMap = true;
		
		potion = new Potion(10, 200.0f, 312.0f, 16.0f, 16.0f);
		gold = new Gold(10, 560.0f, 360.0f, 16.0f, 16.0f);
		
		backpack = new Backpack(1030, 330, 140, 240);
		actionBar = new ActionBar(0, 600, Constants.SCREEN_WIDTH, 75);
		
		music.stop();
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
		brush.setColor(Color.gray.darker());
		brush.setColor(Color.white);
		//brush.draw(obstacle.getBoundingBox());
		
		//drawDebugInfo(brush);
		
		// Draw actionBar-related components...
		for (Object object : actionBar.getAllComponents()) {
			
			Rectangle shape = null;
			GameButton gameButton = null;
			
			if (object instanceof Rectangle) {
				
				shape = (Rectangle)object;
				
				brush.draw(shape);
			}
			
			if (object instanceof GameButton) {
				
				Shape buttonBound = null;
				
				buttonBound = (Shape)object;
				gameButton = (GameButton)object;
				
				brush.draw(buttonBound);
				brush.drawString(gameButton.getText(), gameButton.getX() + 8, gameButton.getY() + 8);
			}
		}
		
		brush.setWorldClip(0f, 0f, 1200.0f, 600.0f);
		
		//Camera.instance().translate(player, map, brush, (float)(Constants.SCREEN_WIDTH / 2), (float)(Constants.SCREEN_HEIGHT / 2));
		
		int x = (int)Math.ceil((player.getX() * -1) + Constants.SCREEN_WIDTH / 2);
		int y = (int)Math.ceil((player.getY() * -1) + Constants.SCREEN_HEIGHT / 2);
		
		int width = (int)Math.floor(Constants.SCREEN_WIDTH / 2);
		int height = (int)Math.floor(Constants.SCREEN_HEIGHT / 2);
		
		brush.translate(x, y);
		
		if (displayMap) {
			
			map.render(0, 0, 0, 0, width, height);
		}
		
		//brush.draw(player.getBoundingBox());
		
		// Draw the potion under the player to simulate perspectived.
//		if ((player.getY() + player.getHeight() >= potion.getY() + potion.getHeight()) ||
//		    (player.getY() <= potion.getY() + potion.getHeight())) {
//			
//			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
//			drawPlayer(brush);
//		}
//		
//		// Draw the potion over the player to simulate perspective.
//		if (player.getY() + player.getHeight() < potion.getY() + potion.getHeight()) {
//			
//			drawPlayer(brush);
//			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
//		}
		
		// Draw the potion under the player to simulate perspectived.
		if ((player.getY() + player.getHeight() >= gold.getY() + gold.getHeight()) ||
		    (player.getY() <= gold.getY() + gold.getHeight())) {
			
			brush.drawImage(gold.getSprite(), gold.getX(), gold.getY());
			drawPlayer(brush);
		}
		
		// Draw the potion over the player to simulate perspective.
		if (player.getY() + player.getHeight() < gold.getY() + gold.getHeight()) {
			
			drawPlayer(brush);
			brush.drawImage(gold.getSprite(), gold.getX(), gold.getY());
		}
		
		//brush.draw(potion.getBoundingBox());
		
		brush.clearWorldClip();
		brush.resetTransform();
		
		brush.drawString(String.valueOf(player.getDirection().getHeading()), 10, 60);
		
		if (!backpack.isDisplayed() && input.isKeyPressed(Input.KEY_B)) {
			
			backpack.setDisplayed(true);
		}
		
		else if (backpack.isDisplayed() && input.isKeyPressed(Input.KEY_B)) {
			
			backpack.setDisplayed(false);
		}
		
		if (backpack.isDisplayed()) {
			
			brush.draw(backpack);
		}
		
		// Draw health bar...
		Color color = brush.getColor();
		brush.setColor(Color.white.darker(0.40f));
		brush.draw(healthBar.getFrame());
		brush.setColor(Color.green.darker(0.30f));
		brush.fill(healthBar.getFillBar());
		brush.setColor(color);
		
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		// TODO: button clicked implementation (refer to MouseListener documenation and find examples)
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
				
				if (player.getDirection().getHeading() == 180) {
					
					player.getPlayerIdleAnimation().draw(player.getX(), player.getY());
				}
				
				if (player.getDirection().getHeading() == 0 ||
					player.getDirection().getHeading() == 90 ||
					player.getDirection().getHeading() == 270) {
					
					brush.drawImage(player.getSprite(), player.getX(), player.getY());
				}
			}
		}
	}
	
	private void drawDebugInfo(Graphics brush) {
		
		brush.drawString(coords, 20.0f, Constants.SCREEN_HEIGHT - 30.0f);
		brush.drawString(strIntersects, 90.0f, 10.0f);
		brush.drawString("Player X: " + String.valueOf(player.getX()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 45.0f);
		brush.drawString("Player Y: " + String.valueOf(player.getY()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 25.0f);
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
