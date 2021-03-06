package main;

/**
 * Class designed to be used to create Guest objects when a user signs up as guest
 *
 */
public class Guest extends User {
	private boolean signUpSuccess;
	/**
     * Constructor
     * 
     * @param surname, guest last name
     * @param forename, guest first name, provided during sign-up, i.e "Sandra"
     * @param address, guest's address, confidential
     * @param phone, guest's phone number for contact, confidential
     * @param userId, provided when the user inputs their email
     * @param password, as provided by the user when they sign up
     *
     */
	public Guest(String surname, String forename, Address address, String phone, String email, String password) {
		super(surname, forename, address, phone, email);
		
		this.signUpSuccess = TDatabase.signUpGuest(surname, forename, phone, email, address.getID(), password);
		this.userID=TDatabase.SearchUserID("Guest", email);
	
	}
	
		
	public Guest(String surname, String forename, Address address, String phone, String email) {
		super(surname, forename, address, phone, email);
		this.signUpSuccess = true;
	}
	
	public boolean getSuccess() {return this.signUpSuccess;}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Guest Name: ");
		sb.append(getName());
		
		return sb.toString();
	}
	public String getID()
	{
		
		return TDatabase.SearchUserID("Guest", email);
	}
}
