package _ATest;

public final class Camera {

	private static Camera camera = null;
	
	private float x;
	private float y;
	private float width;
	private float height;
	
	public static Camera instance() {
		
		if (camera == null) {
			
			camera = new Camera();
		}
		
		return camera;
	}
	
	public void setX(float x) {
		
		camera.x = x;
	}

	public void setY(float y) {
		
		camera.y = y;
	}

	public void setWidth(float width) {
		
		camera.width = width;
	}

	public void setHeight(float height) {
		
		camera.height = height;
	}
}
