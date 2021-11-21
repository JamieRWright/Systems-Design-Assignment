package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
* Class designed to create booking objects
*
*/


public class Booking {
	private int propertyID;
	private int hostID;
	private int guestID;
	private String startDate;
	private String endDate;
	
	public Booking (int propertyID, int hostID, int guestID, String startDate, String endDate) {
		this.propertyID = propertyID;
		this.hostID = hostID;
		this.guestID = guestID;
		this.startDate = startDate;
		this.endDate = endDate;
		
		TDatabase.AddBooking(propertyID, hostID, guestID, startDate, endDate);
	}
	
	//public int getPropertyID() {return this.propertyID;}
	//public int getHostID() {return this.HostID}
	//public int getGuestID() {}
	public String getStartDate() {return this.startDate;}
	public String getEndDate() {return this.endDate;}
	
	// Method to check if two bookings overlap
	public static boolean overlap(String s1, String e1, String s2, String e2) throws ParseException {
		// Change into Date objects
		Date start1 = toDate(s1);
		Date start2 = toDate(s2);
		Date end1 = toDate(e1);
		Date end2 = toDate(e2);
		
		if (end1.before(start2) || end2.before(start1)) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	// Method to check if a booking date has already passed
	public static boolean hasPassed(String d) throws ParseException {
		return (toDate(d).before(new Date()));
	}
	
	public static Date toDate(String d) throws ParseException {
		return (new SimpleDateFormat("yyyy-MM-dd").parse(d));
	}
	
	public static void main(String[]args) throws ParseException {
		boolean a = Booking.overlap("2021-11-20", "2021-11-27", "2021-11-23", "2022-12-1");
		
		
		Date d1 = toDate("2021-09-20");
		Date d2 = toDate("2021-11-23");
		
		//System.out.println(a);
		
		System.out.println(new Date());
		
		//TDatabase.DeleteBooking(1, 3);
		//TDatabase.UpdateValue("Guest", "FirstName", "test_email@gmail.com", "Ash");
		//TDatabase.addProperty(3, "44", "Hunt St.", "45654", "Warwick", "England", "Tiny House", "A very TINY house");
		//System.out.println(TDatabase.GetPropertyID(1, "Family Home", "Quiet Residential 4 Bed Semi-Detached", "123"));
	}
	
}
