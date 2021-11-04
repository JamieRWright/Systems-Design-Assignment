/**
 * Class designed to be used to create Bathroom objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Bathroom {
	final boolean hairDrier;
	final boolean shampoo;
	final boolean toiletPaper;
	final boolean toilet;
	final boolean bath;
	final boolean shower;
	final boolean shared;
	
	public Bathroom(boolean hairDrier, boolean shampoo, boolean toiletPaper, boolean toilet, boolean bath, boolean shower, boolean shared) {
		this.hairDrier = hairDrier;
		this.shampoo = shampoo;
		this.toiletPaper = toiletPaper;
		this.toilet = toilet;
		this.bath = bath;
		this.shower = shower;
		this.shared = shared;
	}
	
	public boolean getHairDrier() {
		return this.hairDrier;
	}
	
	public boolean getShampoo() {
		return this.shampoo;
	}
	
	public boolean getToiletPaper() {
		return this.toiletPaper;
	}
	
	public boolean getToilet() {
		return this.toilet;
	}
	
	public boolean getBath() {
		return this.bath;
	}
	
	public boolean getShower() {
		return this.shower;
	}
	
	public boolean getShared() {
		return this.shared;
	}
	
	public String toString() {
		String str;
		str = "Shared: " + getShared() + "\n";
		str += "Amenities provided" + "\n";
		str += "Hair Drier: " + getHairDrier() + "\n";
		str += "Shampoo: " + getShampoo() + "\n";
		str += "Toilet Paper: " + getToiletPaper() + "\n";
		str += "Toilet: " + getToilet() + "\n";
		str += "Bath Tub: " + getBath() + "\n";
		str += "Shower: " + getShower() + "\n";
		
		return str;
	}
}