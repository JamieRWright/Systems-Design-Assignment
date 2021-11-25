/**
 * Class designed to be used to create Kitchen objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
public class Kitchen {
	private boolean fridge;
	private boolean microwave;
	private boolean oven;
	private boolean stove;
	private boolean dishwasher;
	private boolean tableware;
	private boolean cookware;
	private boolean basicProvisions;
	
	public Kitchen(boolean fridge, boolean microwave, boolean oven, boolean stove, boolean dishwasher, boolean tableware, boolean cookware, boolean basicProvisions) {
		this.fridge = fridge;
		this.microwave = microwave;
		this.oven = oven;
		this.stove = stove;
		this.dishwasher = dishwasher;
		this.tableware = tableware;
		this.cookware = cookware;
		this.basicProvisions = basicProvisions;
	}
	
	public boolean getFridge() {return this.fridge;}
	public boolean getMicrowave() {return this.microwave;}
	public boolean getOven() {return this.oven;}
	public boolean getStove() {return this.stove;}
	public boolean getDishwasher() {return this.dishwasher;}
	public boolean getTableware() {return this.tableware;}
	public boolean getCookware() {return this.cookware;}
	public boolean getBasicProvisions() {return this.basicProvisions;}
	
	public String toString() {
		String str;
		str = "Amenities provided" + "\n";
		str += "Fridge: " + getFridge() + "\n";
		str += "Microwave: " + getMicrowave() + "\n";
		str += "Oven: " + getOven() + "\n";
		str += "Stove: " + getStove() + "\n";
		str += "Dishwasher: " + getDishwasher() + "\n";
		str += "Tableware: " + getTableware() + "\n";
		str += "Cookware: " + getCookware() + "\n";
		str += "Basic Provisions: " + getBasicProvisions() + "\n";
		
		return str;
	}
		public String popUpText() {
		String text ="";
		if (getFridge()) {
			text = text + "Fridge u\2713 \n";
		}
		else if (!getFridge()){
			text = text + "Fridge u\02DF \n";
		}
		if (getMicrowave()) {
			text = text + "Microwave u\2713 \n";
		}
		else if (!getMicrowave()){
			text = text + "Microwave u\02DF \n";
		}
		if (getOven()) {
			text = text + "Oven u\2713 \n";
		}
		else if (!getOven()){
			text = text + "Oven u\02DF \n";
		}
		if (getStove()) {
			text = text + "Stove u\2713 \n";
		}
		else if (!getStove()){
			text = text + "Stove u\02DF \n";
		}
		if (getDishwasher()) {
			text = text + "Dishwasher u\2713 \n";
		}
		else if (!getDishwasher()){
			text = text + "Dishwasher u\02DF \n";
		}
		if (getTableware()) {
			text = text + "Tableware u\2713 \n";
		}
		else if (!getTableware()){
			text = text + "Tableware u\02DF \n";
		}
		if (getCookware()) {
			text = text + "Cookware u\2713 \n";
		}
		else if (!getCookware()){
			text = text + "Cookware u\02DF \n";
		}
		if (getBasicProvisions()) {
			text = text + "Basic Provisions u\2713 \n";
		}
		else if (!getBasicProvisions()){
			text = text + "Basic Provisions u\02DF \n";
		}
		return text;
	}
}
