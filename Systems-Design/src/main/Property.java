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
	private Facilities facility;
	
	/**
     * Constructor
     * @param public location, the general location of property (i.e Sheffield)
     * @param address, complete address of property (confidential)
     * @param maxSleepers, the maximum number of people who can sleep at property simultaneously
     * @param rating, average ratings and individual ratings for the property
     * @param availableList, containing the DatesAvailable lists, for every date the property is available for
     *
     */
	public Property(String shortName, String description, Host host,Address address, int breakfast, 
			Facilities facility, boolean updateBackend) {
		this.shortName = shortName;
		this.description = description;
		this.host = host;
		this.address = address;
		if (this.address != null) 
			this.publicLocation = address.getPublicLocation();
		if (breakfast==1)
			this.breakfast=true;
		else 
			this.breakfast=false;
		this.maxSleepers = 0;
		this.pRating = null;
		this.facility = facility;
		hostID = host.getID();
		if (updateBackend)
		{
			TDatabase.addProperty(Integer.parseInt(hostID), address.getID(), shortName, description, breakfast, address.getHouseName());
		}
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
		return address.getPublicLocation();
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
	
	
	public void setFacilities(Facilities facilities) {
		this.facility = facilities; 
	}
	
	public Facilities getFacilities(){
		return this.facility;
	}
	
	public Integer getID() {
		return Integer.parseInt(TDatabase.GetPropertyID(address.getID()));
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
