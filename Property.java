import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class designed to be used to create Property objects
 * which refers to the house offered by hosts
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Property {
	final String propertyName;
	final String description;
	final Host host;
	final String publicLocation;
	final Address address;
	final boolean breakfast;
	final int maxSleepers;
	final Rating pRating;
	final List<DatesAvailable> availableList;
	
	/**
     * Constructor
     * @param public loccation, the general location of property (i.e Sheffield)
     * @param address, complete address of property (confidential)
     * @param maxSleepers, the maximum number of people who can sleep at property simultaneously
     * @param rating, average ratings and individual ratings for the property
     * @param availableList, containing the DatesAvailable lists, for every date the property is available for
     *
     */
	public Property(String propertyName, String description, Host host, String publicLocation, Address address, boolean breakfast, int maxSleepers, Rating pRating, List<DatesAvailable> availableList) {
		this.propertyName = propertyName;
		this.description = description;
		this.host = host;
		this.publicLocation = publicLocation;
		this.address = address;
		this.breakfast = breakfast;
		this.maxSleepers = maxSleepers;
		this.pRating = pRating;
		this.availableList = availableList;
	}
	
	public String getPropertyName() {
		return this.propertyName;
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
	
	public Rating getRating() {
		return this.rating;
	}
	
	public List<DatesAvailable> getAvailableDates() {
		return this.availableList;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPropertyName());
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
		sb.append(getRating());
		
		return sb.toString();
	}
	
}