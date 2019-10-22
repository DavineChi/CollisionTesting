package _ATest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuState extends BasicGameState {
	
	private int menuState;
	private TextField playerNameField;
	
	public MenuState(int menuState) {
		
		this.menuState = menuState;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		playerNameField = new TextField(container, container.getDefaultFont(), 40, 150, 180, 20);
		playerNameField.setMaxLength(16);
		playerNameField.setFocus(true);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (container.getInput().isKeyPressed(Input.KEY_RETURN)) {
			
			if (!(playerNameField.getText().isEmpty())) {
				
				Player.instance().setName(playerNameField.getText());
				playerNameField.setText("");
				playerNameField.deactivate();
				container.getInput().clearKeyPressedRecord();
				
				game.enterState(States.PLAY, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics brush) throws SlickException {
		
		brush.drawRect(30.0f, 30.f, 300.0f, 160.0f);
		brush.drawString(Constants.GLOBAL_GAME_NAME, 40.0f, 40.0f);
		brush.drawString("By what name are you hailed?", 40.0f, 130.0f);
		playerNameField.setBackgroundColor(Color.gray.darker(0.1f));
		playerNameField.render(container, brush);
	}
	
	@Override
	public int getID() {
		
		return menuState;
	}
}
