/**
 * Class designed to be used to create Bathroom objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
import java.util.Set;

public class Bathroom {
	static public Set<String> Columns = Set.of("HairDryer","Shampoo", "ToiletPaper", "Toilet", "Bath", "Shower", "IsShared");
	private boolean hairDrier;
	private boolean shampoo;
	private boolean toiletPaper;
	private boolean toilet;
	private boolean bath;
	private boolean shower;
	private boolean shared;
	
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
	
	public boolean setHair(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "HairDryer", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "HairDryer", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setShampoo(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Shampoo", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Shampoo", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setToiletPaper(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "ToiletPaper", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "ToiletPaper", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setToilet(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Toilet", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Toilet", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setBath(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Bath", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Bath", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setShower(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Shower", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "Shower", PropertyID.toString(), 0);
		}
		return output;
	}
	public boolean setShared(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "IsShared", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Bathing_Facility", "IsShared", PropertyID.toString(), 0);
		}
		return output;
	}
	
	
	public String toString() {
		String str;
		str = "Amenities provided:" + "\n";
		str += "Hair Drier: " + getHairDrier() + "\n";
		str += "Shampoo: " + getShampoo() + "\n";
		str += "Toilet Paper: " + getToiletPaper() + "\n";
		str += "Toilet: " + getToilet() + "\n";
		str += "Bath Tub: " + getBath() + "\n";
		str += "Shower: " + getShower() + "\n";
		str += "Shared: " + getShared() + "\n";
		
		String result = str.replaceAll("true", "\u2713");
		result = result.replaceAll("false", "\u2717");
		
		return result;
	}
}
