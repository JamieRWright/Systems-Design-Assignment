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
	
	public boolean setFridge(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Refrigerator", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Refrigerator", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setMicrowave(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Microwave", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Microwave", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setOven(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Oven", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Oven", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setStove(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Stove", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Stove", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setDishwasher(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Dishwasher", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Dishwasher", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setTableware(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Tableware", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Tableware", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setCookware(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Cookware", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "Cookware", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setBasicProvision(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "basicProvision", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Kitchen_Facility", "basicProvision", PropertyID.toString(), 0);
		}
		return output;
	}
	
		public String toString() {
		String str;
		str = "Amenities provided:" + "\n";
		str += "Fridge: " + getFridge() + "\n";
		str += "Microwave: " + getMicrowave() + "\n";
		str += "Oven: " + getOven() + "\n";
		str += "Stove: " + getStove() + "\n";
		str += "Dishwasher: " + getDishwasher() + "\n";
		str += "Tableware: " + getTableware() + "\n";
		str += "Cookware: " + getCookware() + "\n";
		str += "Basic Provisions: " + getBasicProvisions() + "\n";
		
		String result = str.replaceAll("true", "\u2713");
		result = result.replaceAll("false", "\u2717");
		
		
		return result;
	}
}
