package _ATest;

import org.newdawn.slick.geom.Rectangle;

public class GameButton extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	/************************************************************************************************************
	 * Constructor used to create a new GameButton.
	 * <p>
	 * 
	 * @param text
	 *   The label for this GameButton.
	 * 
	 * @param posX
	 *   The X-coordinate for this GameButton.
	 * 
	 * @param posY
	 *   The Y-coordinate for this GameButton.
	 * 
	 * @param width
	 *   The width of this GameButton.
	 * 
	 * @param height
	 *   The height of this GameButton.
	 */
	public GameButton(String text, float posX, float posY, float width, float height) {
		
		super(posX, posY, width, height);
		
		this.text = text;
	}
	
	/************************************************************************************************************
	 * Accessor method to retrieve this GameButton's label.
	 * <p>
	 * 
	 * @return This GameButton's label.
	 */
	public String getText() {
		
		return text;
	}
	
	/************************************************************************************************************
	 * Modifier method to set this GameButton's label.
	 * <p>
	 * 
	 * @param text
	 *   The new label for this GameButton.
	 * 
	 * @postcondition This GameButton's label has been modified.
	 */
	public void setText(String text) {
		
		this.text = text;
	}
}
