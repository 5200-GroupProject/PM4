package kit.dal;

import kit.model.HotelsReviews;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelsReviewsDao {
    protected ConnectionManager connectionManager;

    private static HotelsReviewsDao instance = null;
    protected HotelsReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static HotelsReviewsDao getInstance() {
        if(instance == null) {
            instance = new HotelsReviewsDao();
        }
        return instance;
    }

    
    public HotelsReviews create(HotelsReviews review) throws SQLException {
        String insertReview = "INSERT INTO HotelsReviews(reviewId, userName, createdTime, content, rating, hotelId, service, cleanliness, location, sleepQuality) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
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
            insertStmt.setInt(6, review.getHotelId());
            insertStmt.setFloat(7, review.getService());
            insertStmt.setFloat(8, review.getCleanliness());
            insertStmt.setFloat(9, review.getLocation());
            insertStmt.setFloat(10, review.getSleepQuality());
            insertStmt.executeUpdate();
            return review;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    
    public HotelsReviews getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT * FROM HotelsReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                return new HotelsReviews(
                        results.getInt("reviewId"),
                        results.getString("userName"),
                        new Date(results.getTimestamp("createdTime").getTime()),
                        results.getString("content"),
                        results.getDouble("rating"),
                        results.getInt("hotelId"),
                        results.getFloat("service"),
                        results.getFloat("cleanliness"),
                        results.getFloat("location"),
                        results.getFloat("sleepQuality"));
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }
    
    
    
    public List<Integer> findHotelIdsByCleanlinessRatingAbove(float cleanlinessRating) throws SQLException {
        List<Integer> hotelIds = new ArrayList<>();
        String selectHotelIds = "SELECT DISTINCT HotelId FROM HotelsReviews WHERE Cleanliness > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHotelIds);
            selectStmt.setFloat(1, cleanlinessRating);
            results = selectStmt.executeQuery();
            while (results.next()) {
                hotelIds.add(results.getInt("HotelId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return hotelIds;
    }

    public List<Integer> findHotelIdsBySleepQualityRatingAbove(float sleepQualityRating) throws SQLException {
        List<Integer> hotelIds = new ArrayList<>();
        String selectHotelIds = "SELECT DISTINCT HotelId FROM HotelsReviews WHERE SleepQuality > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHotelIds);
            selectStmt.setFloat(1, sleepQualityRating);
            results = selectStmt.executeQuery();
            while (results.next()) {
                hotelIds.add(results.getInt("HotelId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return hotelIds;
    }


    
    public HotelsReviews updateReview(HotelsReviews review) throws SQLException {
        String updateReview = "UPDATE HotelsReviews SET userName = ?, createdTime = ?, content = ?, rating = ?, hotelId = ?, service = ?, cleanliness = ?, location = ?, sleepQuality = ? WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            updateStmt.setString(1, review.getUserName());
            updateStmt.setTimestamp(2, new Timestamp(review.getCreatedTime().getTime()));
            updateStmt.setString(3, review.getContent());
            updateStmt.setDouble(4, review.getRating());
            updateStmt.setInt(5, review.getHotelId());
            updateStmt.setFloat(6, review.getService());
            updateStmt.setFloat(7, review.getCleanliness());
            updateStmt.setFloat(8, review.getLocation());
            updateStmt.setFloat(9, review.getSleepQuality());
            updateStmt.setInt(10, review.getReviewId());
            updateStmt.executeUpdate();
            return review;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    
    
    public HotelsReviews delete(int reviewId) throws SQLException {
        String deleteReview = "DELETE FROM HotelsReviews WHERE reviewId = ?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, reviewId);
            deleteStmt.executeUpdate();

            // Return null since the review is deleted
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(deleteStmt != null) {
                deleteStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }


 
}
