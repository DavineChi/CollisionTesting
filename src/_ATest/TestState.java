package _ATest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends BasicGameState {
	
	private int id;
	private Player player;
	private int hitPoints;
	private GameTimer timer;
	private Input input;
	
	public TestState(int id) {
		
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		input = container.getInput();
		timer = new GameTimer();
		player = Player.instance();
		hitPoints = player.getHitPoints();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		timer.tick(delta);
		
		if (input.isKeyPressed(Input.KEY_R)) {
			
			timer.reset();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		brush.drawString(String.valueOf(hitPoints), 50, 50);
		brush.drawString("Time : " + timer.getTime() / 1000, 500, 500);
	}
	
	@Override
	public int getID() {
		
		return id;
	}
}
