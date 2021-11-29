/**
 * Class designed to create Outdoor Amenity
 * 
 * @version 1.0 
 *
 * @author Rachel Parker
 *
 */

package main;
public class Outdoor {
	private boolean patio;
	private boolean bbq;
	private ParkType parking;
	
	// Outdoor with parking
	public Outdoor(boolean patio, boolean bbq, ParkType parking) {
		this.patio = patio;
		this.bbq = bbq;
		this.parking = parking;
	}
	
	// Outdoor without parking
//		public Outdoor(boolean patio, boolean bbq, ParkType parking) {
//			this.patio = patio;
//			this.bbq = bbq;
//			this.parking = null;
//		}
		
	public boolean getPatio() {
		return this.patio;
	}
	
	public boolean getBbq() {
		return this.bbq;
	}
	
	public ParkType getParking() {
		return this.parking;
	}
	
	public boolean setPatio(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Patio", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Patio", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setBBQ(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Barbeque", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Barbeque", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setParking(ParkType value, Integer PropertyID)
	{
		boolean output=false;
		output=TDatabase.UpdateFacilityValue("Outdoor_Facility", "Parking", PropertyID.toString(), value.getString());
		return output;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Patio: ");
		sb.append(getPatio());
		sb.append("\n");
		sb.append("Bbq provided: ");
		sb.append(getBbq());
		sb.append("\n");
		sb.append("Parking facility: ");
		sb.append("\n");
		if (parking != null) {
			sb.append(getParking().getString());
			sb.append("\n");
			}
		
		return sb.toString();
	}
		
}
