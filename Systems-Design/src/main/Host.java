
/**
 * Class designed to create Host objects when a user signs up as host
 * 
 */

package main;
public class Host extends User {
	private final Rating rating;
	boolean signupSuccess;
	
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
	public Host(String surname, String forename, Address address, String phone, String userID, String password) {
		super(surname, forename, address, phone, userID, password);
		this.rating = null;
		this.signupSuccess = TDatabase.signUpHost(forename, surname, userID, password);
	}
	public Host(String surname, String forename, Address address, String phone, String userID) {
		super(surname, forename, address, phone, userID);
		this.rating = null;
		this.signupSuccess = true;
		
	}

	
	public Rating getRating() {
		return this.rating;
	}
	
	public boolean getSuccess() {
		return this.signupSuccess;
	}

	// check if a host achieved the superhost title
	public boolean isSuperhost() {
		if (getRating().getHostOverall() >= 4.7) {return true;}
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
