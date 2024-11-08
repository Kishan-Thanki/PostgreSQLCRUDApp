package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseManager; 
import Models.Migrants;

public class MigrantController {
private DatabaseManager dbManager;
	
	public MigrantController() {	
		dbManager = new DatabaseManager();
	}

    // Read Migrants
    public List<Migrants> readMigrants() {
    	List<Migrants> MigrantsList=new ArrayList<>();
  
        String selectSQL = "SELECT * FROM migrants";
        try {
        	Connection connection = dbManager.connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
            	
            	MigrantsList.add(new Migrants(rs.getInt("migrants_id"),rs.getInt("birthplace_id"),
            			rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),
            			rs.getDate("birthdate"),rs.getString("contact_no"),rs.getString("email"),
            			rs.getString("gender"),rs.getBoolean("is_married")));
            	
            	
            }
			   
            return MigrantsList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Insert Migrants
    public int insertMigrant(int birthplace_id, String first_name, String middle_name, String last_name,
    		Date birthdate,String contact_no, String email, String gender, boolean is_married) {
    	
        String insertSQL = "INSERT INTO migrants (birthplace_id, first_name, middle_name,last_name,birthdate,"
        		+ "contact_no,email,gender,is_married) VALUES (?, ?, ?,?,?,?,?,?,?)";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
        	pstmt.setInt(1, birthplace_id);
        	pstmt.setString(2, first_name);
        	pstmt.setString(3, middle_name);
        	pstmt.setString(4, last_name);
        	pstmt.setDate(5, birthdate);
        	pstmt.setString(6, contact_no);
        	pstmt.setString(7, email);
        	pstmt.setString(8, gender);
        	pstmt.setBoolean(9, is_married); 
            int result =pstmt.executeUpdate();
            
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    
    // Update Migrants
    public void updateMigrant(int migrants_id,int birthplace_id, String first_name, String middle_name, String last_name,
    		Date birthdate,String contact_no, String email, String gender, boolean is_married) {
    	
        String updateSQL = "UPDATE migrants SET birthplace_id = ?, first_name = ?, middle_name = ?, last_name = ? "
        		+ ",birthdate = ?, contact_no = ? , email = ?, gender = ?, is_married = ? WHERE migrants_id = ?";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
        	pstmt.setInt(1, birthplace_id);
        	pstmt.setString(2, first_name);
        	pstmt.setString(3, middle_name);
        	pstmt.setString(4, last_name);
        	pstmt.setDate(5, birthdate);
        	pstmt.setString(6, contact_no);
        	pstmt.setString(7, email);
        	pstmt.setString(8, gender);
        	pstmt.setBoolean(9, is_married); 
        	pstmt.setInt(10, migrants_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete Migrants
    public void deleteMigrant(int Migrants_id) {
        String deleteSQL = "DELETE FROM migrants WHERE migrants_id = ?";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, Migrants_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
