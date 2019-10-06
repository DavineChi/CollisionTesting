package _ATest;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap extends TiledMap {
	
	public static GameMap MAP = null;
	
	public GameMap(String inputStream) throws SlickException {
		
		super(inputStream);
	}
	
	public static void init() {
		
		try {
			
			MAP = new GameMap("res/base_test.tmx");
		}
		
		catch (SlickException ex) {
			
			ex.printStackTrace();
		}
	}
}
