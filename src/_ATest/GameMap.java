package _ATest;

public class GameMap {

	private static final int MAP_WIDTH = 20;
	private static final int MAP_HEIGHT = 10;
	
	private static int[][] gameMap;
	
	public static int[][] instance() {
		
		if (gameMap == null) {
			
			initialize();
		}
		
		return gameMap;
	}
	
	private static void initialize() {
		
		gameMap = new int[MAP_HEIGHT][MAP_WIDTH];
		
		for (int i = 0; i < MAP_HEIGHT; i++) {
			
			for (int j = 0; j < MAP_WIDTH; j++) {
				
				
			}
		}
	}
}
