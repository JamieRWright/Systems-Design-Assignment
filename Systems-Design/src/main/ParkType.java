/**
 * Enum with constants representing the different parking facilities
 * 
 * @version 1.0 
 *
 * @author Rachel Parker
 *
 */

package main;
public enum ParkType {
	free("free on-site parking"),
	onRoad("on road parking"),
	paid("paid car-park");
	
	private final String ParkType;
	
	public String getString() {return ParkType;}
}