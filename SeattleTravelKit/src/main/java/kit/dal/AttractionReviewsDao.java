package kit.dal;

import kit.model.AttractionReviews;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttractionReviewsDao {
    protected ConnectionManager connectionManager;

    private static AttractionReviewsDao instance = null;
    protected AttractionReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static AttractionReviewsDao getInstance() {
        if(instance == null) {
            instance = new AttractionReviewsDao();
        }
        return instance;
    }

    
    public AttractionReviews create(AttractionReviews review) throws SQLException {
        String insertReview = "INSERT INTO AttractionReviews(reviewId, userName, createdTime, content, rating, attractionId, duration) " +
                "VALUES(?,?,?,?,?,?,?);";
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
            insertStmt.setInt(6, review.getAttractionId());
            insertStmt.setString(7, review.getDuration());
            insertStmt.executeUpdate();
            return review;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    
    public AttractionReviews getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT * FROM AttractionReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                return new AttractionReviews(
                    results.getInt("reviewId"),
                    results.getString("userName"),
                    new Date(results.getTimestamp("createdTime").getTime()),
                    results.getString("content"),
                    results.getDouble("rating"),
                    results.getInt("attractionId"),
                    results.getString("duration"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    
    public List<Integer> findAttractionsByRatingAbove(double rating) throws SQLException {
        List<Integer> attractionIds = new ArrayList<>();
        String selectAttractionIds = "SELECT DISTINCT attractionId FROM AttractionReviews WHERE rating > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAttractionIds);
            selectStmt.setDouble(1, rating);
            results = selectStmt.executeQuery();
            while (results.next()) {
                attractionIds.add(results.getInt("attractionId"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return attractionIds;
    }
    
    
    public AttractionReviews updateContent(int reviewId, String newContent) throws SQLException {
        String updateReview = "UPDATE AttractionReviews SET content = ? WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            updateStmt.setString(1, newContent);
            updateStmt.setInt(2, reviewId);
            
            int affectedRows = updateStmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Unable to update the review with ReviewID: " + reviewId + ". It may not exist.");
            }
            
            return getReviewById(reviewId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    
    
    
    public AttractionReviews delete(int reviewId) throws SQLException {
        String deleteReview = "DELETE FROM AttractionReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, reviewId);
            int affectedRows = deleteStmt.executeUpdate();
            
            
            if (affectedRows == 0) {
                throw new SQLException("Unable to delete the review with ReviewID: " + reviewId + ". It may not exist.");
            }
            
            return null; // Return null to indicate that the deletion was successful and the AttractionReview object is no longer in the database.
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e; 
        } finally {
            
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    
}
