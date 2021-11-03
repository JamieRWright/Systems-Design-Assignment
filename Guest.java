/**
 * Class designed to be used to create Guest objects when a user signs up as guest
 *
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Guest extends User {
	
	/**
     * Constructor
     * 
     * @param surname guest last name
     * @param forename guest first name, provided during sign-up, i.e "Sandra"
     * @param guest's address, confidential
     * @param userId, provided when the user inputs their email
     * @param password as provided by the user when they sign up
     *
     */
	public Guest(String surname, String forename, Address address, String userID, String password) {
		super(surname, forename, address, userID, password);
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Guest Name: ");
		sb.append(getName());
		
		return sb.toString();
	}
	
	public static void main (String [] arg) {
		Address add = new Address("71", "Cherry Court", "SOUTHHAMPTON", "SO53 5PD");
		Guest alec = new Guest("Hardison", "Alec", add, "hardison12@gmail.com", "ageofthegeekbaby36482");
		System.out.println(alec);
	}
}