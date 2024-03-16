package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.Hotels;

public class HotelsDao {
	protected ConnectionManager connectionManager;
	
	private static HotelsDao instance = null;
	

	public HotelsDao() {
		connectionManager = new ConnectionManager();
	}

	
	public static HotelsDao getInstance() {
		if(instance == null) {
			instance = new HotelsDao();
		}
		return instance;
	}
	
	public Hotels create(Hotels hotel) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		String insertHotel = "INSERT INTO Hotels(HotelName, Rating, Website, Phone, Details, Address, ZipCode, City) " +
							"VALUES(?,?,?,?,?,?,?,?);";
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHotel);
			insertStmt.setString(1,hotel.getHotelName());
			insertStmt.setDouble(2,hotel.getRating());
			insertStmt.setString(3,hotel.getWebsite());
			insertStmt.setString(4,hotel.getPhone());
			insertStmt.setString(5,hotel.getDetails());
			insertStmt.setString(6,hotel.getAddress());
			insertStmt.setInt(7,hotel.getZipCode());
			insertStmt.setString(8,hotel.getCity());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int hotelId = -1;
			if(resultKey.next()) {
				hotelId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			hotel.setHotelId(hotelId);
			return hotel;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public Hotels findHotelById(int hotelId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectHotel = "SELECT HotleId, HotelName, Rating, Website, Phone, Details, Address, ZipCode, City " +
							"FROM Hotels WHERE HotelId=? ";
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotel);
			selectStmt.setInt(1,  hotelId);
		
			results = selectStmt.executeQuery();
			if (results.next()) {
				String hotelName = results.getString("HotelName");
				Double rating = results.getDouble("Rating");
				String website = results.getString("Website");
				String phone = results.getString("Phone");
				String details = results.getString("Details");
				String address = results.getString("Address");
				int zipCode = results.getInt("ZipCode");
				String city = results.getString("City");
				
				Hotels hotel = new Hotels(hotelId, hotelName, rating, website, phone, details, address, zipCode, city);
				return hotel;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null; 
	}
	
	public List<Hotels> findHotelByName(String hotelName) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectHotel = "SELECT HotleId, HotelName, Rating, Website, Phone, Details, Address, ZipCode, City " +
							"FROM Hotels WHERE HotelName=? ";
		ResultSet results = null;
		List<Hotels> hotels = new ArrayList<Hotels>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotel);
			selectStmt.setString(1,  hotelName);
		
			results = selectStmt.executeQuery();
			while (results.next()) {
				int hotelId = results.getInt("HotelId");
				Double rating = results.getDouble("Rating");
				String website = results.getString("Website");
				String phone = results.getString("Phone");
				String details = results.getString("Details");
				String address = results.getString("Address");
				int zipCode = results.getInt("ZipCode");
				String city = results.getString("City");
				
				Hotels hotel = new Hotels(hotelId, hotelName, rating, website, phone, details, address, zipCode, city);
				hotels.add(hotel);
			}
			return hotels;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public List<Hotels> findHotelZipCode(int zipCode) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectHotel = "SELECT HotleId, HotelName, Rating, Website, Phone, Details, Address, ZipCode, City " +
							"FROM Hotels WHERE ZipCode=? ";
		ResultSet results = null;
		List<Hotels> hotels = new ArrayList<Hotels>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotel);
			selectStmt.setInt(1,  zipCode);
		
			results = selectStmt.executeQuery();
			while (results.next()) {
				int hotelId = results.getInt("HotelId");
				String hotelName = results.getString("HotelName");
				Double rating = results.getDouble("Rating");
				String website = results.getString("Website");
				String phone = results.getString("Phone");
				String details = results.getString("Details");
				String address = results.getString("Address");
				String city = results.getString("City");
				
				Hotels hotel = new Hotels(hotelId, hotelName, rating, website, phone, details, address, zipCode, city);
				hotels.add(hotel);
			}
			return hotels;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Hotels> findHotelAboveRating(double rating) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectHotel = "SELECT HotleId, HotelName, Rating, Website, Phone, Details, Address, ZipCode, City " +
							"FROM Hotels WHERE Rating>=? ";
		ResultSet results = null;
		List<Hotels> hotels = new ArrayList<Hotels>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHotel);
			selectStmt.setDouble(1,  rating);
		
			results = selectStmt.executeQuery();
			while (results.next()) {
				int hotelId = results.getInt("HotelId");
				String hotelName = results.getString("HotelName");
				Double rating1 = results.getDouble("Rating");
				String website = results.getString("Website");
				String phone = results.getString("Phone");
				String details = results.getString("Details");
				String address = results.getString("Address");
				int zipCode = results.getInt("ZipCode");
				String city = results.getString("City");
				
				Hotels hotel = new Hotels(hotelId, hotelName, rating1, website, phone, details, address, zipCode, city);
				hotels.add(hotel);
			}
			return hotels;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public Hotels updatePhone(Hotels hotel, String phone) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateHotel = "UPDATE Hotels SET Phone=? WHERE HotelId=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHotel);
			updateStmt.setString(1,phone);
			updateStmt.setInt(2,hotel.getHotelId());
			
			updateStmt.executeUpdate();
			
			hotel.setPhone(phone);
			return hotel;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	public Hotels updateDetails(Hotels hotel, String details) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateHotel = "UPDATE Hotels SET Phone=? WHERE HotelId=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHotel);
			updateStmt.setString(1,details);
			updateStmt.setInt(2,hotel.getHotelId());
			
			updateStmt.executeUpdate();
			
			hotel.setDetails(details);
			return hotel;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	
	public Hotels delete(Hotels hotel) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteHotel = "DELETE FROM Hotels WHERE HotelId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHotel);
			deleteStmt.setInt(1, hotel.getHotelId());
			
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
