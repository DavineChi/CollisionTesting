package _ATest;

/****************************************************************************************************************
 * Base class used for creation and management of various Tiles.
 * 
 * @author Shannon Fisher
 */
public class Tile extends Entity {

	private int layer;
	
	/************************************************************************************************************
	 * Constructor used to create a new Tile.
	 * <p>
	 * 
	 * @param name
	 *   The name of this Tile.
	 */
	public Tile(String name) {
		
		this.name = name;
		this.layer = 0;
	}
	
	/************************************************************************************************************
	 * Constructor used to create a new Tile.
	 * <p>
	 * 
	 * @param name
	 *   The name of this Tile.
	 * 
	 * @param layer
	 *   The layer ID for this Tile.
	 */
	public Tile(String name, int layer) {
		
		this.name = name;
		this.layer = layer;
	}
	
	/************************************************************************************************************
	 * Accessor method to get this Tile's layer ID.
	 * 
	 * @return
	 *   The layer ID for this Tile.
	 */
	public int getLayer() {
		
		return layer;
	}
	
	/************************************************************************************************************
	 * Modifier method to set the layer ID for this Tile.
	 * 
	 * @param layer
	 *   The layer ID for this Tile.
	 * 
	 * @postcondition
	 *   This Tile's layer ID has been modified.
	 */
	public void setLayer(int layer) {
		
		this.layer = layer;
	}
}
