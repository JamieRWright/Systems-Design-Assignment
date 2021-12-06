package main;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//Static class created to be used throughout the project
//Methods are called like this: TDatabase.SearchFullTable("Guest")
//Global
public final class TDatabase {
	private static final String connectionString="jdbc:mysql://stusql.dcs.shef.ac.uk/team054";
	private static final String username="team054";
	private static final String password="c77880a3";
	public static Map<Integer, Property> Properties = null;
	public static Map<Integer, Property> Bookmarks = null;
	public static Map<Integer, Booking> Bookings = null;
	public static Map<Integer, ChargeBand> ChargeBands = null;
	public static Map<Integer, Review> Reviews = null;
	public static Map<Integer, Guest> Guests = null;
	public static Map<Integer, Host> Hosts = null;

	private static Connection con;


	public static boolean Initialise()
	{
		boolean isSuccess = true;
		isSuccess=getConnection();
		Hosts = TDatabase.LoadHosts();
		Properties = TDatabase.LoadProperties();
		Guests = TDatabase.LoadGuests();
		Bookings = TDatabase.LoadBookings();
		ChargeBands = TDatabase.LoadChargeBands();
		Reviews = TDatabase.LoadReviews();
		
		return isSuccess;
	}
	
	public static String encryptThisString(String input) throws NoSuchAlgorithmException
	{
		
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
			
	}
	
		private static Map<Integer, Host> LoadHosts() {
		 Map<Integer, Host> output = new HashMap<Integer, Host>();
		 ResultSet table = null;
		 boolean isSuperHost;
		 table = SearchFullTable("Host", true);
		 try {
			while (table.next()) {
				String HostID = table.getString(1);
				String forename = table.getString(2);
				String surname = table.getString(3);
				String getSuperHost = table.getString(4);
				String email = table.getString(5);
				String AddressID = table.getString(6);
				String phone = table.getString(7);
				
				Address host_add = getAddress(AddressID);
				if (getSuperHost == "1")
					isSuperHost = true;


				output.put(Integer.parseInt(HostID), new Host(surname, forename, host_add, phone, email));
			}
			 disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 
		 return output;
	}
	
	private static Map<Integer, Booking> LoadBookings() {
			Map<Integer, Booking> output = new HashMap<Integer, Booking>();
			ResultSet table = null;
			table = SearchFullTable("Bookings", true);
				try {
					while (table.next()) {
						boolean rejected = false;
						boolean provisional = false;
						Integer BookingID = table.getInt(1);
						Integer PropertyID = table.getInt(2);
						Integer GuestID = table.getInt(3);
						String StartDate = table.getString(4);
						String EndDate = table.getString(5);
						int Provisional = table.getInt(6);
						int Rejected = table.getInt(7);
						
						if (Rejected > 0) {
							rejected = true;
						}
						if (Provisional > 0) {
							provisional = true;
						}
						// System.out.println(HostID + Street + Postcode + City + Country + ShortName);
						try {
							// Delete booking from database if end date has passed
							if (Booking.hasPassed(EndDate)) {
								// And only if Guest has submitted a review
								if (TDatabase.GetReviewID(PropertyID, GuestID) != null) {
									TDatabase.DeleteBooking(PropertyID, GuestID);
								}
							}
							else {
								output.put(BookingID, new Booking(PropertyID, HostID, GuestID, StartDate, EndDate, provisional, rejected, false));
							}
						}
						catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					

			return output;
		}
	
	private static Map<Integer, ChargeBand> LoadChargeBands() {
		Map<Integer, ChargeBand> output = new HashMap<Integer, ChargeBand>();
		ResultSet table = null;
		table = SearchFullTable("Charge_Band", true);
		
		try {
			while (table.next()) {
				Integer ChargeBandID = table.getInt(1);
				String StartDate = table.getString(2);
				String EndDate = table.getString(3);
				Integer PropertyID = table.getInt(4);
				Double PricePerNight = table.getDouble(5);
				Double ServiceCharge = table.getDouble(6);
				Double CleaningCharge = table.getDouble(7);
				
				output.put(ChargeBandID, new ChargeBand(StartDate, EndDate, PropertyID, PricePerNight, ServiceCharge, CleaningCharge, false));
			}
			disconnect();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static Map<Integer, Review> LoadReviews() {
		Map<Integer, Review> output = new HashMap<Integer, Review>();
		ResultSet table = null;
		table = SearchFullTable("Reviews", true);
		
		try {
			while (table.next()) {
				Integer ReviewID = table.getInt(1);
				Integer PropertyID = table.getInt(2);
				Integer GuestID = table.getInt(3);
				Integer Cleanliness = table.getInt(4);
				Integer Communication = table.getInt(5);
				Integer CheckIn = table.getInt(6);
				Integer Accuracy = table.getInt(7);
				Integer Location = table.getInt(8);
				Integer Value_for_money = table.getInt(9);
				String OptionalDescription = table.getString(10);
				RatingCategory[] key = new RatingCategory[] {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn,
						RatingCategory.Accuracy, RatingCategory.Location, RatingCategory.Value};
				Integer[] value = {Cleanliness, Communication, CheckIn, Accuracy, Location, Value_for_money};
				RatingMap rm = new RatingMap();
				
				for (int i = 0; i < value.length; i++) {rm.put(key[i], value[i]);}
				output.put(ReviewID, new Review(GuestID, PropertyID, OptionalDescription, rm));
			}
			disconnect();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	private static Map<Integer, Property> LoadProperties(){
		Map<Integer, Property> output = new HashMap<Integer, Property>();
		ResultSet table = null;
		table = SearchFullTable("Property", true);
			try {
				while (table.next()) {
					Integer PropertyID = table.getInt(1);
					Integer HostID = table.getInt(2);
					String ShortName = table.getString(4);
					String Description = table.getString(5);
					String AddressID = table.getString(6);
					int Breakfast = table.getInt(7);
					// System.out.println(HostID + Street + Postcode + City + Country + ShortName);
					Address address_temp = getAddress(AddressID);
					Host current_host = Hosts.get(HostID);	
					Facilities facilities = loadFacilities(PropertyID);
					output.put(PropertyID, new Property(ShortName, Description, current_host, address_temp, Breakfast, facilities, false));
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

		return output;
	}
private static List<Bathroom> loadBathrooms(Integer PropertyID)
	{
		
		List<Bathroom> output = new ArrayList<Bathroom>();
		ResultSet table = null;
		boolean HDBool = false, ShampBool=false, TPBool = false, TBool = false, BBool = false, SBool = false, SharedBool=false;
		table = SearchFacility("Bathing_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int HairDryer = table.getInt(3);
					int Shampoo = table.getInt(4);
					int ToiletPaper = table.getInt(5);
					int Toilet = table.getInt(6);
					int Bath = table.getInt(7);
					int Shower = table.getInt(8);
					int IsShared = table.getInt(9);
					
					if (HairDryer==1)
						HDBool = true;
					if(Shampoo==1)
						ShampBool = true;
					if (ToiletPaper==1)
						TPBool = true;
					if(Toilet==1)
						TBool = true;
					if(Bath==1)
						BBool = true;
					if(Shower==1)
						SBool = true;
					if(IsShared==1)
						SharedBool = true;
					// System.out.println(HostID + Street + Postcode + City + Country + ShortName);
					output.add(new Bathroom(HDBool, ShampBool, TPBool, TBool, BBool, SBool, SharedBool));
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
	
	private static List<Bedroom> loadBedrooms(Integer PropertyID)
	{
		
		List<Bedroom> output = new ArrayList<Bedroom>();
		ResultSet table = null;
		boolean BLBool = false, TBool= false;
		table = SearchFacility("Sleeping_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int BedLinen = table.getInt(2);
					int Towels = table.getInt(3);
					String Bed1Type = table.getString(4).toUpperCase();
					String Bed2Type = table.getString(5);
					if (Bed2Type != null) {Bed2Type = Bed2Type.toUpperCase();}
					if(BedLinen==1)
						BLBool = true;
					if(Towels==1)
						TBool = true;
					
					BedType Bed1;
					switch(Bed1Type)
					{
						case "SINGLE BED": 
							Bed1=BedType.Single;
							break;
						case "DOUBLE BED":
							Bed1=BedType.Double;
							break;
						case "BUNK BED":
							Bed1=BedType.Bunk;
							break;
						case "KINGSIZE BED":
							Bed1=BedType.King;
							break;
						default:
							Bed1=null;
					}
					
					BedType Bed2=null;
					if (Bed2Type != null)
					{
						switch(Bed2Type)
						{
							case "SINGLE BED": 
								Bed1=BedType.Single;
								break;
							case "DOUBLE BED":
								Bed1=BedType.Double;
								break;
							case "BUNK BED":
								Bed1=BedType.Bunk;
								break;
							case "KINGSIZE BED":
								Bed1=BedType.King;
								break;
							default:
								Bed1=null;
						}
					}

					
					output.add(new Bedroom(BLBool, TBool, Bed1, Bed2));
					}
				disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return output;
	}
	
	private static Kitchen loadKitchen(Integer PropertyID)
	{
		
		Kitchen output = null;
		ResultSet table = null;
		boolean FBool = false, MBool= false, OBool = false, SBool= false, DBool = false, TBool= false, CBool = false, PBool= false;
		table = SearchFacility("Kitchen_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int fridge = table.getInt(2);
					int micro = table.getInt(3);
					int oven = table.getInt(4);
					int stove = table.getInt(5);
					int dishwasher = table.getInt(6);
					int tableware = table.getInt(7);
					int cookware = table.getInt(8);
					int provisions = table.getInt(9);
					
					if(fridge==1)
						FBool = true;
					if(micro==1)
						MBool = true;
					if(oven==1)
						OBool = true;
					if(stove==1)
						TBool = true;
					if(dishwasher==1)
						DBool = true;
					if(tableware==1)
						TBool = true;
					if(cookware==1)
						CBool = true;
					if(provisions==1)
						PBool = true;
					output = new Kitchen(FBool, MBool, OBool, SBool, DBool, TBool, CBool, PBool);
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
	
	private static Living loadLiving(Integer PropertyID)
	{
		
		Living output = null;
		ResultSet table = null;
		boolean WBool = false, TVBool= false, SatBool = false, StreamBool= false, DVDBool = false, GBool= false;
		table = SearchFacility("Living_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int wifi = table.getInt(2);
					int tv = table.getInt(3);
					int sat = table.getInt(4);
					int streaming = table.getInt(5);
					int dvd = table.getInt(6);
					int games = table.getInt(7);
					if(wifi==1)
						WBool = true;
					if(tv==1)
						TVBool = true;
					if(sat==1)
						SatBool = true;
					if(streaming==1)
						StreamBool = true;
					if(dvd==1)
						DVDBool = true;
					if(games==1)
						GBool = true;
					output = new Living(WBool, TVBool, SatBool, StreamBool, DVDBool, GBool);
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
	
	private static Utility loadUtility(Integer PropertyID)
	{
		
		Utility output = null;
		ResultSet table = null;
		boolean HeatBool = false, WBool= false, DBool = false, FEBool= false, ABool = false, FABool= false;
		table = SearchFacility("Utility_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int CHeat = table.getInt(2);
					int Washing = table.getInt(3);
					int Drying = table.getInt(4);
					int fireExtinguish = table.getInt(5);
					int Alarm = table.getInt(6);
					int FirstAid = table.getInt(7);
					if(CHeat==1)
						HeatBool = true;
					if(Washing==1)
						WBool = true;
					if(Drying==1)
						DBool = true;
					if(fireExtinguish==1)
						FEBool = true;
					if(Alarm==1)
						ABool = true;
					if(FirstAid==1)
						FABool = true;
					//(boolean centralHeating, boolean washingMachine, boolean dryingMachine, boolean fireExtinguisher, boolean smokeAlarm, boolean firstAid)
					output = new Utility(HeatBool, WBool, DBool, FEBool, ABool, FABool);
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
	
	private static Outdoor loadOutdoor(Integer PropertyID)
	{
		
		Outdoor output = null;
		ResultSet table = null;
		boolean PatioBool = false, BBQBool=false;
		table = SearchFacility("Outdoor_Facility", PropertyID.toString(), true);
			try {
				while (table.next()) {
					int Patio = table.getInt(3);
					int BBQ = table.getInt(4);
					String parking = table.getString(2);
					if(Patio==1)
						PatioBool = true;
					if(BBQ==1)
						BBQBool = true;
					
					
					ParkType park=null;
					if (parking != null)
					{
						switch(parking)
						{
							case "free on-site parking": 
								park=ParkType.free;
								break;
							case "on road parking":
								park=ParkType.onRoad;
							case "paid car-park":
								park=ParkType.paid;
							default:
								park=null;
						}
					}
					output = new Outdoor(PatioBool, BBQBool, park);
					
					}
				disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return output;
	}
	
	private static Facilities loadFacilities(Integer PropertyID)
	{
		//(Property p, Kitchen kitchen, Living living, Utility utility, Outdoor outdoor)
		return new Facilities(PropertyID, loadBedrooms(PropertyID), loadBathrooms(PropertyID), loadKitchen(PropertyID), loadLiving(PropertyID), loadUtility(PropertyID), loadOutdoor(PropertyID));
	}

	private static Map<Integer, Guest> LoadGuests() {
		Map<Integer, Guest> output = new HashMap<Integer, Guest>();
		ResultSet table = null;
		table = SearchFullTable("Guest", true);
		try {
			while (table.next()) {
				String forename = table.getString(1);
				String surname = table.getString(2);
				String phoneNum = table.getString(3);
				String email = table.getString(4);
				Integer GuestID = table.getInt(5);
				String AddressID = table.getString(6);
				Address address_temp = getAddress(AddressID);
				output.put(GuestID, new Guest(surname, forename, address_temp, phoneNum, email));
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}
	//connections and disconnections should only be made within static class methods
	private static boolean getConnection()
	{
		//Initialised the static class with the connection string
		con = null;
		try {
			con = DriverManager.getConnection(connectionString, username, password);

			return true;
			}
			catch (Exception ex) {
			return false;
			}
	}
	private static void disconnect()
	{
		//Initialised the static class with the connection string
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			con = null;
		}
	}

	//Returns a full table
	 public static ResultSet SearchFullTable(String TableName, boolean keepConnectionOpen)
     {
		 ResultSet table=null;
		 String sql="";
		 switch(TableName)
			{
			case "Bookings":
				sql = "SELECT * FROM Bookings;";
				break;
			case "Charge_Band":
				sql = "SELECT * FROM Charge_Band;";
				break;
			case "Guest":
				sql = "SELECT * FROM Guest;";
				break;
			case "Host":
				sql = "SELECT * FROM Host;";
				break;
			case "Property":
				sql = "SELECT * FROM Property;";
				break;
			case "Reviews":
				sql = "SELECT * FROM Reviews;";
				break;
			default:
				return table;
			}
                 
         try {
        	 getConnection();
			 PreparedStatement pst=con.prepareStatement(sql);
			 table = pst.executeQuery();
			if (!keepConnectionOpen)
			{
				table.close();
				pst.close();
				disconnect();
			}
		} 
        catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
         return table;
     }
	 //returns a given user
	 public static ResultSet SearchUser(String TableName, String UserID, boolean keepConnection) {
		ResultSet table = null;
		String sql="";
		if (TableName == "Guest")
			sql = "SELECT * FROM Guest WHERE GuestID = ?;";
		else if (TableName == "Host")
			sql = "SELECT * FROM Host WHERE HostID = ?;";
		else if (TableName == "Address")
			sql = "SELECT * FROM Address WHERE AddressID = ?;";
		else 
			return null;
		
		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(UserID));
			table = pst.executeQuery();
			if (!keepConnection) {
				table.close();
				pst.close();
				disconnect();
			}
		} catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		return table;
	}
	
	//returns a given Facility
	 public static ResultSet SearchFacility(String TableName, String PropertyID, boolean keepConnection) {
		ResultSet table = null;
		String sql = "";
		switch(TableName)
		{
		case "Sleeping_Facility":
			sql = "SELECT * FROM Sleeping_Facility WHERE PropertyID = ?;";
			break;
		case "Bathing_Facility":
			sql = "SELECT * FROM Bathing_Facility WHERE PropertyID = ?;";
			break;
		case "Living_Facility":
			sql = "SELECT * FROM Living_Facility WHERE PropertyID = ?;";
			break;
		case "Kitchen_Facility":
			sql = "SELECT * FROM Kitchen_Facility WHERE PropertyID = ?;";
			break;
		case "Utility_Facility":
			sql = "SELECT * FROM Utility_Facility WHERE PropertyID = ?;";
			break;
		case "Outdoor_Facility":
			sql = "SELECT * FROM Outdoor_Facility WHERE PropertyID = ?;";
			break;
		default:
			return null;
		}
		try {
			getConnection();
			 PreparedStatement pst=con.prepareStatement(sql);
			 pst.setInt(1, Integer.parseInt(PropertyID));
			 table = pst.executeQuery();
			if (!keepConnection) {
				table.close();
				pst.close();
				disconnect();
			}
		} catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		return table;
	}

	public static Address getAddress(String AddressID) {
		ResultSet output = SearchUser("Address", AddressID, true);
		Address temp = null;

		try {
			while (output.next()) {
				String HouseNumber = output.getString(2);
				String Street = output.getString(3);
				String Postcode = output.getString(4);
				String City = output.getString(5);
				temp = new Address(HouseNumber, Street, Postcode, City, false);
			}
			output.close();
				disconnect();

	
		} catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		disconnect();
		return temp;
	}
	
	
	 public static String SearchUserID(String TableName, String email) {
		 ResultSet table=null;
		  String UserID="";
		 String sql="";
		 if (TableName == "Guest")
			 sql = "SELECT GuestID FROM Guest WHERE Email = ?;";
		 else if (TableName == "Host")
			 sql = "SELECT HostID FROM Host WHERE Email = ?;";
		 else 
			 return UserID;
		 
		 try {
			 getConnection();
			 PreparedStatement pst=con.prepareStatement(sql);
			 pst.setString(1, email);
			 table = pst.executeQuery(); 
			 while (table.next()) {
				UserID = table.getString(1);
			}			
			 table.close();
			 pst.close();
			 disconnect();
		 }
		 catch (SQLException e) {
			 disconnect();
			 e.printStackTrace();
		 }
		 return UserID;
     }

	public static String GetPropertyID(String AddressID) {
		ResultSet table = null;
		String PropertyID = null;
		String sql = "SELECT PropertyID FROM Property WHERE AddressID = ?;";

		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(AddressID));
			table = pst.executeQuery(); 
			while (table.next()) {
				PropertyID = table.getString(1);

			}
			table.close();
			pst.close();
			disconnect();
		} catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		return PropertyID;

	}

	public static String SearchAddressID(String houseNumber, String postcode) {
		ResultSet table = null;
		String AddressID = null;
		String sql = "SELECT AddressID FROM Address WHERE HouseNumber = ? AND Postcode = ?;";

		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, houseNumber);
			pst.setString(2, postcode);
			table = pst.executeQuery(); 
			while (table.next()) {
				AddressID = table.getString(1);

			}
			table.close();
			pst.close();
			disconnect();
		} catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		return AddressID;

	}
	
	public static String GetChargeBandID(int propertyID, String startDate, String endDate) {
		String ChargeBandID = null;
		ResultSet table = null;
		String sql = "SELECT * FROM Charge_Band WHERE PropertyID=? AND  StartDate=? AND EndDate=?;";
		
		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, propertyID);
			pst.setString(2, startDate);
			pst.setString(3, endDate);
			table = pst.executeQuery(); 
			while (table.next()) {ChargeBandID = table.getString(1);}
			table.close();
			pst.close();
			disconnect();
		}
		catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		return ChargeBandID;
	}
	public static String GetReviewID(int propertyID, int guestID) {
		String ReviewID = null;
		ResultSet table = null;
		String sql = "SELECT * FROM Reviews WHERE PropertyID=? AND GuestID=?;";
		
		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, propertyID);
			pst.setInt(2, guestID);
			table = pst.executeQuery(); 
			while (table.next()) {ReviewID = table.getString(1);}
			table.close();
			pst.close();
			disconnect();
		}
		catch (SQLException e) {
			disconnect();
			e.printStackTrace();
		}
		
		return ReviewID;
	}
	
	
	// Returns true if user exists
	public static Boolean IsUser(String TableName, String Email) {
		String UserID = SearchUserID(TableName, Email);
		if (UserID == "")
			return false;
		else
			return true;
	}

		public static Array SearchUserColumn(String TableName, String UserID, String Column)
		{
			 ResultSet table=null;
			 Array output=null;
			 Statement stmt;
	        String Command = "SELECT * FROM "+TableName+" WHERE " +TableName+"ID = " +UserID+";";

	         try {
			 getConnection();
	        	stmt = con.createStatement();
				table = stmt.executeQuery(Command);
				output=table.getArray(Column);
			 disconnect();
			}
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return output;
	     }

		//generates an update string for SQL
	private static String getUpdateString(String TableName, String ColumnName, boolean isFacilityUpdate)
	{
		String sql="";
		if (isFacilityUpdate)
		{
			String sql_bed="", sql_bath="", sql_living="",
					sql_kitchen="", sql_util="", sql_outdoor="";
			//Since PreparedStatement does not allow dynamic table/column calls, validate string and do manually 
			if (Bedroom.Columns.contains(ColumnName))
				sql_bed = "UPDATE Sleeping_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";
			if (Bathroom.Columns.contains(ColumnName))
				sql_bath = "UPDATE Bathing_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";
			if (Living.Columns.contains(ColumnName))
				sql_living = "UPDATE Living_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";	
			if (Kitchen.Columns.contains(ColumnName))
				sql_kitchen = "UPDATE Kitchen_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";	
			if (Utility.Columns.contains(ColumnName))
				sql_util = "UPDATE Utility_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";	
			if (Outdoor.Columns.contains(ColumnName))
				sql_outdoor = "UPDATE Outdoor_Facility SET "+ColumnName +"= ? WHERE PropertyID = ?;";	


			switch(TableName)
			{
			case "Sleeping_Facility":
				sql = sql_bed;
				break;
			case "Bathing_Facility":
				sql = sql_bath;
				break;
			case "Living_Facility":
				sql = sql_living;
				break;
			case "Kitchen_Facility":
				sql = sql_kitchen;
				break;
			case "Utility_Facility":
				sql = sql_util;
				break;
			case "Outdoor_Facility":
				sql = sql_outdoor;
				break;
			default:
				return "";
			}
			return sql;
		}
		else 
		{
			String sql_host="", sql_guest="";
			if (Host.Columns.contains(ColumnName))
				sql_host="UPDATE Host SET "+ ColumnName+ "= ? WHERE HostID = ?;";
			if (Guest.Columns.contains(ColumnName))
				sql_host="UPDATE Guest SET "+ ColumnName+ "= ? WHERE GuestID = ?;";

			if (TableName == "Host")
				sql=sql_host;
			else if (TableName == "Guest")
				sql=sql_guest;
			return sql;
		}
	}
	//Overloaded function, depending on the value being updated this may be an integer or a String
	public static boolean UpdateValue(String TableName, String ColumnName, String UserID, String Value)
	{
		int count=0;
		String sql ="";
		boolean output = false;
		sql=getUpdateString(TableName, ColumnName, false);
		if (sql == "")
			return false;
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, Value);
			pst.setInt(2, Integer.parseInt(UserID));
			count = pst.executeUpdate();
			pst.close();
			disconnect();
			if (count>0) 
				output=true;
		}
		catch (SQLException ex) {
			disconnect();
			ex.printStackTrace();
		}
		return output;
	}

	public static boolean UpdateValue(String TableName, String ColumnName, String UserID, int Value)
	{
		int count=0;
		String sql ="";
		boolean output = false;
		sql=getUpdateString(TableName, ColumnName, false);
		if (sql == "")
			return false;
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Value);
			pst.setInt(2, Integer.parseInt(UserID));
			count = pst.executeUpdate();
			pst.close();
			disconnect();
			if (count>0) 
				output=true;
		}
		catch (SQLException ex) {
			disconnect();
			ex.printStackTrace();
		}
		return output;
	}

	public static boolean UpdateFacilityValue(String TableName, String ColumnName, String PropertyID, int Value)
	{
		int count=0;
		String sql ="";
		boolean output = false;
		sql=getUpdateString(TableName, ColumnName, true);
		if (sql == "")
			return false;
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Value);
			pst.setInt(2, Integer.parseInt(PropertyID));
			count = pst.executeUpdate();
			pst.close();
			disconnect();
			
			if (count>0) 
				output=true;
		}
		catch (SQLException ex) {
			disconnect();
			ex.printStackTrace();
		}
		return output;
	}

	public static boolean UpdateFacilityValue(String TableName, String ColumnName, String PropertyID, String Value)
	{
		int count=0;
		String sql ="";
		boolean output = false;
		sql=getUpdateString(TableName, ColumnName, true);
		if (sql == "")
			return false;
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, Value);
			pst.setInt(2, Integer.parseInt(PropertyID));
			count = pst.executeUpdate();
			pst.close();
			disconnect();
			if (count>0) 
				output=true;
		}
		catch (SQLException ex) {
			disconnect();
			ex.printStackTrace();
		}
		return output;
	}
		
	public static boolean UpdateBookingValue(int bookingID, String columnName, int value) {

		int count=0;
		boolean output = false;
		String sql="";
		if (Booking.Columns.contains(columnName))
			sql = "UPDATE Bookings SET "+ columnName+ "= ? WHERE BookingID = ?;";
		else return output;
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,value);
			pst.setInt(2, bookingID);
			count = pst.executeUpdate();
			pst.close();
			disconnect();
			
			
			if (count>0) 
				output=true;
			
		}
		catch (SQLException ex) {
			disconnect();
			ex.printStackTrace();
		}
		return output;
	}
		
		/*This is a method for Guest's sign up. Using GuestSignUp class and Guest&Guest_Passwords table.
 */
	public static boolean signUpHost(String fName, String lName, String email, String phone, String addressID, String hostPW) {
		try {
			getConnection();
			String sql = "INSERT INTO Host(FirstName, LastName, IsSuperHost, Email, PhoneNumber, AddressID) VALUES (?,?,?,?,?,?)";
			String sql2 = "INSERT INTO Host_Passwords(HostID, Passwords) VALUES(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fName);
			pst.setString(2, lName);
			// default isSuperHost is false
			pst.setInt(3, 0);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setString(6, addressID);
			
			pst.execute();
			pst.close();

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, SearchUserID("Host", email));
			pst2.setString(2, hostPW);

			pst2.execute();
			pst2.close();
			disconnect();
			return true;

		} 
		catch (Exception e) {
			disconnect();
			return false;
		}

	}


	public static boolean signUpGuest(String fName, String lName, String phone, String email, String addressID, String guestPW) {
		try {

			getConnection();
			String sql = "INSERT INTO Guest(FirstName, LastName,MobileNumber,Email, AddressID) VALUES (?,?,?,?,?)";
			String sql2 = "INSERT INTO Guest_Passwords(GuestID, Passwords) VALUES(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fName);
			pst.setString(2, lName);
			pst.setString(3, phone);
			pst.setString(4, email);
			pst.setString(5, addressID);
			pst.execute();
			pst.close();

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, SearchUserID("Guest", email));
			pst2.setString(2, guestPW);

			pst2.execute();
			pst2.close();
			disconnect();
			return true;

		} catch (Exception e) {
			disconnect();
			return false;
		}
	}
	public static boolean addBathroom(int PropertyID, int BathroomCount, boolean HairDryer, boolean Shampoo, boolean ToiletPaper, boolean Toilet, boolean Bath, boolean Shower, boolean IsShared) {
		int HD_int=0, S_int=0, TP_int=0, T_int=0, B_int=0, Shower_int=0, IS_int=0;
		if (HairDryer)
			HD_int=1;
		if (Shampoo)
			S_int=1;
		if (ToiletPaper)
			TP_int=1;
		if (Toilet)
			T_int=1;
		if (Bath)
			B_int=1;
		if (Shower)
			Shower_int=1;
		if (IsShared)
			IS_int=1;
		try {
			getConnection();
			String sql = "INSERT INTO Bathing_Facility(PropertyID, BathroomCount, HairDryer, Shampoo, ToiletPaper, Toilet, Bath, Shower, IsShared) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, PropertyID);
			pst.setInt(2, BathroomCount);
			pst.setInt(3, HD_int);
			pst.setInt(4, S_int);
			pst.setInt(5, TP_int);
			pst.setInt(6, T_int);
			pst.setInt(7, B_int);
			pst.setInt(8, Shower_int);
			pst.setInt(9, IS_int);
			
			pst.execute();
			pst.close();	

			disconnect();
			return true;
		} catch (Exception e) {
			disconnect();
			return false;
		}
	}
	public static boolean addBedroom(int PropertyID, int BedroomNumber, boolean BedLinen, boolean Towels, String Bed1Type, String Bed2Type) {
		int BL_int=0, T_int=0;
		if (BedLinen)
			BL_int=1;
		if (Towels)
			T_int=1;
		try {
			getConnection();
			String sql = "INSERT INTO Sleeping_Facility(PropertyID, BedroomNumber, BedLinen, Towels, Bed1Type, Bed2Type) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, PropertyID);
			pst.setInt(2, BedroomNumber);
			pst.setInt(3, BL_int);
			pst.setInt(4, T_int);
			pst.setString(5, Bed1Type);
			pst.setString(6, Bed2Type);
			
			pst.execute();
			pst.close();	

			disconnect();
			return true;
		} catch (Exception e) {
			disconnect();
			return false;
		}
	}

   public static boolean addProperty(int HostID, String AddressID, String ShortName, String Descriptions, int Breakfast,
			String HouseNumber) {
		try {
			getConnection();
			String sql = "INSERT INTO Property(HostID, AddressID, ShortName, Descriptions, Breakfast) VALUES (?,?,?,?,?)";
			String sql2 = "INSERT INTO Bathing_Facility(PropertyID, BathroomCount, HairDryer, Shampoo, ToiletPaper, Toilet, Bath, Shower, IsShared) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sql3 = "INSERT INTO Sleeping_Facility(PropertyID, BedroomNumber, BedLinen, Towels, Bed1Type, Bed2Type) VALUES (?, ?, ?, ?, ?, ?);";
			String sql4 = "INSERT INTO Kitchen_Facility(PropertyID, Refrigerator, Microwave, Oven, Stove, Dishwasher, Tableware, Cookware, basicProvision) VALUES (?, null, null, null, null, null, null, null, null);";
			String sql5 = "INSERT INTO Outdoor_Facility(PropertyID, Parking, Patio, Barbeque) VALUES (?, null, null, null);";
			String sql6 = "INSERT INTO Living_Facility(PropertyID, WIFI, Television, Satellite, Streaming, DVDPlayer, BoardGames) VALUES (?, null, null, null, null, null, null);";
			String sql7 = "INSERT INTO Utility_Facility(PropertyID, CentralHeating, WashingMachine, DryingMachine, FireExtinguisher, SmokeAlarm, FirstAid) VALUES(?, null, null, null, null, null, null);";

			PreparedStatement pst = con.prepareStatement(sql);
			PreparedStatement pst2 = con.prepareStatement(sql2);
			PreparedStatement pst3 = con.prepareStatement(sql3);
			PreparedStatement pst4 = con.prepareStatement(sql4);
			PreparedStatement pst5 = con.prepareStatement(sql5);
			PreparedStatement pst6 = con.prepareStatement(sql6);
			PreparedStatement pst7 = con.prepareStatement(sql7);

			pst.setInt(1, HostID);
			pst.setString(2, AddressID);
			pst.setString(3, ShortName);
			pst.setString(4, Descriptions);
			pst.setInt(5, Breakfast);
			pst.execute();
			pst.close();

			Integer propertyID = Integer.parseInt(TDatabase.GetPropertyID(AddressID));
			// Bathing Facility
			pst2.setInt(1, propertyID);
			pst2.setInt(2, 1);
			pst2.setInt(3, 0);
			pst2.setInt(4, 0);
			pst2.setInt(5, 0);
			pst2.setInt(6, 0);
			pst2.setInt(7, 0);
			pst2.setInt(8, 0);
			pst2.setInt(9, 0);
			pst2.execute();
			pst2.close();

			// Sleeping Facility
			// Sleeping Facility
			pst3.setInt(1, propertyID);
			pst3.setInt(2, 1);
			pst3.setInt(3, 0);
			pst3.setInt(4, 0);
			pst3.setString(5, null);
			pst3.setString(6, null);
			pst3.execute();
			pst3.close();

			// Kitchen Facility
			pst4.setInt(1, propertyID);
			pst4.execute();
			pst4.close();

			// Outdoor Facility
			pst5.setInt(1, propertyID);
			pst5.execute();
			pst5.close();

			// Living Facility
			pst6.setInt(1, propertyID);
			pst6.execute();
			pst6.close();

			// Utility Facility
			pst7.setInt(1, propertyID);
			pst7.execute();
			pst7.close();

			disconnect();
			return true;
		} catch (Exception e) {
			disconnect();
			return false;
		}
	}

	public static boolean addAddress(String houseNumber, String street, String postcode, String city) {
		try {
			getConnection();
			String sql = "INSERT INTO Address(HouseNumber, Street, Postcode, City) VALUES (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, houseNumber);
			pst.setString(2, street);
			pst.setString(3, postcode);
			pst.setString(4, city);
			pst.execute();
			pst.close();
			disconnect();
			return true;

		} catch (Exception e) {
			disconnect();
			return false;
		}
	}

	public static boolean GuestLogin(String email, String Password)
	{
		if (IsUser("Guest", email))
		{
			String UserID = SearchUserID("Guest", email);
			String password= getPassword("Guest",UserID);
			if (password.equals(Password))
				return true;
		}
		return false;
	}


	public static boolean HostLogin(String email, String Password) {
		if (IsUser("Host", email)) {
			String UserID = SearchUserID("Host", email);
			String password = getPassword("Host", UserID);
			if (password.equals(Password))
			{
				
				return true;
			}
		}

		return false;
	}
	public static String getPassword(String TableName, String USERID) {
		ResultSet table = null;
		String password = null;
		String sql = "";
		int user = Integer.parseInt(USERID);	

		if (TableName == "Guest")
			sql = "SELECT * FROM Guest_Passwords WHERE GuestID = ?;";
		else if (TableName == "Host")
			sql = "SELECT * FROM Host_Passwords WHERE HostID = ?;";
		else 
			return "Invalid";


		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, user);
			table = pst.executeQuery(); 
			while (table.next()) {
				password = table.getString(2);
			}
			table.close();
			pst.close();  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		disconnect();
		return password;

	}



	// A method for adding a booking to the Bookings table
		public static boolean AddBooking(int propertyID, int guestID, String startDate, String endDate) {
		try {
			getConnection();
			String sql="INSERT INTO Bookings(PropertyID, GuestID, StartDate, EndDate, Provisional, Rejected) VALUES (?,?,?,?,?,?);";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,propertyID);
			pst.setInt(2,guestID);
			pst.setString(3,startDate);
			pst.setString(4,endDate);
			pst.setInt(5, 1);
			pst.setInt(6, 0);
			pst.execute();
			pst.close();
			disconnect();
			
			return true;

		}
		catch (Exception e) {
			disconnect();
			return false;
		}
	}

       public static void DeleteBooking(int p, int g){
		String sql = "DELETE FROM Bookings WHERE PropertyID=? AND GuestID=?;";
		try
		{
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, p);
			pst.setInt(2, g);
			pst.executeUpdate();
			pst.close();
			disconnect();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			disconnect();
		}
	}
	//
	public static int GetBookingID(int propertyID, int guestID) {
		ResultSet table = null;
		int bookingID = -1;
		String sql = "SELECT * FROM Bookings WHERE PropertyID=? AND GuestID=?;";

		try {
			getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, propertyID);
			pst.setInt(2, guestID);

			table = pst.executeQuery(); 

			while (table.next()) {
				bookingID = table.getInt(1);
			}
			table.close();
			pst.close();  
			disconnect();
		}
		catch (SQLException e) {
			e.printStackTrace();
			disconnect();
		}
		return bookingID;
	}

	public static boolean AddReview(int propertyID, int guestID, int cl, int com, int chk, int ac, int loc, int val, String desc) {
		try {
			getConnection();
			String sql="INSERT INTO Reviews(PropertyID, GuestID, Cleanliness, Communication, Checkin, Accuracy, Location, Value_for_money, OptionalDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, propertyID);
			pst.setInt(2, guestID);
			pst.setInt(3, cl);
			pst.setInt(4, com);
			pst.setInt(5, chk);
			pst.setInt(6, ac);
			pst.setInt(7, loc);
			pst.setInt(8, val);
			if (desc == "") {pst.setString(9, null);}
			else {pst.setString(9, desc);}
			pst.execute();
			pst.close();
			disconnect();
			return true;

		}
		catch (Exception e) {
			disconnect();
			return false;
		}
	}

	public static boolean AddChargeBand(String start, String end, int propertyID, double ppn, double sc, double cc) {
		try {
			getConnection();
			String sql="INSERT INTO Charge_Band(StartDate, EndDate, PropertyID, PricePerNight, ServiceCharge, CleaningCharge) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, start);
			pst.setString(2, end);
			pst.setInt(3, propertyID);
			pst.setDouble(4, ppn);
			pst.setDouble(5, sc);
			pst.setDouble(6, cc);
			pst.execute();
			pst.close();
			disconnect();
			return true;

		}
		catch (Exception e) {
			disconnect();
			return false;
		}
	}


	public static boolean isProperty(String houseNo, String postcode) {
		ResultSet table=null;
		 String AddressID="";
		 String sql="SELECT Property.AddressID FROM Property"
		 		+ " INNER JOIN Address ON Property.AddressID = Address.AddressID"
		 		+ " WHERE HouseNumber = ? AND Postcode = ?;";
		 
		 try {
			 getConnection();
			 PreparedStatement pst=con.prepareStatement(sql);
			 pst.setString(1, houseNo);
			 pst.setString(2, postcode);
			 table = pst.executeQuery(); 
			 while (table.next()) {
				AddressID = table.getString(1);
			}			
			 table.close();
			 pst.close();
			 disconnect();
		 }
		 catch (SQLException e) {
			 disconnect();
			 e.printStackTrace();
		 }
		 if (AddressID == "")
			 return false;
		 else 
			 return true;
	}
}
