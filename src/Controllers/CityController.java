package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseManager;
import Models.City; 

public class CityController {
private DatabaseManager dbManager;
	
	public CityController() {	
		dbManager = new DatabaseManager();
	}

    // Read City According State
    public List<City> readCities(int state_id) {
    	List<City> cityList=new ArrayList<>();
  
        String selectSQL = "SELECT * FROM city where state_id = ?";
        try {
        	Connection connection = dbManager.connect();
            PreparedStatement pstmt = connection.prepareStatement(selectSQL);
            pstmt.setInt(1, state_id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
            	cityList.add(new City(rs.getInt("city_id"),rs.getString("name"),
            			rs.getString("pincode"),rs.getInt("state_id")));
            }
            
            return cityList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Insert City
    public void insertCity(String name, String pincode, int state_id) {
        String insertSQL = "INSERT INTO city (name, pincode, state_id) VALUES (?, ?, ?)";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, pincode);
            pstmt.setInt(3, state_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // Update City
    public void updateCity(int city_id,String name, String pincode, int state_id) {
        String updateSQL = "UPDATE city SET name = ?, pincode = ?, state_id = ? WHERE city_id = ?";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, pincode);
            pstmt.setInt(3, state_id);
            pstmt.setInt(4, city_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete City 
    public void deleteCity(int city_id) {
        String deleteSQL = "DELETE FROM city WHERE city_id = ?";
        try (Connection connection = dbManager.connect();
             PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, city_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 }

