package _ATest;

import org.lwjgl.util.Timer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends BasicGameState {
	
	private int id;
	private Player player;
	private Input input;
	private Music music;
	
	private HealthBar healthBar;
	
	public TestState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		
		//music.play(1.0f, 0.3f);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		healthBar = new HealthBar(70.0f, 70.0f, 180.0f, 10.0f);
		
		music = new Music(Constants.MUSIC_PATH + "dayforest01.ogg");
		input = container.getInput();
		player = Player.instance();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Timer.tick();
		
		if (input.isKeyPressed(Input.KEY_H)) {
			
			player.setHitPoints(player.getHitPoints() / 2);
			healthBar.getTimer().reset();
			healthBar.getCooldownTimer().reset();
		}
		
		if (input.isKeyPressed(Input.KEY_LBRACKET)) {
			
			player.setState(Player.State.IN_COMBAT);
		}
		
		if (input.isKeyPressed(Input.KEY_RBRACKET)) {
			
			player.setState(Player.State.OUT_COMBAT);
			healthBar.getTimer().reset();
			healthBar.getCooldownTimer().reset();
		}
		
		healthBar.update();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		Color color = brush.getColor();
		brush.drawString(String.valueOf(player.getHitPoints()), 50, 10);
		brush.drawString("HB Timer : " + (int)healthBar.getTime(), 500, 550);
		
		brush.setColor(Color.white.darker(0.40f));
		brush.draw(healthBar.getFrame());
		brush.setColor(Color.green.darker(0.30f));
		brush.fill(healthBar.getFillBar());
		brush.setColor(color);
		
		brush.drawString(healthBar.getState().toString(), 250.0f, 250.0f);
		brush.drawString(player.getState().toString(), 600.0f, 20.0f);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
}
