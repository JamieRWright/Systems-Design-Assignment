package main;
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
	String hostID;
	private String publicLocation;
	private Address address;
	private boolean breakfast;
	private int maxSleepers;
	private Rating pRating;
	private Bathroom bath;
	private Bedroom bed;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;
	private Facilities facility;
	private List<DatesAvailable> datesAvailable;
	
	/**
     * Constructor
     * @param public location, the general location of property (i.e Sheffield)
     * @param address, complete address of property (confidential)
     * @param maxSleepers, the maximum number of people who can sleep at property simultaneously
     * @param rating, average ratings and individual ratings for the property
     * @param availableList, containing the DatesAvailable lists, for every date the property is available for
     *
     */
	public Property(String shortName, String description, Host host, 
			String publicLocation, Address address, boolean breakfast,
			Bathroom bath, Bedroom bed, Kitchen kitchen, Living living,
			Utility utility, Outdoor outdoor, Facilities facility) {
		this.shortName = shortName;
		this.description = description;
		this.host = host;
		this.publicLocation = publicLocation;
		this.address = address;
		this.breakfast = breakfast;
		this.maxSleepers = 0;
		this.pRating = null;
		this.datesAvailable = null;
		this.bath = bath;
		this.bed = bed;
		this.kitchen = kitchen;
		this.living = living;
		this.utility = utility;
		this.outdoor = outdoor;
		this.facility = facility;
		hostID = host.getID();
		
		TDatabase.addProperty(maxSleepers, hostID, hostID, hostID, publicLocation, hostID, shortName, description);
		
	}
	
	public Property(int i, String houseNo, String street, String postcode, String city, String country, String s_name,
			String description, boolean updateBackend) {
		if (updateBackend)
		{
			TDatabase.addProperty(i, houseNo, street, postcode, city, country, s_name, description);
		}
		this.shortName=s_name;
		this.address = new Address(houseNo, street, s_name, postcode);
		this.description = description;
		this.hostID = String.valueOf(i);
		
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
		publicLocation = (this.address).getCity() + (this.address).getStreetName();
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
	
	public boolean getBreakfast() {
		return this.breakfast;
	}
	
	public List<DatesAvailable> getAvailableDates() {
		return this.datesAvailable;
	}
	
	public Bathroom getBathroom() {
		return this.bath;
	}	
	public Bedroom getBedroom() {
		return this.bed;
	}
	public Kitchen getKitchen() {
		return this.kitchen;
	}
	public Living getLiving() {
		return this.living;
	}
	public Utility getUtility() {
		return this.utility;
	}
	public Outdoor getOutdoor() {
		return this.outdoor;
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
		return false;
	}
	
}
