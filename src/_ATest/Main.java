package _ATest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	//private static final int PLAY = 0;
	private static final int TEST = 1;
	
	public Main(String gameName) {
		
		super(gameName);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		//this.addState(new PlayState(PLAY));
		this.addState(new TestState(TEST));
		this.enterState(TEST);
	}
	
	public static void main(String[] args) {
		
		try {
			
			AppGameContainer gameContainer = new AppGameContainer(new Main(Constants.GLOBAL_GAME_NAME));
			
			gameContainer.setDisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
			gameContainer.setTargetFrameRate(60);
			gameContainer.setShowFPS(true);
			gameContainer.start();
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
}
