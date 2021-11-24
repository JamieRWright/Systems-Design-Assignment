package main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//Static class created to be used throughout the project
//Methods are called like this: TDatabase.SearchFullTable("Guest")
//Global
public final class TDatabase {
	private static final String connectionString="jdbc:mysql://stusql.dcs.shef.ac.uk/team054";
	private static final String username="team054";
	private static final String password="c77880a3";
	public static List<Property> Properties = null;
	public static List<Property> Bookmarks = null;
	public static List<Guest> Guests = null;
	public static List<Host> Hosts = null;
	private static Connection con;
	private static String _SQLGuest = "SELECT * FROM Guest;";
	private static String _SQLHost = "SELECT * FROM Host;";
	private static String _SQLProperty = "SELECT * FROM Property;";
	private static String _SQLBeds = "SELECT * FROM Beds";

	public static boolean Initialise()
	{
		boolean isSuccess = true;
		isSuccess=getConnection();
		Properties = TDatabase.LoadProperties();
		Guests = TDatabase.LoadGuests();
		Hosts = TDatabase.LoadHosts();
		Bookmarks = new ArrayList<>();
		disconnect();
		
		return isSuccess;
	}
	
		private static List<Host> LoadHosts() {
		 List<Host> output = new ArrayList<>();
		 ResultSet table = null;
		 boolean isSuperHost;
		 table = SearchFullTable("Host", true);
		 try {
			while (table.next()) {
				  String HostID = table.getString(1);
			      String forename = table.getString(2);
			      String surname = table.getString(3);
			      String getSuperHost = table.getString(4);
			      isSuperHost = false;
			      if (getSuperHost=="1")
			    	  isSuperHost=true;
			      
			      String email = table.getString(5);
			      
			      output.add(new Host(surname, forename, null, null, HostID));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return output;
	}
	
	 private static List<Property> LoadProperties()
	 {
		 List<Property> output = new ArrayList<>();
		 ResultSet table = null;
		 table = SearchFullTable("Property", true);
		 try {
			while (table.next()) {
			      int HostID = table.getInt(2);
			      String HouseNo = table.getString(4);
			      String Street = table.getString(5);
			      String Postcode = table.getString(6);
			      String City = table.getString(7);
			      String Country = table.getString(8);
			      String ShortName = table.getString(9);
			      String Description = table.getString(10);
			      //System.out.println(HostID + Street + Postcode + City + Country + ShortName);
			      output.add(new Property(HostID, HouseNo, Street, Postcode, City, Country, ShortName, Description, false));
			 }
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return output;
	 }
	 
	 private static List<Guest> LoadGuests()
	 {
		 List<Guest> output = new ArrayList<>();
		 ResultSet table = null;
		 table = SearchFullTable("Guest", true);
		 try {
			while (table.next()) {
			      String forename = table.getString(1);
			      String surname = table.getString(2);
			      String phoneNum = table.getString(3);
			      String email = table.getString(4);
			      String GuestID = table.getString(5);
			      output.add(new Guest(surname, forename, null, phoneNum, GuestID));
			 }
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
	 public static ResultSet SearchUser(String TableName, String UserID) {
		 ResultSet table=null;
		 Statement stmt;
        	 String Command = "SELECT * FROM "+TableName+" WHERE " +TableName+"ID = " +UserID+";";

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


	 //Returns true if user exists
	 public static Boolean IsUser(String TableName, String Email) {
		 int rows=0;
		 Statement stmt;
        String Command = "SELECT UserID FROM "+TableName+" WHERE Email = " +Email+";";

         try {
		 getConnection();
        	 stmt = con.createStatement();
		 rows = stmt.executeUpdate(Command);
		 disconnect();
		}
        catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
        if (rows==0) return false;
        else return true;
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
			String Command = "UPDATE" +TableName + " SET "+ ColumnName+ "= '" + Value + "' WHERE "+TableName+"ID = " +UserID+";";
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
			String Command = "UPDATE" +TableName + " SET "+ ColumnName+ "= " + Value + " WHERE "+TableName+"ID = " +UserID+";";
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

		/*This is a method for Guest's sign up. Using GuestSignUp class and Guest&Guest_Passwords table.
 */
		public static boolean signUpHost(String fName,String lName, String email,String hostPW){
			try {
				getConnection();
				String sql="INSERT INTO Host(FirstName, LastName, IsSuperHost, Email) VALUES (?,?,?,?)";
				String sql2="INSERT INTO Host_Passwords(GuestID, Passwords) VALUES(?,?)";
			    PreparedStatement pst=con.prepareStatement(sql);
				pst.setString(1,fName);
				pst.setString(2,lName);
				//default isSuperHost is false
				pst.setInt(3,0);
				pst.setString(4,email);
				pst.execute();

				PreparedStatement pst2=con.prepareStatement(sql2);
				pst2.setString(1, SearchUserID("Host", email));
				pst2.setString(2,hostPW);

				pst2.execute();
				disconnect();
				return true;


			}
		catch (Exception e) {
			return false;
		}


		}


				public static boolean signUpGuest(String fName,String lName,String phone, String email,String guestPW){
					try {

						getConnection();
						String sql="INSERT INTO Guest(FirstName, LastName,MobileNumber,Email) VALUES (?,?,?,?)";
						String sql2="INSERT INTO Guest_Passwords(GuestID, Passwords) VALUES(?,?)";
						PreparedStatement pst=con.prepareStatement(sql);
						pst.setString(1,fName);
						pst.setString(2,lName);
						pst.setString(3,phone);
						pst.setString(4,email);
						pst.execute();

						PreparedStatement pst2=con.prepareStatement(sql2);
						pst2.setString(1, SearchUserID("Guest", email));
						pst2.setString(2,guestPW);

						pst2.execute();
						disconnect();
						return true;

					}
					catch (Exception e) {
						return false;
					}
				}

    public static boolean addProperty(int HostID,String HouseNumber,String Street, String Postcode,String City, String Country, String ShortName, String Descriptions){
        try {
            getConnection();
	    String sql="INSERT INTO Property(HostID, HouseNumber, Street, Postcode, City, Country, ShortName, Descriptions) VALUES (?,?,?,?,?,?,?,?)";
            String sql2 = "INSERT INTO Bathing_Facility(PropertyID, BathroomCount, HairDryer, Shampoo, ToiletPaper, Toilet, Bath, Shower, IsShared) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    String sql3 = "INSERT INTO Sleeping_Facility(PropertyID, BedroomNumber, BedLinen, Towels, Bed1Type, PeopleInBed1, Bed2Type, PeopleInBed2) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	    String sql4 = "INSERT INTO Kitchen_Facility(PropertyID, Refrigerator, Microwave, Oven, Stove, Dishwasher, Tableware, Cookware, basicProvision) VALUES (?, null, null, null, null, null, null, null, null);";
	    String sql5 = "INSERT INTO Outdoor_Facility(PropertyID, Parking, Patio, Barbeque) VALUES (?, null, null, null);";
	    String sql6 = "INSERT INTO Living_Facility(PropertyID, WIFI, Television, Satellite, Streaming, DVDPlayer, BoardGames) VALUES (?, null, null, null, null, null, null);";
	    String sql7 = "INSERT INTO Utility_Facility(PropertyID, CentralHeating, WashingMachine, DryingMachine, FireExtinguisher, SmokeAlarm, FirstAid) VALUES(?, null, null, null, null, null, null);";

	    PreparedStatement pst=con.prepareStatement(sql);
	    PreparedStatement pst2 = con.prepareStatement(sql2);
	    PreparedStatement pst3 = con.prepareStatement(sql3);
	    PreparedStatement pst4 = con.prepareStatement(sql4);
	    PreparedStatement pst5 = con.prepareStatement(sql5);
	    PreparedStatement pst6 = con.prepareStatement(sql6);
	    PreparedStatement pst7 = con.prepareStatement(sql7);

	    pst.setInt(1,HostID);
	    pst.setString(2,HouseNumber);
	    pst.setString(3,Street);
	    pst.setString(4,Postcode);
	    pst.setString(5,City);
	    pst.setString(6,Country);
	    pst.setString(7,ShortName);
	    pst.setString(8,Descriptions);
	    pst.execute();

	    int propertyID = GetPropertyID(HostID, ShortName, Descriptions, HouseNumber);
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
	}
	catch (Exception e) {
	    return false;
	}
}


				public static boolean GuestLogin(String email, String Password)
				{
					if (IsUser("Guest", email))
					{
						String UserID = SearchUserID("Guest", email);
						String password= getPassword("Guest_Passwords",UserID);
						if (password.equals(Password))
							return true;
					}

					//search for user ID, if it exists, compare passwords
					//Look at how SearchUserID works to retrieve SQL values!
					//Will need a function public static String getPassword(String TableName, String USERID)
					//It'd be awesome to see some encryption working but don't worry if not possible :)
					return false;
				}
				public static String getPassword(String TableName, String USERID)
				{
					ResultSet table=null;
					Statement stmt;
					String password=null;
					String Command = "SELECT * FROM "+TableName+"_Passwords WHERE " +TableName+"ID = " +USERID+";";
					try {
						getConnection();
						stmt = con.createStatement();
						table = stmt.executeQuery(Command);
						while (table.next()) {
							password = table.getString(1);
							System.out.println(password);
						}
						disconnect();
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return password;


				}


	// A method for adding a booking to the Bookings table
	public static boolean AddBooking(int propertyID, int hostID, int guestID, String startDate, String endDate) {
		try {
			getConnection();
			String sql="INSERT INTO Bookings(PropertyID, HostID, GuestID, startDate, endDate) VALUES (?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,propertyID);
			pst.setInt(2,hostID);
			pst.setInt(3,guestID);
			pst.setString(4,startDate);
			pst.setString(5,endDate);
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

    public static int GetPropertyID(int hostID, String name, String desc, String houseNum) {
	ResultSet table=null;
	int propertyID = 0;
	Statement stmt;
	 String n = "'" + name + "'";
	 String d = "'" + desc + "'";
	 String h = "'" + houseNum + "'";
	 String Command = "SELECT * FROM Property WHERE HostID = "+hostID+" AND ShortName = "+n+" AND Descriptions = "+d+" AND HouseNumber = "+h+";";

	 try {
		getConnection();
		stmt = con.createStatement();
		table = stmt.executeQuery(Command);
	while (table.next()) {
		propertyID = table.getInt(1);
	    }
	       disconnect();
	}
	catch (SQLException e) {
	       e.printStackTrace();
	}
	return propertyID;
    }
    
  /*  
    public static String getStartDate(int p, int g) {
    	ResultSet table=null;
    	String date = "";
    	Statement stmt;
    	String Command = "SELECT * FROM Bookings WHERE PropertyID = "+p+" AND GuestID = "+g+";";

    	 try {
    		getConnection();
    		stmt = con.createStatement();
    		table = stmt.executeQuery(Command);
    	while (table.next()) {
    		date = table.getString(4);
    	    }
    	       disconnect();
    	}
    	catch (SQLException e) {
    	       e.printStackTrace();
    	}
    	return date;
        }
        */
    
    public static boolean AddReview(int propertyID, int guestID, int hostID, int cl, int com, int chk, int ac, int loc, int val, String desc) {
 		try {
 			getConnection();
 			System.out.println("Connection established");
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
}
