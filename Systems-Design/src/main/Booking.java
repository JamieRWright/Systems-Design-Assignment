package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

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
	private boolean provisional;
	private boolean rejected;
	static public Set<String> Columns = Set.of("Provisional", "Rejected");

	
	public Booking (int propertyID, int hostID, int guestID, String startDate, String endDate, boolean provisional, boolean rejected, boolean updateBackend) {
		this.propertyID = propertyID;
		this.hostID = hostID;
		this.guestID = guestID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.provisional = provisional;
		this.rejected = rejected;
		if (updateBackend)
			TDatabase.AddBooking(propertyID, guestID, startDate, endDate);
	}
	
	public int getPropertyID() {return this.propertyID;}
	public int getHostID() {return this.hostID;}
	public int getGuestID() {return this.guestID;}
	public String getStartDate() {return this.startDate;}
	public String getEndDate() {return this.endDate;}
	public boolean getProvisional() {return this.provisional;}
	public boolean getRejected() {return this.rejected;}
	
	public int getID() {return TDatabase.GetBookingID(this.propertyID, this.guestID);}
	
	public void setProvisional(boolean p) {this.provisional = p;}
	public void setRejected(boolean r) {this.rejected = r;}
	
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
	
	public static boolean before(String start, String end) throws ParseException {
		return (toDate(start).before(toDate(end)));
	}
	
	public static Date toDate(String d) throws ParseException {
		return (new SimpleDateFormat("yyyy-MM-dd").parse(d));
	}
	
	public static int getNightsNum(String sDate, String eDate) throws ParseException {
		int nights = 0;
		Date s = toDate(sDate);
		Date e = toDate(eDate);
		
		Calendar c = Calendar.getInstance();
		
		while (s.before(e)) {
			c.setTime(s);
			c.add(Calendar.DATE, 1);
			nights++;
			s = c.getTime();
		}
		
		return nights;
	}	
}
