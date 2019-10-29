package _ATest;

/****************************************************************************************************************
 * The HitPoints class consists of a collection of static methods which calculate hit points of a Character (from
 * the Character class) based on the Character's level.
 * 
 * @author Shannon L. Fisher
 *         <p>
 *         Begin Date: 2017.05.22
 */
public class HitPoints {

	// https://www.desmos.com/calculator/wbsfd1fd9l
	/************************************************************************************************************
	 * An accessor method to calculate and retrieve a Player's hit points based on the Player's level.
	 * <p>
	 * 
	 * @param player
	 *        The Player from which to calculate hit points.
	 * 
	 * @precondition The parameter player is an instance of the Player class.
	 * 
	 * @return The Player's newly-calculated hit points.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter player is not an instance of the Player class.
	 */
	public static int calculate(Player player) {

		int hitPoints;
		
		if (!(player instanceof Player)) {
			
			throw new IllegalArgumentException("Invalid parameter type.");
		}
		
		else {
			
			hitPoints = calculatePlayerHitPoints(player.getLevel());
		}
		
		return hitPoints;
	}

	/************************************************************************************************************
	 * An accessor method to calculate and retrieve a Monster's hit points based on the Monster's level.
	 * <p>
	 * 
	 * @param monster
	 *        The Monster from which to calculate hit points.
	 * 
	 * @precondition The parameter monster is an instance of the Monster class.
	 * 
	 * @return The Monster's newly-calculated hit points.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter monster is not an instance of the Monster
	 *         class.
	 */
	// public static int calculate(Monster monster) {
	//
	// int hitPoints;
	// if (!(monster instanceof Monster))
	// throw new IllegalArgumentException("Invalid parameter type.");
	// else
	// hitPoints = calculateMonsterHitPoints(monster.getLevel());
	// return hitPoints;
	// }
	/************************************************************************************************************
	 * An accessor method to calculate and retrieve hit points based on the specified level.
	 * <p>
	 * 
	 * @param level
	 *        The level from which to calculate hit points.
	 * 
	 * @precondition The parameter level is greater than or equal to 1.
	 * 
	 * @return The newly-calculated hit points.
	 * 
	 * @throws IllegalArgumentException
	 *         IllegalArgumentException is thrown if the parameter level is less than 1.
	 */
	public static int calculate(int level) {

		int hitPoints;
		
		if (level < 1) {
			
			throw new IllegalArgumentException("Invalid parameter: " + level);
		}
		
		hitPoints = calculatePlayerHitPoints(level);
		
		return hitPoints;
	}

	// A private helper method containing the hit point formula for a Player.
	private static int calculatePlayerHitPoints(int level) {

		double hitPoints;
		int convertedHitPoints;
		int newHitPoints;
		
		hitPoints = ((163 * (level * level)) / 9) - ((131 * level) / 3) + (1130 / 9);
		convertedHitPoints = (int) (Math.ceil(hitPoints));
		newHitPoints = ((convertedHitPoints / 5) * 5);
		
		if (newHitPoints < convertedHitPoints) {
			
			newHitPoints = newHitPoints + 5;
		}
		
		return newHitPoints;
	}

	// A private helper method containing the hit point formula for a Monster.
	private static int calculateMonsterHitPoints(int level) {

		double hitPoints;
		int convertedHitPoints;
		int newHitPoints;
		
		hitPoints = (int)((10 * (Math.pow(level, 3)) / 9) + (475 * ((Math.pow(level, 2)) / 36)) - ((1165 * level) / 36) + (1675 / 18));
		convertedHitPoints = (int)(Math.ceil(hitPoints));
		newHitPoints = ((convertedHitPoints / 5) * 5);
		
		if (newHitPoints < convertedHitPoints) {
			
			newHitPoints = newHitPoints + 5;
		}
		
		return newHitPoints;
	}

	/************************************************************************************************************
	 * Main method from where program execution begins. Used here for testing and debugging.
	 * <p>
	 * 
	 * @param args
	 *        This parameter is not used.
	 * 
	 * @postcondition Varies depending on testing.
	 */
	public static void main(String[] args) {
		
		final int MAX_LEVEL = Constants.MAXIMUM_PLAYER_LEVEL;
		
		for (int i = 1; i <= MAX_LEVEL; i++) {
			
			System.out.println("Level: " + i);
			System.out.println("Hit Points (Player):  " + calculatePlayerHitPoints(i));
			System.out.println("Hit Points (Monster): " + calculateMonsterHitPoints(i));
			System.out.println("----------------------------");
		}
	}
}
