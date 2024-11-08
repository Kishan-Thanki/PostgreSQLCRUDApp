package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	 private static final String URL = "jdbc:postgresql://localhost:5432/Youth";  
	  private static final String USER = "postgres";   
	     private static final String PASSWORD = "root";
	     
	     // Connect to the database
	     	
	     public Connection connect() throws SQLException {
	         return DriverManager.getConnection(URL, USER, PASSWORD);
	     }
}
