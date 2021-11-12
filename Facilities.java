/**
 * Class designed to be used to connect a property to facilities
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Facilities extends Property {
	private Property property;
	private Bedroom[] sleeping;
	private Bathroom[] bathing;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;
	
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
	public Facilities (Property p, Bedroom[] sleeping, Bathroom[] bathing, Kitchen kitchen, Living living, Utility utility, Outdoor outdoor) {
		super(p.getShortName(), p.getDescription(), p.getHost(), p.getPublicLocation(), p.getFullAddress(), p.breakfastServed(), p.getMaxSleepers(), p.getPropertyRating(), p.getAvailableDates());
		this.sleeping = sleeping;
		this.bathing = bathing;
		this.kitchen = kitchen;
		this.living = living;
		this.utility = utility;
		this.outdoor = outdoor;
		
	}
	
	// Methods to get bedroom and bathroom numbers
	public int getBedroomNum() {
		return (this.sleeping).length;
	}
	
	public int getBathroomNum() {
		return ((this.bathing).length);
	}
	
	// Accessor methods
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
	
	// Set methods
	public void setProperty(Property p) {
		this.property = p;
	}
	
	public void setKitchen(Kitchen k) {
		this.kitchen = k;
	}
	
	public void setLivingFacility(Living l) {
		this.living = l;
	}
	
	public void setUtility(Utility u) {
		this.utility = u;
	}
	
	public void setOutdoor(Outdoor o) {
		this.outdoor = o;
	}
	
	// Add a bathroom or bedroom
	
	public void addBedroom(Bedroom br) {
		Bedroom[] newBedrooms = new Bedroom[(this.sleeping).length + 1];
		
		for (int i = 0; i <= (this.sleeping).length; i++) {
			if (i < (this.sleeping).length) {
				newBedrooms[i] = (this.sleeping)[i];
			}
			else {
				newBedrooms[i] = br;
			}
		}
	}
	
	public void addBathroom(Bathroom b) {
        Bathroom[] newBathrooms = new Bathroom[(this.bathing).length + 1];
		
		for (int i = 0; i <= (this.bathing).length; i++) {
			if (i < (this.bathing).length) {
				newBathrooms[i] = (this.bathing)[i];
			}
			else {
				newBathrooms[i] = b;
			}
		}
	}
}