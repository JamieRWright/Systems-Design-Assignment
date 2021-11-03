/**
 * Class designed for Host and Guest to inherit from
 *
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class User {
	protected final String surname;
	protected final String forename;
	protected final String userID;
	protected final String password;
	protected final Address address;
	
	/**
     * Constructor
     * 
     * @param surname User's last name
     * @param forename User's first name
     * @param userId, provided when the user inputs their email
     * @param password as provided by the user when they sign up
     * @param address User's confidential address
     *
     */
	public User(String surname, String forename, Address address, String userID, String password) {
		this.surname = surname;
		this.forename = forename;
		this.userID = userID;
		this.password = password;
		this.address = address;
	}
	
	public String getName() {
		return (this.forename) + " " + (this.surname);
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public String getID() {
		return this.userID;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
}