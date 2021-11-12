/**
 * Class designed to be used to connect a property to facilities
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Facilities {
	final Property property;
	final Bedroom[] sleeping;
	final Bathroom[] bathing;
	final Kitchen kitchen;
	final Living living;
	final Utility utility;
	final Outdoor outdoor;
	TDatabase t = new TDatabase();
	
	/**
     * Constructor
     * @param property, property to which facility is to be added
     * @param sleeping, an array of all the bedrooms inside the property
     * @param bathing, and array of all bathrooms inside the property
     * @param kitchen, the kitchen facility of property
     * @param living, living facility of property
     * @param utility, utility facility of property
     * @param outdoor, outdoor facility of property
     *
     */
	public Facilities (Property property, Bedroom[] sleeping, Bathroom[] bathing, Kitchen kitchen, Living living, Utility utility, Outdoor outdoor) {
		this.property = property;
		this.sleeping = sleeping;
		this.bathing = bathing;
		this.kitchen = kitchen;
		this.living = living;
		this.utility = utility;
		this.outdoor = outdoor;
		
		t.addFacility(property.getID(), sleeping, bathing, kitchen, living, utility, outdoor);
	}
	
	public int getBedroomNum() {
		return (this.sleeping).length;
	}
	
	public int getBathroomNum() {
		return ((this.bathing).length);
	}
	
	public Property getProperty() {
		return this.property;
	}
	
	public Bedroom getBedroom(int bedroomNo) {
		return (this.sleeping)[bedroomNo - 1];
	} 
	
	public Bathroom getBathroom(int bathroomNo) {
		return (this.bathing)[bathroomNo - 1];
	}
	
	public Kitchen getKitchen() {
		return this.kitchen;
	}
	
	public Living getLivingFacility() {
		return this.living;
	}
	
	public Utility getUtilityFacility() {
		return this.utility;
	}
	
	public Outdoor getOutdoorFacility() {
		return this.outdoor;
	}
}