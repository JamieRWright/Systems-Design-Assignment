/**
 * Enum with constants representing the the six rating categories 
 *
 * @version 1.0
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public enum RatingCategory {
	Cleanliness("Cleanliness"),
	Communication("Communication (response time)"),
	CheckIn("Check-In (how easy)"),
	Accuracy("Accuracy (of public info)"),
	Location("Location (quality)"),
	Value("Value (for money)");
	
	private final String name;
	RatingCategory(String n) {name = n;}
	
	public String getName() {return name;}
	
}