package Controllers;
import Database.DatabaseManager;
import Models.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatesController {
	
	private DatabaseManager dbManager;
	
	public StatesController() {
		dbManager = new DatabaseManager();
	}

    // Read States
    public List<State> readStates() {
    	List<State> statelist=new ArrayList<>();
  
        String selectSQL = "SELECT * FROM state";
        try {
        	Connection connection = dbManager.connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
            	statelist.add(new State(rs.getInt("state_id"), rs.getLong("population"),
            			rs.getString("name"),rs.getFloat("literacy_rate")));
            }
            
            return statelist;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
