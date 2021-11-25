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
	private final String postcode;
	private final String city;
	private final String country;
	
	/**
     * Constructor
     * @param house/apartment/room number i.e 3A
     * @param name of street i.e High Street
     * @param post town, i.e Southhampton
     * @param postcode, i.e SO31 4NG
     *
     */
	public Address(String houseName, String streetName, String postcode, String city, boolean UpdateBackend) {
		this.houseName = houseName;
		this.streetName = streetName;
		this.postcode = postcode;
		this.city=city;
		//If initialising global lists don't add to back end
		if (UpdateBackend)
		{
			TDatabase.addAddress(houseName, streetName, postcode, city);
		}
	}
	
	public String getHouseName() {
		return this.houseName;
	}
	
	public String getStreetName() {
		return this.streetName;
	}
	
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String getCountry() {
		return this.country;
	}

	public String getPublicLocation() {
		return getCity() + ", "+ getStreetName();
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHouseName());
		sb.append(", ");
		sb.append(getStreetName());
		sb.append(", ");
		sb.append("\n");
		sb.append(getCity());
		sb.append(", ");
		sb.append("\n");
		sb.append(getPostcode());
		
		return sb.toString();
	}

	public String getCity() {
		return this.city;
	}

	public String getID() {
		return TDatabase.SearchAddressID(getHouseName(), getPostcode());
	}
}
