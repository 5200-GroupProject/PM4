package kit.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kit.model.Attractions;

public class AttractionsDao {
	protected ConnectionManager connectionManager;
	
	private static AttractionsDao instance = null;
	

	public AttractionsDao() {
		connectionManager = new ConnectionManager();
	}

	
	public static AttractionsDao getInstance() {
		if(instance == null) {
			instance = new AttractionsDao();
		}
		return instance;
	}
	
	public Attractions create(Attractions attraction) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		String insertAttraction = "INSERT INTO Hotels(AttractionsName, Phone, Website, ZipCode, Area) " +
							"VALUES(?,?,?,?,?);";
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAttraction);
			insertStmt.setString(1,attraction.getAttractionsName());
			insertStmt.setString(2,attraction.getPhone());
			insertStmt.setString(3,attraction.getWebsite());
			insertStmt.setInt(4,attraction.getZipCode());
			insertStmt.setString(5,attraction.getArea());
		
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int attractionId = -1;
			if(resultKey.next()) {
				attractionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			attraction.setAttractionId(attractionId);
			return attraction;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
	}
	
	
	public Attractions findAttractionById(int attractionId) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectAttraction = "SELECT AttractionId, Name, Phone, Website, ZipCode, Area " +
								"FROM Attractions WHERE AttractionId=?;";
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttraction);
			selectStmt.setInt(1, attractionId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				String attractionName = results.getString("Name");
				String phone = results.getString("Phone");
				String website = results.getString("Website");
				int zipCode = results.getInt("ZipCode");
				String area = results.getString("Area");
				
				Attractions attraction = new Attractions(attractionId, attractionName, phone, website, zipCode, area);
				return attraction;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
		
		return null;
	}
	
	public Attractions findAttractionByName(String attractionName) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		String selectAttraction = "SELECT AttractionId, AttractionsName, Phone, Website, ZipCode, Area " +
								"FROM Attractions WHERE AttractionName=?;";
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAttraction);
			selectStmt.setString(1, selectAttraction);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				int attractionId = results.getInt("AttractionsId");
				String phone = results.getString("Phone");
				String website = results.getString("Website");
				int zipCode = results.getInt("ZipCode");
				String area = results.getString("Area");
				
				Attractions attraction = new Attractions(attractionId, attractionName, phone, website, zipCode, area);
				return attraction;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
		
		return null;
	}
	
	
	public List<Attractions> findAttractionByZipCode(int zipCode) throws SQLException {
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    List<Attractions> attractions = new ArrayList<>();
	    String selectAttraction = "SELECT AttractionId, Name, Phone, Website, ZipCode, Area " +
	            "FROM Attractions WHERE ZipCode=?;";
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectAttraction);
	        selectStmt.setInt(1, zipCode);
	        results = selectStmt.executeQuery();

	        while (results.next()) {
	            int attractionId = results.getInt("AttractionId");
	            String name = results.getString("Name");
	            String phone = results.getString("Phone");
	            String website = results.getString("Website");
	            int resultZipCode = results.getInt("ZipCode");
	            String area = results.getString("Area");

	            Attractions attraction = new Attractions(attractionId, name, phone, website, resultZipCode, area);
	            attractions.add(attraction);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // It's important to close resources to prevent leaks
	        if (results != null) {
	            results.close();
	        }
	        if (selectStmt != null) {
	            selectStmt.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return attractions; // Return the actual list instead of null
	}
	
	public Attractions updateAttractionPhone(Attractions attraction, String phone) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		String updateAttraction = "UPDATE Attractions SET Phone=? WHERE AttractionId=?;";
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAttraction);
			updateStmt.setString(1,phone);
			updateStmt.setInt(2,attraction.getAttractionId());
			
			updateStmt.executeUpdate();
			
			attraction.setPhone(phone);
			return attraction;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
	}
	
	
	public Attractions deleteAttraction(Attractions attraction) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		String deleteAttraction = "DELETE FROM Attractions WHERE AttractionId=?;";
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAttraction);
			deleteStmt.setInt(1, attraction.getAttractionId());
			
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
	}
	
	
}
