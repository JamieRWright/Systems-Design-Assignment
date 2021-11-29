/**
 * Class designed to be used to create Bedroom objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
public class Bedroom {
	private boolean bedLinen;
	private boolean towels;
	private BedType bedOne;
	private BedType bedTwo;
	
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
	
	public boolean setLinen(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "BedLinen", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "BedLinen", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setTowels(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Towels", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Towels", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setBed1Type(BedType value, Integer PropertyID)
	{
		boolean output=false;
		output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Bed1Type", PropertyID.toString(), value.getString());
		return output;
	}
	public boolean setBed1Num(BedType value, Integer PropertyID)
	{
		boolean output=false;
		output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "PeopleInBed1", PropertyID.toString(), value.getNumSleeper());
		return output;
	}
	
	public boolean setBed2Type(BedType value, Integer PropertyID)
	{
		boolean output=false;
		output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Bed2Type", PropertyID.toString(), value.getString());
		return output;
	}
	public boolean setBed2Num(BedType value, Integer PropertyID)
	{
		boolean output=false;
		output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "PeopleInBed2", PropertyID.toString(), value.getNumSleeper());
		return output;
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
