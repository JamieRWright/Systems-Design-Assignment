/**
 * Class designed to be used to Bedroom objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Bedroom {
	final boolean bedLinen;
	final boolean towels;
	final BedType bedOne;
	final BedType bedTwo;
	
	// Bedroom with only one bed
	public Bedroom(boolean bedLinen, boolean towels, BedType bedOne) {
		this.bedLinen = bedLinen;
		this.towels = towels;
		this.bedOne = bedOne;
		this.bedTwo = null;
	}
	
	// Bedroom with two beds
	public Bedroom(boolean bedLinen, boolean towels, BedType bedOne, BedType bedTwo) {
		this.bedLinen = bedLinen;
		this.towels = towels;
		this.bedOne = bedOne;
		this.bedTwo = bedTwo;
	}
	
	public boolean getBedLinen() {
		return this.bedLinen;
	}
	
	public boolean getTowels() {
		return this.towels;
	}
	
	public BedType getBedOne() {
		return this.bedOne;
	}
	
	public BedType getBedTwo() {
		return this.bedTwo;
	}
	
	public int maxSleep(BedType b1, BedType b2) {
		int nSleeper = 0;
		
		if (b2 == null) {
			nSleeper = b1.getNumSleeper();
		}
		else {
			nSleeper += b1.getNumSleeper();
			nSleeper += b2.getNumSleeper();
		}
		
		return nSleeper;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Bed linen provided: ");
		sb.append(getBedLinen());
		sb.append("\n");
		sb.append("Towels provided: ");
		sb.append(getTowels());
		sb.append("\n");
		sb.append("Bed(s) provided: ");
		sb.append("\n");
		sb.append(getBedOne().getString());
		sb.append("\n");
		if (bedTwo != null) {
			sb.append(getBedTwo().getString());
			sb.append("\n");
			}
		sb.append("Maximum Sleepers: ");
		sb.append("\n");
		sb.append(maxSleep(getBedOne(), getBedTwo()));
		
		return sb.toString();
	}
		
}