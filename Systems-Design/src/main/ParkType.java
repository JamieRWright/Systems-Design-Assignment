/**
 * Enum with constants representing the different parking facilities
 * 
 * @version 1.1
 *
 * @author Rachel Parker
 *
 */

package main;
public enum ParkType {
	free("free on-site parking"),
	onRoad("on road parking"),
	paid("paid car-park");
	
	private String ParkType;
	
	ParkType(String n) {
		ParkType = n;
	}

	public String getString() {return ParkType;}
}
