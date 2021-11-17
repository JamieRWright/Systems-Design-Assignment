package Main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Static class created to be used throughout the project
//Methods are called like this: TDatabase.SearchFullTable("Guest")
//Global
public final class TDatabase {
	private static final String connectionString="jdbc:mysql://localhost:3306/SystemsDesign";
	private static final String username="root";
	private static final String password="ShairAt2owwij!";
	private static Connection con;
	private static String _SQLHost, _SQLStudentAttempts, _SQLProperty, _SQLGuest, _SQLBeds;

	private TDatabase()
	{
		//Initialised the static class with the connection string
		con = null;
		try {
			con = DriverManager.getConnection(connectionString, username, password);


			// use the open connection
			// for one or more queries
			}
			catch (Exception ex) {
			ex.printStackTrace();
			}



        _SQLGuest = "SELECT * FROM Guest;";
        _SQLHost = "SELECT * FROM Host;";
        _SQLProperty = "SELECT * FROM Property;";
        _SQLBeds = "SELECT * FROM Beds";
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
		public void UpdateValue(String TableName, String ColumnName, String UserID, String Value)
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

		public void UpdateValue(String TableName, String ColumnName, String UserID, int Value)
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
public static void signUpGuest(String fName,String lName,String phone, String email,String guestPW){
	try {


			String sql="INSERT INTO Guest(FirstName, LastName,MobileNumber,Email) VALUES (?,?,?,?)";
		    String sql2="INSERT INTO Guest_Passwords(Passwords) VALUES(?)";
		    PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,fName);
			pst.setString(2,lName);
			pst.setString(3,phone);
			pst.setString(4,email);

		    PreparedStatement pst2=con.prepareStatement(sql2);
			pst2.setString(1,guestPW);
		    pst.excute();
		    pst2.excute();

}
catch (Exception e) {
}
}
public static void signUpHost(String fName,String lName, String email,String hostPW){
	try {
		String sql="INSERT INTO Host(FirstName, LastName,Email) VALUES (?,?,?)";
	    String sql2="INSERT INTO Host_Passwords(Passwords) VALUES(?)";
	    PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1,fName);
		pst.setString(2,lName);
		pst.setString(4,email);

	    PreparedStatement pst2=con.prepareStatement(sql2);
		pst2.setString(1,hostPW);
	    pst.excute();
	    pst2.excute();

	}
catch (Exception e) {
}


}
}

