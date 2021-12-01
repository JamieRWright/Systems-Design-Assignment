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
	private List<Bedroom> sleeping;
	private List<Bathroom> bathing;
	private Kitchen kitchen;
	private Living living;
	private Utility utility;
	private Outdoor outdoor;
	private Integer propertyID;
	
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
	public Facilities (Integer propertyID, List<Bedroom> sleeping, List<Bathroom> bathing, Kitchen kitchen, Living living, Utility utility, Outdoor outdoor) {
		this.sleeping = sleeping;
		this.bathing = bathing;
		this.kitchen = kitchen;
		this.living = living;
		this.utility = utility;
		this.outdoor = outdoor;
		this.propertyID = propertyID;
		
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
		return this.propertyID;
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
	public void setPropertyID(Integer ID) {
		this.propertyID = ID;
	}
	
	public void setKitchen(Kitchen k, Integer PropertyID) {
		k.setStove(k.getStove(), PropertyID);
		k.setDishwasher(k.getDishwasher(), PropertyID);
		k.setTableware(k.getTableware(), PropertyID);
		k.setCookware(k.getCookware(), PropertyID);
		k.setBasicProvision(k.getBasicProvisions(), PropertyID);
		this.kitchen = k;
	}

	public void setLivingFacility(Living l, Integer PropertyID) {
		l.setWifi(l.getWifi(), PropertyID);
		l.setTV(l.getTelevision(), PropertyID);
		l.setSat(l.getSatellite(), PropertyID);
		l.setStream(l.getStreaming(), PropertyID);
		l.setDvd(l.getDvdPlayer(), PropertyID);
		l.setBoard(l.getBoardGames(), PropertyID);
		this.living = l;
		this.living = l;
	}

	public void setUtility(Utility u, Integer PropertyID) {
		u.setCentral(u.getCentralHeating(), PropertyID);
		u.setWashing(u.getWashingMachine(), PropertyID);
		u.setDrying(u.getDryingMachine(), PropertyID);
		u.setFire(u.getFireExtinguisher(), PropertyID);
		u.setSmoke(u.getSmokeAlarm(), PropertyID);
		u.setFirst(u.getFirstAid(), PropertyID);
		this.utility = u;
	}

	public void setOutdoor(Outdoor o, Integer PropertyID) {
		o.setBBQ(o.getBbq(), PropertyID);
		o.setPatio(o.getPatio(), PropertyID);
		o.setParking(o.getParking(), PropertyID);
		this.outdoor = o;
	}


	// Add a bathroom or bedroom


	public void addBedroom(Bedroom br, Integer PropertyID) {
		br.setLinen(br.getBedLinen(), PropertyID);
		br.setTowels(br.getTowels(), PropertyID);
		br.setBed1Type(br.getBedOne(), PropertyID);
		br.setBed2Type(br.getBedTwo(), PropertyID);
		this.sleeping.add(br);
	}



	public void addBathroom(Bathroom b, Integer PropertyID) {
		b.setBath(b.getBath(), PropertyID);
		b.setHair(b.getHairDrier(), PropertyID);
		b.setShampoo(b.getShampoo(), PropertyID);
		b.setShared(b.getShared(), PropertyID);
		b.setShower(b.getShower(), PropertyID);
		b.setToilet(b.getToilet(), PropertyID);
		b.setToiletPaper(b.getToiletPaper(), PropertyID);
		this.bathing.add(b);
	}
}
