/**
 * Class designed to be used to create Address objects,
 * consist of house number, street name, post town, postcode
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Address {
	private final String houseNumber;
	private final String streetName;
	private final String postTown;
	private final String postcode;
	
	/**
     * Constructor
     * @param house/apartment/room number i.e 3A
     * @param name of street i.e High Street
     * @param post town, i.e Southhampton
     * @param postcode, i.e SO31 4NG
     *
     */
	public Address(String houseNumber, String streetName, String postTown, String postcode) {
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.postTown = postTown;
		this.postcode = postcode;
	}
	
	public String getHouseNo() {
		return this.houseNumber;
	}
	
	public String getStreetName() {
		return this.streetName;
	}
	
	public String getPostTown() {
		return this.postTown;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHouseNo());
		sb.append(", ");
		sb.append(getStreetName());
		sb.append(", ");
		sb.append("\n");
		sb.append(getPostTown());
		sb.append(", ");
		sb.append("\n");
		sb.append(getPostcode());
		
		return sb.toString();
	}
}