package main;
/**
 * Class designed for Host and Guest to inherit from
 *
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */
public class User {
	protected String surname;
	protected String forename;
	protected String userID;
	protected String password;
	protected String phone;
	protected String email;
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
	public User(String forename, String surname, Address address, String phone, String userID, String email, String password) {
		this.surname = surname;
		this.forename = forename;
		this.phone = phone;
		this.userID = userID;
		this.email=email;
		this.password = password;
		this.address = address;
	}
	
	public User(String forename, String surname, Address address, String phone, String email, String password) {
		this.surname = surname;
		this.forename = forename;
		this.phone = phone;
		this.email=email;
		this.password = password;
		this.address = address;
	}
	public User(String forename, String surname, Address address, String phone, String email) {
		this.surname = surname;
		this.forename = forename;
		this.phone = phone;
		this.email=email;
		this.address = address;
	}
	
	
	// Accessor methods
	public String getName() {
		return (this.surname) + " " + (this.forename);
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getEmail() {
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
