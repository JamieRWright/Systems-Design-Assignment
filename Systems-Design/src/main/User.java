/**
 * Class designed for Host and Guest to inherit from
 *
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
package main;
public class User {
	protected String surname;
	protected String forename;
	protected String userID;
	protected String password;
	protected String phone;
	protected Address address;
	
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
	public User(String forename, String surname, Address address, String phone, String userID, String password) {
		this.surname = surname;
		this.forename = forename;
		this.phone = phone;
		this.userID = userID;
		this.password = password;
		this.address = address;
	}
	public User(String forename, String surname, Address address, String phone, String userID) {
		this.surname = surname;
		this.forename = forename;
		this.phone = phone;
		this.userID = userID;
		this.address = address;
	}
	
	// Accessor methods
	public String getName() {
		return (this.forename) + " " + (this.surname);
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getID() {
		return this.userID;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	// Set methods
	
	public void setName(String fname, String sname) {
        this.forename = fname;
        this.surname = sname;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}
	
}
