package Controllers;
import Database.DatabaseManager;
import Models.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatesController {
	
	private DatabaseManager dbManager;
	
	public StatesController() {
		dbManager = new DatabaseManager();
	}

    // Read States
    public Map<String, Integer> readStates() { 
    	 Map<String, Integer> states = new HashMap<>();
        String selectSQL = "SELECT * FROM state";
        try {
        	Connection connection = dbManager.connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            ResultSet rs=pstmt.executeQuery();
            
            while(rs.next()) {
            	states.put(rs.getString("name"), rs.getInt("state_id")); 
            }
            
            return states;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
