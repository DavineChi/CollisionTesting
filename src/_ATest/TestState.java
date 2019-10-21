package _ATest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends BasicGameState {
	
	private int id;
	private Player player;
	private GameTimer timer;
	private Input input;
	
	private HealthBar healthBar;
	
	public TestState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		healthBar = new HealthBar(70.0f, 70.0f, 120.0f, 10.0f);
		input = container.getInput();
		timer = new GameTimer();
		player = Player.instance();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		timer.tick(delta);
		
		if (input.isKeyPressed(Input.KEY_R)) {
			
			timer.reset();
		}
		
		if (input.isKeyPressed(Input.KEY_H)) {
			
			player.setHitPoints(player.getHitPoints() / 2);
			timer.reset();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		Color color = brush.getColor();
		brush.drawString(String.valueOf(player.getHitPoints()), 50, 10);
		brush.drawString("Time : " + timer.getTime() / 1000, 500, 500);
		
		brush.setColor(Color.white.darker(0.40f));
		brush.draw(healthBar.getFrame());
		brush.setColor(Color.green.darker(0.30f));
		brush.fill(healthBar.getFillBar());
		brush.setColor(color);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
}
