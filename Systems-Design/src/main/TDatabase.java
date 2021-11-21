package main;

import java.sql.*;

//Static class created to be used throughout the project
//Methods are called like this: TDatabase.SearchFullTable("Guest")
//Global
public final class TDatabase {
	private static final String connectionString="jdbc:mysql://stusql.dcs.shef.ac.uk/team054";
	private static final String username="team054";
	private static final String password="c77880a3";
	private static Connection con;
	private static String _SQLGuest = "SELECT * FROM Guest;";
	private static String _SQLHost = "SELECT * FROM Host;";
	private static String _SQLProperty = "SELECT * FROM Property;";
	private static String _SQLBeds = "SELECT * FROM Beds";

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
	 public static ResultSet SearchFullTable(String TableName)
     {
		 ResultSet table=null;
		 Statement stmt;
         String Command = "SELECT * FROM "+TableName+";";
                 
         try {
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
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
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
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
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return table;
     }
	 
	 //Returns all properties
	 public static ResultSet SearchProperty(String TableName) {
		 ResultSet table=null;
		 Statement stmt;
        	 String Command = "SELECT * FROM Property;";
                 
         try {
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
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
        	stmt = con.createStatement();
			table = stmt.executeQuery(Command);
		    while (table.next()) {
		        GuestID = table.getString(1);
		        System.out.println(GuestID);
		    }
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return GuestID;

     }
	 
	 
	 //Returns true if user exists
	 public static Boolean IsUser(String TableName, String UserID) {
		 int rows=0;
		 Statement stmt;
        String Command = "SELECT * FROM "+TableName+" WHERE " +TableName+"ID = " +UserID+";";
                 
         try {
        	stmt = con.createStatement();
			rows = stmt.executeUpdate(Command);
		} 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	        	stmt = con.createStatement();
				table = stmt.executeQuery(Command);
				output=table.getArray(Column);
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
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
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
				stmt = con.createStatement();
				count = stmt.executeUpdate(Command);
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
					}
					//search for user ID, if it exists, compare passwords 
					//Look at how SearchUserID works to retrieve SQL values!
					//Will need a function public static String getPassword(String TableName, String USERID)
					//It'd be awesome to see some encryption working but don't worry if not possible :)
					return false;
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
}

