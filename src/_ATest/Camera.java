package _ATest;

import org.newdawn.slick.Graphics;

public final class Camera {

	private static Camera camera = null;
	
	public static Camera instance() {
		
		if (camera == null) {
			
			camera = new Camera();
		}
		
		return camera;
	}
	
	public void translate(Player player, GameMap map, Graphics brush, float offsetX, float offsetY) {
		
		brush.translate((player.getX() * -1) + offsetX, (player.getY() * -1) + offsetY);
		map.render(0, 0, 0, 0, (int)offsetX, (int)offsetY);
		brush.draw(player.getBoundingBox());
	}
}
