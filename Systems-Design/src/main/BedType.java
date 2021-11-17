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
	Single("Single-sized bed", 1),
	Double("Double-sized bed", 2),
	King("King-sized bed", 2),
	Bunk("Bunk beds", 2);
	
	private String bedType;
	private int numSleeper;
	BedType(String n, int sleeper) {
		bedType = n;
		numSleeper = sleeper;
		}
	
	public String getString() {return bedType;}
	
	public int getNumSleeper() {return numSleeper;}
}