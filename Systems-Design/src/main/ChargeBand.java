package main;

public class ChargeBand {
	private String startDate;
	private String endDate;
	private int propertyID;
	private int pricePerNight;
	private int serviceCharge;
	private int cleaningCharge;
	
	public ChargeBand (String startDate, String endDate, int propertyID, int pricePerNight, int serviceCharge, int cleaningCharge, boolean updateBackend) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.propertyID = propertyID;
		this.pricePerNight = pricePerNight;
		this.serviceCharge = serviceCharge;
		this.cleaningCharge = cleaningCharge;
		
		int totalPrice = pricePerNight + serviceCharge + cleaningCharge;
		
		if (updateBackend) {
			TDatabase.AddChargeBand(startDate, endDate, totalPrice, propertyID, pricePerNight, serviceCharge, cleaningCharge);
		}	
	}
	
	public String getStartDate() {return this.startDate;}
	public String getEndDate() {return this.endDate;}
	public int propertyID() {return this.propertyID;}
	public int getPPN() {return this.pricePerNight;}
	public int getSC() {return this.serviceCharge;}
	public int getCC() {return this.cleaningCharge;}
	public int getTotalPrice() {return (this.pricePerNight) + (this.serviceCharge) + (this.cleaningCharge);}
	
}
