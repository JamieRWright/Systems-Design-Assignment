package main;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public static Map<Integer, Guest> Guests = null;
	public static Map<Integer, Host> Hosts = null;

	private static Connection con;
	private static String _SQLGuest = "SELECT * FROM Guest;";
	private static String _SQLHost = "SELECT * FROM Host;";
	private static String _SQLProperty = "SELECT * FROM Property;";
	private static String _SQLBeds = "SELECT * FROM Beds";

	public static boolean Initialise()
	{
		boolean isSuccess = true;
		isSuccess=getConnection();
		Hosts = TDatabase.LoadHosts();
		Properties = TDatabase.LoadProperties();
		Guests = TDatabase.LoadGuests();
		Bookings = TDatabase.LoadBookings();
		
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
				
				Address host_add = getAddress(AddressID);
				if (getSuperHost == "1")
					isSuperHost = true;


				output.put(Integer.parseInt(HostID), new Host(surname, forename, host_add, "", email));
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
						Integer BookingID = table.getInt(2);
						Integer PropertyID = table.getInt(2);
						Integer HostID = table.getInt(3);
						Integer GuestID = table.getInt(4);
						String StartDate = table.getString(5);
						String EndDate = table.getString(6);
						int Provisional = table.getInt(7);
						int Rejected = table.getInt(8);
						if (Rejected > 0) {
							rejected = true;
						}
						if (Provisional == 0) {
							provisional = true;
						}
						// System.out.println(HostID + Street + Postcode + City + Country + ShortName);
						Host current_host = Hosts.get(HostID);	
						output.put(BookingID, new Booking(PropertyID, HostID, GuestID, StartDate, EndDate, provisional, rejected, false));
						}
					disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
					String HairDryer = table.getString(3);
					String Shampoo = table.getString(4);
					String ToiletPaper = table.getString(5);
					int Toilet = table.getInt(6);
					int Bath = table.getInt(7);
					int Shower = table.getInt(8);
					int IsShared = table.getInt(9);
					
					if (HairDryer=="1")
						HDBool = true;
					if(Shampoo=="1")
						ShampBool = true;
					if (ToiletPaper=="1")
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
					String BedLinen = table.getString(3);
					String Towels = table.getString(4);
					String Bed1Type = table.getString(5);
					String Bed2Type = table.getString(7);
					if(BedLinen=="1")
						BLBool = true;
					if(Towels=="1")
						TBool = true;
					
					BedType Bed1;
					switch(Bed1Type)
					{
						case "Single Bed": 
							Bed1=BedType.Single;
							break;
						case "Double Bed":
							Bed1=BedType.Double;
						case "Bunk Bed":
							Bed1=BedType.Bunk;
						case "Kingsize Bed":
							Bed1=BedType.King;
						default:
							Bed1=null;
					}
					
					BedType Bed2=null;
					if (Bed2Type != null)
					{
						switch(Bed2Type)
						{
							case "Single Bed": 
								Bed2=BedType.Single;
								break;
							case "Double Bed":
								Bed2=BedType.Double;
							case "Bunk Bed":
								Bed2=BedType.Bunk;
							case "Kingsize Bed":
								Bed2=BedType.King;
							default:
								Bed2=null;
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
					String fridge = table.getString(2);
					String micro = table.getString(3);
					String oven = table.getString(4);
					String stove = table.getString(5);
					String dishwasher = table.getString(6);
					String tableware = table.getString(7);
					String cookware = table.getString(8);
					String provisions = table.getString(9);
					
					if(fridge=="1")
						FBool = true;
					if(micro=="1")
						MBool = true;
					if(oven=="1")
						OBool = true;
					if(stove=="1")
						TBool = true;
					if(dishwasher=="1")
						DBool = true;
					if(tableware=="1")
						TBool = true;
					if(cookware=="1")
						CBool = true;
					if(provisions=="1")
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
					String wifi = table.getString(2);
					String tv = table.getString(3);
					String sat = table.getString(4);
					String streaming = table.getString(5);
					String dvd = table.getString(6);
					String games = table.getString(7);
					if(wifi=="1")
						WBool = true;
					if(tv=="1")
						TVBool = true;
					if(sat=="1")
						SatBool = true;
					if(streaming=="1")
						StreamBool = true;
					if(dvd=="1")
						DVDBool = true;
					if(games=="1")
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
					String CHeat = table.getString(2);
					String Washing = table.getString(3);
					String Drying = table.getString(4);
					String fireExtinguish = table.getString(5);
					String Alarm = table.getString(6);
					String FirstAid = table.getString(7);
					if(CHeat=="1")
						HeatBool = true;
					if(Washing=="1")
						WBool = true;
					if(Drying=="1")
						DBool = true;
					if(fireExtinguish=="1")
						FEBool = true;
					if(Alarm=="1")
						ABool = true;
					if(FirstAid=="1")
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
					String Patio = table.getString(2);
					String BBQ = table.getString(3);
					String parking = table.getString(4);
					if(Patio=="1")
						PatioBool = true;
					if(BBQ=="1")
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
		return new Facilities(null, loadBedrooms(PropertyID), loadBathrooms(PropertyID), loadKitchen(PropertyID), loadLiving(PropertyID), loadUtility(PropertyID), loadOutdoor(PropertyID));
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

	//Returns a full table, this may return Array in future
	 public static ResultSet SearchFullTable(String TableName, boolean keepConnectionOpen)
     {
		 
		 ResultSet table=null;
		 Statement stmt;
         String Command = "SELECT * FROM "+TableName+";";
                 
         try {
        	 getConnection();
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			if (!keepConnectionOpen)
			{
				disconnect();
			}
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return table;
     }
	 //returns a given user
	 public static ResultSet SearchUser(String TableName, String UserID, boolean keepConnection) {
		ResultSet table = null;
		Statement stmt;
		String Command = "SELECT * FROM " + TableName + " WHERE " + TableName + "ID = '" + UserID + "';";

		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			if (!keepConnection) {
				disconnect();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	//returns a given Facility
	 public static ResultSet SearchFacility(String TableName, String PropertyID, boolean keepConnection) {
		ResultSet table = null;
		Statement stmt;
		String Command = "SELECT * FROM " + TableName + " WHERE PropertyID = '" + PropertyID + "';";

		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			if (!keepConnection) {
				disconnect();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

	public static Address getAddress(String AddressID) throws SQLException {
		ResultSet output = SearchUser("Address", AddressID, true);
		Address temp = null;

		while (output.next()) {
			String HouseNumber = output.getString(2);
			String Street = output.getString(3);
			String Postcode = output.getString(4);
			String City = output.getString(5);
			temp = new Address(HouseNumber, Street, Postcode, City, false);

		}
		disconnect();
		return temp;
	}
	
	 //Returns all properties owned by a given HostID
	 public static ResultSet SearchProperty(String TableName, String UserID) {
		 ResultSet table=null;
		 Statement stmt;
        	 String Command = "SELECT * FROM Property WHERE HostID = " +UserID+";";

         try {
        	 getConnection();
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			disconnect();
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return table;
     }

	 public static String SearchUserID(String TableName, String email) {
		 ResultSet table=null;
		 Statement stmt;
		 String GuestID=null;
        	 String Command = "SELECT "+ TableName+"ID FROM "+TableName+" WHERE Email = '" +email+"';";

         try {
		 getConnection();
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
		    while (table.next()) {
		        GuestID = table.getString(1);
		        
		    }
		 disconnect();
		}
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return GuestID;

     }

	public static String GetPropertyID(String AddressID) {
		ResultSet table = null;
		Statement stmt;
		String PropertyID = null;
		String Command = "SELECT PropertyID FROM Property WHERE AddressID = " + AddressID + ";";

		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			while (table.next()) {
				PropertyID = table.getString(1);

			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PropertyID;

	}

	public static String SearchAddressID(String houseNumber, String postcode) {
		ResultSet table = null;
		Statement stmt;
		String AddressID = null;
		String Command = "SELECT AddressID FROM Address WHERE HouseNumber = '" + houseNumber + "' AND Postcode = '"
				+ postcode + "';";

		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			while (table.next()) {
				AddressID = table.getString(1);

			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AddressID;

	}
	
	
	// Returns true if user exists
	public static Boolean IsUser(String TableName, String Email) {
		ResultSet table = null;
		Statement stmt;
		String Command = "SELECT "+ TableName +"ID FROM " + TableName + " WHERE Email = '" + Email + "';";
		String UserID ="";
		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			while (table.next()) {
				UserID = table.getString(1);

			}
			
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
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

		//Overloaded function, depending on the value being updated this may be an integer or a String
		public static void UpdateValue(String TableName, String ColumnName, String UserID, String Value)
		{
			Statement stmt = null;
			int count=0;
			String Command = "UPDATE " +TableName + " SET "+ ColumnName+ "= '" + Value + "' WHERE "+TableName+"ID = " +UserID+";";
			try
			{
				getConnection();
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
				disconnect();
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		public static void UpdateValue(String TableName, String ColumnName, String UserID, int Value)
		{
			Statement stmt = null;
			int count=0;
			String Command = "UPDATE " +TableName + " SET "+ ColumnName+ "= " + Value + " WHERE "+TableName+"ID = " +UserID+";";
			try
			{
				getConnection();
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
				disconnect();
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

			public static boolean UpdateFacilityValue(String TableName, String ColumnName, String PropertyID, int Value)
		{
			Statement stmt = null;
			int count=0;
			boolean output = false;
			String Command = "UPDATE " +TableName + " SET "+ ColumnName+ "= " + Value + " WHERE PropertyID = " +PropertyID+";";
			try
			{
				getConnection();
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
				disconnect();
				if (count>0) 
					output=true;
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			return output;
		}
		
		public static boolean UpdateFacilityValue(String TableName, String ColumnName, String PropertyID, String Value)
		{
			Statement stmt = null;
			int count=0;
			boolean output = false;
			String Command = "UPDATE " +TableName + " SET "+ ColumnName+ "= '" + Value + "' WHERE PropertyID = " +PropertyID+";";
			try
			{
				getConnection();
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
				disconnect();
				if (count>0) 
					output=true;
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			return output;
		}
		/*This is a method for Guest's sign up. Using GuestSignUp class and Guest&Guest_Passwords table.
 */
	public static boolean signUpHost(String fName, String lName, String email, String addressID, String hostPW) {
		try {
			getConnection();
			String sql = "INSERT INTO Host(FirstName, LastName, IsSuperHost, Email, AddressID) VALUES (?,?,?,?,?)";
			String sql2 = "INSERT INTO Host_Passwords(HostID, Passwords) VALUES(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fName);
			pst.setString(2, lName);
			// default isSuperHost is false
			pst.setInt(3, 0);
			pst.setString(4, email);
			pst.setString(5, addressID);
			pst.execute();

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, SearchUserID("Host", email));
			pst2.setString(2, hostPW);

			pst2.execute();
			disconnect();
			return true;

		} catch (Exception e) {
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

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, SearchUserID("Guest", email));
			pst2.setString(2, guestPW);

			pst2.execute();
			disconnect();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

   public static boolean addProperty(int HostID, String AddressID, String ShortName, String Descriptions, int Breakfast,
			String HouseNumber) {
		try {
			getConnection();
			String sql = "INSERT INTO Property(HostID, AddressID, ShortName, Descriptions, Breakfast) VALUES (?,?,?,?,?)";
			String sql2 = "INSERT INTO Bathing_Facility(PropertyID, BathroomCount, HairDryer, Shampoo, ToiletPaper, Toilet, Bath, Shower, IsShared) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String sql3 = "INSERT INTO Sleeping_Facility(PropertyID, BedroomNumber, BedLinen, Towels, Bed1Type, PeopleInBed1, Bed2Type, PeopleInBed2) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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

			Integer propertyID = Integer.parseInt(TDatabase.GetPropertyID(AddressID));
			// Bathing Facility
			pst2.setInt(1, propertyID);
			pst2.setInt(2, 1);
			pst2.setString(3, null);
			pst2.setString(4, null);
			pst2.setString(5, null);
			pst2.setInt(6, 0);
			pst2.setInt(7, 0);
			pst2.setInt(8, 0);
			pst2.setInt(9, 0);
			pst2.execute();

			// Sleeping Facility
			pst3.setInt(1, propertyID);
			pst3.setInt(2, 1);
			pst3.setInt(3, 0);
			pst3.setInt(4, 0);
			pst3.setString(5, "Single Bed");
			pst3.setInt(6, 1);
			pst3.setString(7, null);
			pst3.setString(8, null);
			pst3.execute();

			// Kitchen Facility
			pst4.setInt(1, propertyID);
			pst4.execute();

			// Outdoor Facility
			pst5.setInt(1, propertyID);
			pst5.execute();

			// Living Facility
			pst6.setInt(1, propertyID);
			pst6.execute();

			// Utility Facility
			pst7.setInt(1, propertyID);
			pst7.execute();

			disconnect();
			return true;
		} catch (Exception e) {
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
			disconnect();
			return true;

		} catch (Exception e) {
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
		Statement stmt;
		String password = null;
		String Command = "SELECT * FROM " + TableName + "_Passwords WHERE " + TableName + "ID = " + USERID + ";";
		try {
			getConnection();
			stmt = con.createStatement();
			table = stmt.executeQuery(Command);
			while (table.next()) {
				password = table.getString(2);
			}
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;

	}


	// A method for adding a booking to the Bookings table
	public static boolean AddBooking(int propertyID, int hostID, int guestID, String startDate, String endDate) {
		try {
			getConnection();
			String sql="INSERT INTO Bookings(PropertyID, HostID, GuestID, StartDate, EndDate, Provisional) VALUES (?,?,?,?,?,?);";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,propertyID);
			pst.setInt(2,hostID);
			pst.setInt(3,guestID);
			pst.setString(4,startDate);
			pst.setString(5,endDate);
			pst.setInt(6, 1);
			pst.execute();
			disconnect();
			return true;

			}
		catch (Exception e) {
			return false;
		}
	}

       public static void DeleteBooking(int p, int g){
		Statement stmt = null;
		int count=0;
		String Command = "DELETE FROM Bookings WHERE PropertyID=" + p + " AND GuestID=" + g + ";";
		try
		{
			getConnection();
			stmt = con.createStatement();
			count = stmt.executeUpdate(Command);
			disconnect();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
    
    public static int GetBookingID(int propertyID, int guestID) {
    	ResultSet table = null;
    	Statement stmt;
    	int bookingID = 0;
    	String Command = "SELECT * FROM Bookings WHERE PropertyID=" + propertyID + " AND GuestID=" + guestID + ";";
    	
    	try {
    		getConnection();
    		stmt = con.createStatement();
    		table = stmt.executeQuery(Command);
    		
    		while (table.next()) {
    			bookingID = table.getInt(1);
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return bookingID;
    }
    
    public static boolean AddReview(int propertyID, int guestID, int hostID, int cl, int com, int chk, int ac, int loc, int val, String desc) {
 		try {
 			getConnection();
 			String sql="INSERT INTO Reviews(PropertyID, GuestID, HostID, Cleanliness, Communication, Checkin, Accuracy, Location, Value_for_money, OptionalDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
 			PreparedStatement pst=con.prepareStatement(sql);
 			pst.setInt(1, propertyID);
 			pst.setInt(2, guestID);
 			pst.setInt(3, hostID);
 			pst.setInt(4, cl);
 			pst.setInt(5, com);
 			pst.setInt(6, chk);
 			pst.setInt(7, ac);
 			pst.setInt(8, loc);
 			pst.setInt(9, val);
 			if (desc == "") {pst.setString(10, null);}
 			else {pst.setString(10, desc);}
 			pst.execute();
 			disconnect();
 			return true;

 			}
 		catch (Exception e) {
 			return false;
 		}
 	}
    
    public static boolean AddChargeBand(String start, String end, int price, int propertyID, int ppn, int sc, int cc) {
 		try {
 			getConnection();
 			String sql="INSERT INTO Charge_Band(StartDate, EndDate, Price, PropertyID, PricePerNight, ServiceCharge, CleaningCharge) VALUES (?, ?, ?, ?, ?, ?, ?);";
 			PreparedStatement pst=con.prepareStatement(sql);
 			pst.setString(1, start);
 			pst.setString(2, end);
 			pst.setInt(3, price);
 			pst.setInt(4, propertyID);
 			pst.setInt(5, ppn);
 			pst.setInt(6, sc);
 			pst.setInt(7, cc);
 			pst.execute();
 			disconnect();
 			return true;

 			}
 		catch (Exception e) {
 			return false;
 		}
 	}
}
