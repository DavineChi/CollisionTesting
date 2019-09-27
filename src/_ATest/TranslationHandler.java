package _ATest;

import org.newdawn.slick.Graphics;

public class TranslationHandler {

	private static final int PLAYER_POSITION_BOUND_X = 67;
	private static final int PLAYER_POSITION_BOUND_Y = 23;
	
	private static final float TRANSLATE_OFFSET_X = 816.0f;
	private static final float TRANSLATE_OFFSET_Y = 384.0f;
	
	public static void translate(Player player, Graphics brush, String element) {
		
		float playerPosX = player.getX();
		float playerPosY = player.getY();
		
		if (element.equals("GameMap")) {
			
			if (playerPosY > PLAYER_POSITION_BOUND_Y)
				brush.translate(0.0f, -TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 2) + 1)
				brush.translate(0.0f, -TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 3) + 2)
				brush.translate(0.0f, -TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 4) + 3)
				brush.translate(0.0f, -TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 5) + 4)
				brush.translate(0.0f, -TRANSLATE_OFFSET_Y);
			
			if (playerPosX > PLAYER_POSITION_BOUND_X)
				brush.translate(-TRANSLATE_OFFSET_X, 0.0f);
			
			if (playerPosX > (PLAYER_POSITION_BOUND_X * 2) + 1)
				brush.translate(-TRANSLATE_OFFSET_X, 0.0f);
			
			if (playerPosX > (PLAYER_POSITION_BOUND_X * 3) + 2)
				brush.translate(-TRANSLATE_OFFSET_X, 0.0f);
		}
		
		if (element.equals("InformationPanel")) {
			
			if (playerPosY > PLAYER_POSITION_BOUND_Y)
				brush.translate(0.0f, TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 2) + 1)
				brush.translate(0.0f, TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 3) + 2)
				brush.translate(0.0f, TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 4) + 3)
				brush.translate(0.0f, TRANSLATE_OFFSET_Y);
			
			if (playerPosY > (PLAYER_POSITION_BOUND_Y * 5) + 4)
				brush.translate(0.0f, TRANSLATE_OFFSET_Y);
			
			if (playerPosX > PLAYER_POSITION_BOUND_X)
				brush.translate(TRANSLATE_OFFSET_X, 0.0f);
			
			if (playerPosX > (PLAYER_POSITION_BOUND_X * 2) + 1)
				brush.translate(TRANSLATE_OFFSET_X, 0.0f);
			
			if (playerPosX > (PLAYER_POSITION_BOUND_X * 3) + 2)
				brush.translate(TRANSLATE_OFFSET_X, 0.0f);
		}
	}
}
