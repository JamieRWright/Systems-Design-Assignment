package main;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ChargeBand {
	private String startDate;
	private String endDate;
	private int propertyID;
	private double pricePerNight;
	private double serviceCharge;
	private double cleaningCharge;
	
	public ChargeBand (String startDate, String endDate, int propertyID, double pricePerNight, double serviceCharge, double cleaningCharge, boolean updateBackend) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.propertyID = propertyID;
		this.pricePerNight = pricePerNight;
		this.serviceCharge = serviceCharge;
		this.cleaningCharge = cleaningCharge;
		
		double totalPrice = pricePerNight + serviceCharge + cleaningCharge;
		
		if (updateBackend) {
			TDatabase.AddChargeBand(startDate, endDate, propertyID, pricePerNight, serviceCharge, cleaningCharge);
		}	
	}
	
	public String getStartDate() {return this.startDate;}
	public String getEndDate() {return this.endDate;}
	public int getPropertyID() {return this.propertyID;}
	public double getPPN() {return this.pricePerNight;}
	public double getSC() {return this.serviceCharge;}
	public double getCC() {return this.cleaningCharge;}
	public double getTotalPrice() {return (this.pricePerNight) + (this.serviceCharge) + (this.cleaningCharge);}
	
	public String getID() {return TDatabase.GetChargeBandID(this.propertyID, this.startDate, this.endDate);}
	
	// Checks if the charge band for a property is complete
	public boolean isComplete(int propertyID) throws ParseException {
		boolean continuous = false;
		Map<Integer, ChargeBand> chargeBandSet = new HashMap<Integer, ChargeBand>();
		for (ChargeBand cb : TDatabase.ChargeBands.values()) {
			if (cb.getPropertyID() == propertyID) {
				chargeBandSet.put(Integer.parseInt(cb.getID()), cb);
			}
		}
		
		String[] y2022 = new String[365];
		
		for (int i = 0; i < y2022.length; i++) {
			if (i <= 30) {y2022[i] = "2022-1-" + (i + 1);}
			if (i>30 && i<=58) {y2022[i] = "2022-2-" + (i - 30);}
			if (i>58 && i<=89) {y2022[i] = "2022-3-" + (i - 58);}
			if (i>89 && i<=119) {y2022[i] = "2022-4-" + (i - 89);}
			if (i>119 && i<=150) {y2022[i] = "2022-5-" + (i - 119);}
			if (i>150 && i<=180) {y2022[i] = "2022-6-" + (i - 150);}
			if (i>180 && i<=211) {y2022[i] = "2022-7-" + (i - 180);}
			if (i>211 && i<=242) {y2022[i] = "2022-8-" + (i - 211);}
			if (i>242 && i<=272) {y2022[i] = "2022-9-" + (i - 242);}
			if (i>272 && i<=303) {y2022[i] = "2022-10-" + (i - 272);}
			if (i>303 && i<=333) {y2022[i] = "2022-11-" + (i - 303);}
			if (i>333) {y2022[i] = "2022-12-" + (i - 333);}
		}
		
		for (ChargeBand cb : chargeBandSet.values()) {
			String start = cb.getStartDate();
			String end = cb.getEndDate();
			int between = Booking.getNightsNum(start, end) - 1;
			for (int i = 0; i < y2022.length; i++) {
				if (cb.getStartDate().equals(y2022[i])) {
					y2022[i] = "covered";
					for (int j = i; j < between + 1; j++) {y2022[j] = "covered";};
				}
			}
			
		}
		
		int count = 0;
		
		
		while (y2022[count].equals("completed") && count < y2022.length) {
			count++;
		}
		
		if (count == y2022.length) {continuous = true;}
		
		return continuous;
	}
	
}
