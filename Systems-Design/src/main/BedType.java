/**
 * Enum with constants representing the different bed types
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
public enum BedType {
	Single("Single bed", 1),
	Double("Double bed", 2),
	King("Kingsize bed", 2),
	Bunk("Bunk bed", 2);
	
	private String bedType;
	private int numSleeper;
	BedType(String n, int sleeper) {
		bedType = n;
		numSleeper = sleeper;
		}
	
	public String getString() {return bedType;}
	
	public int getNumSleeper() {return numSleeper;}
}
