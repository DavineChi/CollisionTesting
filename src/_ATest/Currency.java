package _ATest;

public class Currency {
	
	private int value;
	
	public Currency() {
		
		this.value = 0;
	}
	
	public void addValue(int value) {
		
		this.value = this.value + value;
	}
	
	public int getValue() {
		
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
		
		return gold + " G | " + silver + " S | " + copper + " C";
	}
}
