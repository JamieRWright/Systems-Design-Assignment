package main;
import java.util.List;
/**
 * Class designed to be used to connect a property to facilities
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Facilities {
	private Property property;
	private List<Bedroom> sleeping;
	private List<Bathroom> bathing;
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
	public Facilities (Property property, List<Bedroom> sleeping, List<Bathroom> bathing, Kitchen kitchen, Living living, Utility utility, Outdoor outdoor) {
		this.sleeping = sleeping;
		this.bathing = bathing;
		this.kitchen = kitchen;
		this.living = living;
		this.utility = utility;
		this.outdoor = outdoor;
		this.property = property;
		
	}
	// Methods to get bedroom and bathroom numbers
	public int getBedroomNum() {
		return (this.sleeping).size();
	}
	
	public int getBathroomNum() {
		return ((this.bathing).size());
	}
	
	// Accessor methods
	public Integer getPropertyID() {
		return (this.property).getID();
	}
	
	public Bedroom getBedroom(int bedroomNo) {
		return (this.sleeping).get(bedroomNo-1);
	} 
	
	public Bathroom getBathroom(int bathroomNo) {
		return (this.bathing).get(bathroomNo-1);
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
