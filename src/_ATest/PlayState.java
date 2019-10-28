package _ATest;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.lwjgl.util.Timer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class PlayState extends BasicGameState {
	
	private int id;
	private Player player;
	private Player obstacle;
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
	private Music dayforest01;
	private Music dayforest02;
	private Font awtFont;
	private UnicodeFont font;
	private InputStream inStream;
	private Sound levelUpSound;
	private Image particleImage;
	private ParticleSystem particleSystem;
	private Sound forestNormalDay;
	private Timer levelUpParticleTimer;
	private boolean isLevelingUp;
	private Sound[] currencyCollectSound;
	private Bank bank;
	
	private ConfigurableEmitter configurableEmitter;
	
	public PlayState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		
		dayforest01.loop(1.0f, 0.125f);
		forestNormalDay.loop(1.0f, 0.2f);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
		//music.stop();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		// TODO: Find or make a sprite for a bank...
		//bank = new Bank(0, 0, 0, 0);
		
		Sound curSound01 = new Sound("res/audio/interactive/Coins_Few_03.ogg");
		Sound curSound02 = new Sound("res/audio/interactive/Coins_Few_06.ogg");
		Sound curSound03 = new Sound("res/audio/interactive/Coins_Few_07.ogg");
		Sound curSound04 = new Sound("res/audio/interactive/Coins_Few_30.ogg");
		Sound curSound05 = new Sound("res/audio/interactive/Coins_Few_44.ogg");
		
		currencyCollectSound = new Sound[5];
		
		currencyCollectSound[0] = curSound01;
		currencyCollectSound[1] = curSound02;
		currencyCollectSound[2] = curSound03;
		currencyCollectSound[3] = curSound04;
		currencyCollectSound[4] = curSound05;
		
		isLevelingUp = false;
		
		levelUpParticleTimer = new Timer();
		
		forestNormalDay = new Sound("res/audio/environment/ForestNormalDay.ogg");
		
		particleImage = new Image("res/particle-data/sparkling_fireball_pack/particles_fireball_small_wind/0011.png", false);
		particleSystem = new ParticleSystem(particleImage, 100);
		
		configurableEmitter = new ConfigurableEmitter(null);
		
		particleSystem.addEmitter(new FireEmitter(0, 0, 10));
		particleSystem.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		
		levelUpSound = new Sound("res/audio/effects/levelup.ogg");
		
		inStream = ResourceLoader.getResourceAsStream("res/fonts/Friz Quadrata TT Regular.ttf");
		
		try {
			
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inStream);
		}
		
		catch (FontFormatException ffEx) {
			
			ffEx.printStackTrace();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
		}
		
		awtFont = awtFont.deriveFont(Font.PLAIN, 14.0f);
		font = new UnicodeFont(awtFont);
		
		font.addAsciiGlyphs();
		
		ColorEffect effect = new ColorEffect();
		
		effect.setColor(java.awt.Color.white);
		font.getEffects().add(effect);
		font.loadGlyphs();
		
		dayforest01 = new Music(Constants.MUSIC_PATH + "dayforest01.ogg");
		dayforest02 = new Music(Constants.MUSIC_PATH + "dayforest02.ogg");
		
		healthBar = new HealthBar(50.0f, 46.0f, 180.0f, 8.0f);
		
		input = container.getInput();
		
		playerSprites = new Image("res/Fumiko.png");
		playerSpriteSheet = new SpriteSheet(playerSprites, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		
		playerSpriteSheet.setFilter(Image.FILTER_NEAREST);
		
		//player = new Player("Ayrn", 340.0f, 280.0f, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, new Direction(180.0), playerSpriteSheet);
		player = Player.instance();
		obstacle = new Player("Water", 64.0f, 384.0f, 192.0f, 192.0f, new Direction(0.0));
		
		GameMap.init();
		
		//map = new GameMap("res/base_test.tmx");
		map = new GameMap("res/northshire_test.tmx");
		
		displayMap = true;
		
		potion = new Potion(10, 200.0f, 312.0f, 16.0f, 16.0f);
		gold = new Gold(10, 560.0f, 360.0f, 16.0f, 16.0f);
		
		backpack = new Backpack(1030, 330, 140, 240);
		actionBar = new ActionBar(0, 600, Constants.SCREEN_WIDTH, 75);
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
		
		// Timer section for HealthBar testing...
		Timer.tick();
		
		if (input.isKeyPressed(Input.KEY_APOSTROPHE)) {
			
			int index = getCurrencyCollectSoundIndex();
			
			player.addCurrency(15);
			currencyCollectSound[index].play(1.0f, 0.2f);
		}
		
		if (input.isKeyPressed(Input.KEY_SEMICOLON)) {
			
			if (player.getCurrency().getValue() > 0) {
				
				int index = getCurrencyCollectSoundIndex();
				
				player.removeCurrency(5);
				currencyCollectSound[index].play(1.0f, 0.2f);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_PERIOD)) {
			
			int index = getCurrencyCollectSoundIndex();
			
			player.addCurrency(2525);
			currencyCollectSound[index].play(1.0f, 0.2f);
		}
		
		if (input.isKeyPressed(Input.KEY_COMMA)) {
			
			if (player.getCurrency().getValue() > 0) {
				
				int index = getCurrencyCollectSoundIndex();
				
				player.removeCurrency(500);
				currencyCollectSound[index].play(1.0f, 0.2f);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_H)) {
			
			player.setHitPoints(player.getHitPoints() / 2);
			healthBar.getTimer().reset();
			healthBar.getCooldownTimer().reset();
		}
		
		if (input.isKeyPressed(Input.KEY_LBRACKET)) {
			
			player.setState(Player.State.IN_COMBAT);
		}
		
		if (input.isKeyPressed(Input.KEY_RBRACKET)) {
			
			player.setState(Player.State.NORMAL);
			healthBar.getTimer().reset();
			healthBar.getCooldownTimer().reset();
		}
		
		if (input.isKeyPressed(Input.KEY_L) && (player.getLevel() + 1) <= Player.MAXIMUM_LEVEL) {
			
			Player.addLevel();
			levelUpSound.play(1.0f, 0.375f);
			levelUpParticleTimer.reset();
			
			isLevelingUp = true;
		}
		
		healthBar.update();
		particleSystem.update(delta);
	}

	private int getCurrencyCollectSoundIndex() {
		
		int index = 0;
		double randomValue = Math.random();
		
		if (randomValue > 0.5 && randomValue < 1.0) {
			
			randomValue = randomValue - 0.25;
		}
		
		if (randomValue < 0.5 && randomValue > 0.0) {
			
			index = (int)Math.floor(randomValue * 10);
		}
		
		return index;
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
		
		// This line moves the map instead of the player.
		brush.translate(x, y);
		
		if (displayMap) {
			
			map.render(0, 0, 0, 0, width, height);
		}
		
		// Okay, that's interesting. Although this is incorrect, this appears to provide a parallax effect
		// layered above the map itself. Once potential use-case for this would be something like tree-cover
		// above, and, if we recreate Teldrassil, also a background effect that moves also.
//		for (int i = 0; i < 250; i++) {
//			
//			particleSystem.render(player.getX() - width, player.getY() - height);
//		}
		
		//brush.draw(player.getBoundingBox());
		
		// Draw the potion under the player to simulate perspectived.
		if ((player.getY() + player.getHeight() >= potion.getY() + potion.getHeight()) ||
		    (player.getY() <= potion.getY() + potion.getHeight())) {
			
			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
			drawPlayer(brush);
		}
		
		// Draw the potion over the player to simulate perspective.
		if (player.getY() + player.getHeight() < potion.getY() + potion.getHeight()) {
			
			drawPlayer(brush);
			brush.drawImage(potion.getSprite(), potion.getX(), potion.getY());
		}
		
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
		
		// Level-up particle rendering...
		if (isLevelingUp && levelUpParticleTimer.getTime() < 3.0f) {
			
			//float size = 1.0f;
			
			for (int i = 0; i < 20; i++) {
				
				particleSystem.render(player.getX() + (player.getWidth() / 2), player.getY() + player.getHeight());
			}
		}
		
		if (levelUpParticleTimer.getTime() >= 3.0f) {
			
			isLevelingUp = false;
			levelUpParticleTimer.reset();
		}
		
		brush.clearWorldClip();
		brush.resetTransform();
		
		if (!backpack.isDisplayed() && input.isKeyPressed(Input.KEY_B)) {
			
			backpack.setDisplayed(true);
		}
		
		else if (backpack.isDisplayed() && input.isKeyPressed(Input.KEY_B)) {
			
			backpack.setDisplayed(false);
		}
		
		if (backpack.isDisplayed()) {
			
			brush.draw(backpack);
		}
		
		// -- draw health bar --
		Color color = brush.getColor();
		font.drawString(50.0f, 2.0f, String.valueOf("Level " + player.getLevel()));
		font.drawString(50.0f, 20.0f, String.valueOf("Health " + player.getHitPoints()) + " / " + player.getMaxHitPoints());
		
		brush.setColor(Color.white.darker(0.40f));
		brush.draw(healthBar.getFrame());
		brush.setColor(Color.green.darker(0.30f));
		brush.fill(healthBar.getFillBar());
		brush.setColor(color);
		// -- end health bar section --
		
		font.drawString(10.0f, 90.0f, "       Player State: " + player.getState().toString());
		font.drawString(10.0f, 110.0f, "HealthBar State: " + healthBar.getState().toString());
		font.drawString(10.0f, 130.0f, String.valueOf("            Heading: " + player.getDirection().getHeading()));
		
		font.drawString(10.0f, 570.0f, Player.instance().getCurrency().toString());
		
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
	
//	private void drawDebugInfo(Graphics brush) {
//		
//		brush.drawString(coords, 20.0f, Constants.SCREEN_HEIGHT - 30.0f);
//		brush.drawString(strIntersects, 90.0f, 10.0f);
//		brush.drawString("Player X: " + String.valueOf(player.getX()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 45.0f);
//		brush.drawString("Player Y: " + String.valueOf(player.getY()), Constants.SCREEN_WIDTH - 154.0f, Constants.SCREEN_HEIGHT - 25.0f);
//	}
	
	private void teleport() {
		
		player.setX(300.0f);
		player.setY(300.0f);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
}
