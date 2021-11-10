package Main;

import java.sql.*;

public class TDatabase {
	private final String connectionString="jdbc:mysql://localhost:3306/SystemsDesign";
	private final String username="root";
	private final String password="ShairAt2owwij!";
	private Connection con;
	private String _SQLHost, _SQLStudentAttempts, _SQLProperty, _SQLGuest, _SQLBeds;

	public TDatabase()
	{
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
	
	 private void SearchFullTable(String TableName)
     {
		 ResultSet table;
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
     }
}

//Statement stmt = null;
//try 
//{
//	stmt = con.createStatement();
//	count = stmt.executeUpdate("UPDATE Guest SET FirstName = 'Tim'" + " WHERE GuestID = 1");
//}
//catch (SQLException ex) {
//	ex.printStackTrace();
//}
//finally {
//	if (stmt != null)
//		stmt.close();
//	System.out.print(count);
//}
