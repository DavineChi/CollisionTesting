package _ATest;

public class ExperienceSystem {

	private static int[] matrix = new int[] { 0,
			                                  400,
			                                  900,
			                                  1400,
			                                  2100,
			                                  2800,
			                                  3600,
			                                  4500,
			                                  5400,
			                                  6500 };
	
	/************************************************************************************************************
	 * Accessor method to retrieve the maximum experience points required to level up
	 * based on the specified level.
	 * 
	 * @param level
	 *   the current level of the Player
	 * 
	 * @return
	 *   The total number of experience points required to reach the next level.
	 */
	public static int getExperienceForNextLevel(int level) {
		
		return matrix[level];
	}
}
