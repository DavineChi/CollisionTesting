package _ATest;

public class Currency {
	
	private long value;
	
	/************************************************************************************************************
	 * Constructor to create a new Currency object.
	 * <p>
	 * 
	 */
	public Currency() {
		
		this.value = 0;
	}
	
	/************************************************************************************************************
	 * Modifier method to add to this Currency object's value.
	 * 
	 * @param value
	 *   the amount of value to add
	 * 
	 * @postcondition
	 *   This Currency object's value has been modified.
	 */
	public void addValue(int value) {
		
		this.value = this.value + value;
	}
	
	/************************************************************************************************************
	 * Modifier method to remove from this Currency object's value.
	 * 
	 * @param value
	 *   the amount of value to remove
	 * 
	 * @postcondition
	 *   This Currency object's value has been modified.
	 * </br>
	 * </br>
     * <b>Note:</b>
     *   This method prevents negative-resulting values from being set.
	 */
	public void removeValue(int value) {
		
		if (this.value - value >= 0) {
			
			this.value = this.value - value;
		}
	}
	
	/************************************************************************************************************
	 * Accessor method to retrieve the value of this Currency object.
	 * <p>
	 * 
	 * @return
	 *   The value of this Currency object.
	 */
	public long getValue() {
		
		return value;
	}
	
	@Override
	public String toString() {
		
		String strVal = String.valueOf(value);
		int strLen = strVal.length();
		
		String copper = "0";
		String silver = "0";
		String gold = "0";
		
		if (strLen < 2) {
			
			copper = strVal;
		}
		
		if (strLen > 1) {
			
			copper = strVal.substring(strVal.length() - 2, strVal.length());
			
			if (Integer.parseInt(copper) < 10) {
				
				copper = copper.substring(1);
			}
		}
		
		if (strLen > 2 && strLen < 4) {
			
			silver = strVal.substring(strVal.length() - 3, strVal.length() - 2);
		}
		
		if (strLen == 4) {
			
			silver = strVal.substring(strVal.length() - 4, strVal.length() - 2);
		}
		
		if (strLen > 4) {
			
			silver = strVal.substring(strVal.length() - 4, strVal.length() - 2);
			
			if (Integer.parseInt(silver) < 10) {
				
				silver = silver.substring(1);
			}
			
			gold = strVal.substring(0, strVal.length() - 4);
		}
		
		return gold + " G    " + silver + " S    " + copper + " C";
	}
}
