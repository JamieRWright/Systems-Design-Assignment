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
	
	public Rating getPropertyRating() {
		return this.pRating;
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
		sb.append(getPropertyRating());
		
		return sb.toString();
	}
	
	public static void main (String [] args) {
		Address add = new Address("71", "Cherry Court", "SOUTHHAMPTON", "SO53 5PD");
		List<DatesAvailable> l = new ArrayList<DatesAvailable>();
		l.add(new DatesAvailable("3/11/21", "16/11/21"));
        List<RatingMap> rate = new ArrayList<RatingMap>();
		
		RatingCategory[] categories = {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn, RatingCategory.Accuracy, RatingCategory.Location,RatingCategory.Value};
		int[] r = {5, 5, 4, 4, 5, 5};
		int[] r1 = {4, 5, 4, 4, 5, 4};
		int[] r2 = {3, 5, 1, 5, 5, 4};
		int[] r3 = {1, 5, 5, 5, 5, 4};
		int[] r4 = {5, 5, 5, 5, 5, 5};
		int[][] ratingsSet = {r, r1, r2, r3, r4};
		
		for (int i = 0; i < ratingsSet.length; i++) {
			RatingMap ratings = new RatingMap();
			for (int j = 0; j < r.length; j++) {
				ratings.put(categories[j], ratingsSet[i][j]);
			}
			rate.add(ratings);
		}
        Rating rating = new Rating(rate);
        Host soph = new Host("Devereaux", "Sophie", add, "charlotteprentis_32@gmail.com", "hafuiewhfb", rating);
		
		Property mine = new Property("Small room", "Just a small room", soph, "Gombak", add, false, 2, rating, l);
		System.out.println(mine);
		
	}
}