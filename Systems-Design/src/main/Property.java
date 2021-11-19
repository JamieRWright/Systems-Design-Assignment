package main;
import java.util.ArrayList;
import java.util.List;

/**
 * Class designed to be used to create Property objects
 * which refers to the house offered by hosts
 * @version 1.0 
 *
 *
 */

public class Property {
	private String shortName;
	private String description;
	private Host host;
	private String publicLocation;
	private Address address;
	private boolean breakfast;
	private int maxSleepers;
	private Rating pRating;
	private List<DatesAvailable> datesAvailable;
	
	/**
     * Constructor
     * @param public loccation, the general location of property (i.e Sheffield)
     * @param address, complete address of property (confidential)
     * @param maxSleepers, the maximum number of people who can sleep at property simultaneously
     * @param rating, average ratings and individual ratings for the property
     * @param availableList, containing the DatesAvailable lists, for every date the property is available for
     *
     */
	public Property(String shortName, String description, Host host, String publicLocation, Address address, boolean breakfast, int maxSleepers, Rating pRating, List<DatesAvailable> datesAvailable) {
		this.shortName = shortName;
		this.description = description;
		this.host = host;
		this.publicLocation = publicLocation;
		this.address = address;
		this.breakfast = breakfast;
		this.maxSleepers = maxSleepers;
		this.pRating = pRating;
		this.datesAvailable = datesAvailable;
		String hostID = host.getID();
		
		TDatabase.addProperty(maxSleepers, hostID, hostID, hostID, publicLocation, hostID, shortName, description);
		//TDatabase.addFacilities
		
	}
	
	public Property(int i, String houseNo, String street, String postcode, String city, String country, String s_name,
			String description) {
		TDatabase.addProperty(i, houseNo, street, postcode, city, country, s_name, description);
	}

	public String getShortName() {
		return this.shortName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Host getHost() {
		return this.host;
	}
	
	public String getPublicLocation() {
		return this.publicLocation;
	}
	
	public Address getFullAddress() {
		return this.address;
	}
	
	public int getMaxSleepers() {
		return this.maxSleepers;
	}
	
	public Rating getPropertyRating() {
		return this.pRating;
	}
	
	public List<DatesAvailable> getAvailableDates() {
		return this.datesAvailable;
	}
	
	public int getID() {
		//t.getPropertyID((this.host).getID(), this.shortName);
		return 0;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getShortName());
		sb.append("\n");
		sb.append(getDescription());
		sb.append("\n");
		sb.append(getPublicLocation());
		sb.append("\n");
		sb.append("Host: ");
		sb.append(getHost().getName());
		sb.append("\n");
		sb.append("Maximum number of sleepers: ");
		sb.append(getMaxSleepers());
		sb.append("\n");
		sb.append("Rating: ");
		sb.append("\n");
		sb.append(getPropertyRating());
		
		return sb.toString();
	}

	public boolean breakfastServed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}