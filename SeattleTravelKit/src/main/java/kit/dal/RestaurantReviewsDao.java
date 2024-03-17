package kit.dal;

import kit.model.RestaurantReviews;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantReviewsDao {
    protected ConnectionManager connectionManager;

    private static RestaurantReviewsDao instance = null;
    protected RestaurantReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RestaurantReviewsDao getInstance() {
        if(instance == null) {
            instance = new RestaurantReviewsDao();
        }
        return instance;
    }

   
    public RestaurantReviews create(RestaurantReviews review) throws SQLException {
        String insertReview = "INSERT INTO RestaurantReviews(reviewId, userName, createdTime, content, rating, restaurantId, service, foodQuality, operationTime) " +
                "VALUES(?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview);
            insertStmt.setInt(1, review.getReviewId());
            insertStmt.setString(2, review.getUserName());
            insertStmt.setTimestamp(3, new Timestamp(review.getCreatedTime().getTime()));
            insertStmt.setString(4, review.getContent());
            insertStmt.setDouble(5, review.getRating());
            insertStmt.setInt(6, review.getRestaurantId());
            insertStmt.setDouble(7, review.getService());
            insertStmt.setDouble(8, review.getFoodQuality());
            insertStmt.setString(9, review.getOperationTime());
            insertStmt.executeUpdate();
            return review;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    
    public RestaurantReviews getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT * FROM RestaurantReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                return new RestaurantReviews(
                    results.getInt("reviewId"),
                    results.getString("userName"),
                    new Date(results.getTimestamp("createdTime").getTime()),
                    results.getString("content"),
                    results.getDouble("rating"),
                    results.getInt("restaurantId"),
                    results.getDouble("service"),
                    results.getDouble("foodQuality"),
                    results.getString("operationTime"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    
    public List<Integer> findRestaurantsByServiceAbove(double serviceRating) throws SQLException {
        List<Integer> restaurantIds = new ArrayList<>();
        String selectRestaurantIds = "SELECT DISTINCT restaurantId FROM RestaurantReviews WHERE service > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurantIds);
            selectStmt.setDouble(1, serviceRating);
            results = selectStmt.executeQuery();
            while (results.next()) {
                restaurantIds.add(results.getInt("restaurantId"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return restaurantIds;
    }

    
    public List<Integer> findRestaurantsByFoodQualityAbove(double foodQualityRating) throws SQLException {
        List<Integer> restaurantIds = new ArrayList<>();
        String selectRestaurantIds = "SELECT DISTINCT restaurantId FROM RestaurantReviews WHERE foodQuality > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurantIds);
            selectStmt.setDouble(1, foodQualityRating);
            results = selectStmt.executeQuery();
            while (results.next()) {
                restaurantIds.add(results.getInt("restaurantId"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return restaurantIds;
    }
    
    
    
    public RestaurantReviews updateContent(int reviewId, String newContent) throws SQLException {
        String updateReview = "UPDATE RestaurantReviews SET content = ? WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            
            updateStmt.setString(1, newContent);
            updateStmt.setInt(2, reviewId);
            
           
            int affectedRows = updateStmt.executeUpdate();
            
          
            if (affectedRows == 0) {
                throw new SQLException("No records found to update for ReviewID: " + reviewId);
            }

            return getReviewById(reviewId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
           
            if (updateStmt != null) {
                try {
                    updateStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    
    
    public RestaurantReviews delete(int reviewId) throws SQLException {
        String deleteReview = "DELETE FROM RestaurantReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, reviewId);
            int affectedRows = deleteStmt.executeUpdate();
            
            
            if (affectedRows == 0) {
                throw new SQLException("No records found to delete for ReviewID: " + reviewId);
            }

            return null; 
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            
            if (deleteStmt != null) {
                try {
                    deleteStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    
}
