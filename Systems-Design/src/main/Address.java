/**
 * Class designed to be used to create Address objects,
 * consist of house number, street name, post town, postcode
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
public class Address {
	private final String houseName;
	private final String streetName;
	private final String placeName;
	private final String postcode;
	
	/**
     * Constructor
     * @param house/apartment/room number i.e 3A
     * @param name of street i.e High Street
     * @param post town, i.e Southhampton
     * @param postcode, i.e SO31 4NG
     *
     */
	public Address(String houseName, String streetName, String placeName, String postcode) {
		this.houseName = houseName;
		this.streetName = streetName;
		this.placeName = placeName;
		this.postcode = postcode;
	}
	
	public String getHouseName() {
		return this.houseName;
	}
	
	public String getStreetName() {
		return this.streetName;
	}
	
	public String getPlaceName() {
		return this.placeName;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHouseName());
		sb.append(", ");
		sb.append(getStreetName());
		sb.append(", ");
		sb.append("\n");
		sb.append(getPlaceName());
		sb.append(", ");
		sb.append("\n");
		sb.append(getPostcode());
		
		return sb.toString();
	}
}