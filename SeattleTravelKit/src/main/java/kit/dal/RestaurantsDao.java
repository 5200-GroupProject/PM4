package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.Restaurants;

public class RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	private static RestaurantsDao instance = null;
	

	public RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}

	
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}
	
	
	public Restaurants create(Restaurants restaurant) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		String insertRest = "INSERT INTO Restaurants(RestaurantName, Address, Rating, Area, Category, Service, ZipCode) " + 
							"VALUES(?,?,?,?,?,?,?);";
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRest);
			insertStmt.setString(1,restaurant.getRestaurantName());
			insertStmt.setString(2,restaurant.getAddress());
			insertStmt.setDouble(3,restaurant.getRating());
			insertStmt.setString(4,restaurant.getArea());
			insertStmt.setString(5,restaurant.getCategory());
			insertStmt.setString(6,restaurant.getService());
			insertStmt.setInt(7,restaurant.getZipCode());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int restaurantId = -1;
			if (resultKey.next()) {
				restaurantId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restaurant.setRestaurantId(restaurantId);
			return restaurant;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	
	public Restaurants findRestaurantById(int restaurantId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRest = "SELECT RestaurantId, RestaurantName, Address, Rating, Area, Category, Service, ZipCode " + 
							"FROM Restaurants WHERE RestaurantId=?;";
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRest);
			
			selectStmt.setInt(1,  restaurantId);
			
			results = selectStmt.executeQuery();
			String restaurantName = results.getString("RestaurantName");
			String address = results.getString("Address");
			Double rating = results.getDouble("Rating");
			String area = results.getString("Area");
			String category = results.getString("Category");
			String service = results.getString("Service");
			int zipCode = results.getInt("ZipCode");
		
	
			Restaurants restaurant = new Restaurants(restaurantId, restaurantName, address, rating, area, category, service, zipCode);
			return restaurant;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	
	public List<Restaurants> findRestaurantByName(String restaurantName) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRest = "SELECT RestaurantId, RestaurantName, Address, Rating, Area, Category, Service, ZipCode " + 
							"FROM Restaurants WHERE RestaurantName=?;";
		ResultSet results = null;
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRest);
			
			selectStmt.setString(1,  restaurantName);
			
			results = selectStmt.executeQuery();
			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String address = results.getString("Address");
				Double rating = results.getDouble("Rating");
				String area = results.getString("Area");
				String category = results.getString("Category");
				String service = results.getString("Service");
				int zipCode = results.getInt("ZipCode");
			
		
				Restaurants restaurant = new Restaurants(restaurantId, restaurantName, address, rating, area, category, service, zipCode);
				restaurants.add(restaurant);
			}
			
			return restaurants;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public List<Restaurants> findRestaurantByZipCode(int zipCode) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRest = "SELECT RestaurantId, RestaurantName, Address, Rating, Area, Category, Service, ZipCode " + 
							"FROM Restaurants WHERE ZipCode=?;";
		ResultSet results = null;
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRest);
			
			selectStmt.setInt(1,  zipCode);
			
			results = selectStmt.executeQuery();
			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				String address = results.getString("Address");
				Double rating = results.getDouble("Rating");
				String area = results.getString("Area");
				String category = results.getString("Category");
				String service = results.getString("Service");

		
				Restaurants restaurant = new Restaurants(restaurantId, restaurantName, address, rating, area, category, service, zipCode);
				restaurants.add(restaurant);
			}
			
			return restaurants;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	public List<Restaurants> findRestaurantAboveRating(double rating) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectRest = "SELECT RestaurantId, RestaurantName, Address, Rating, Area, Category, Service, ZipCode " + 
							"FROM Restaurants WHERE Rating>=?;";
		ResultSet results = null;
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRest);
			
			selectStmt.setDouble(1,  rating);
			
			results = selectStmt.executeQuery();
			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				String address = results.getString("Address");
				Double rating1 = results.getDouble("Rating");
				String area = results.getString("Area");
				String category = results.getString("Category");
				String service = results.getString("Service");
				int zipCode = results.getInt("ZipCode");

		
				Restaurants restaurant = new Restaurants(restaurantId, restaurantName, address, rating1, area, category, service, zipCode);
				restaurants.add(restaurant);
			}
			
			return restaurants;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public Restaurants updateRestaurantAddress(Restaurants restaurant, String newAddress) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateRest = "UPDATE Restaurants SET Address=? WHERE RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRest);
			updateStmt.setString(1,newAddress);
			updateStmt.setInt(2,restaurant.getRestaurantId());
			
			updateStmt.executeUpdate();
			
			restaurant.setAddress(newAddress);
			return restaurant;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public Restaurants updateRestaurantService(Restaurants restaurant, String newService) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateRest = "UPDATE Restaurants SET Service=? WHERE RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRest);
			updateStmt.setString(1,newService);
			updateStmt.setInt(2,restaurant.getRestaurantId());
			
			updateStmt.executeUpdate();
			
			restaurant.setService(newService);
			return restaurant;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public Restaurants deleteRestaurant(Restaurants restaurant) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteRest = "DELETE FROM Restaurants WHERE RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRest);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
			
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
