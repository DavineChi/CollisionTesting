package _ATest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	public Main(String gameName) {
		
		super(gameName);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		//this.addState(new PlayState(States.PLAY));
		this.addState(new TestState(States.TEST));
		//this.addState(new MenuState(States.MENU));
		this.enterState(States.TEST);
	}
	
	public static void main(String[] args) {
		
		try {
			
			AppGameContainer gameContainer = new AppGameContainer(new Main(Constants.GLOBAL_GAME_NAME));
			
			gameContainer.setDisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
			gameContainer.setTargetFrameRate(60);
			gameContainer.setShowFPS(false);
			gameContainer.start();
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
}
