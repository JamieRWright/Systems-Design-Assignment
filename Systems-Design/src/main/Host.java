
/**
 * Class designed to create Host objects when a user signs up as host
 * 
 */

ppackage main;
public class Host extends User {
	private final Rating rating;
	
	 /**
     * Constructor
     * @param surname host's last name, provided during sign-up
     * @param forename host's first name
     * @param address, host's address, confidential
     * @param phone, host's phone number for contact, confidential
     * @param userId, provided when the user inputs their email
     * @param password as provided by the user when they sign up
     * @param rating of type Rating
     * @param boolean whether they are a superhost or not; if new then defaults as false
     *
     */
	
	public Host(String surname, String forename, Address address, String phone, String userID, String password, Rating rating) {
		super(surname, forename, address, phone, userID, password);
		this.rating = rating;
	}
	
	public String getID()
	{
		return TDatabase.SearchUserID("Host", forename);
	}

	
	public Rating getRating() {
		return this.rating;
	}

	// check if a host achieved the superhost title, if so, update in DB aswell
	public boolean isSuperhost() {
		if (getRating().getHostOverall() >= 4.7) {
			TDatabase.UpdateValue("Host", "isSuperHost", this.getID(), 1);
			return true;}
		else {return false;}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Host Name: ");
		sb.append(getName());
		sb.append("\n");
		sb.append(getRating());
		
		if (isSuperhost()) {
			sb.append("\n");
			sb.append("This user has achieved the superhost status!");
		}
		
		return sb.toString();
	}
	
}