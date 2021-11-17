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