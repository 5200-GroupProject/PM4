package kit.dal;
import kit.model.Reviews;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ReviewsDao {
    protected ConnectionManager connectionManager;

    private static ReviewsDao instance = null;
    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static ReviewsDao getInstance() {
        if(instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews review) throws SQLException {
        String insertReview = "INSERT INTO Reviews(UserName, CreatedTime, Content, Rating) " +
                "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview,
                Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, review.getUserName());
            insertStmt.setTimestamp(2, new Timestamp(review.getCreatedTime().getTime()));
            insertStmt.setString(3, review.getContent());
            insertStmt.setDouble(4, review.getRating());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if(resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);
            return review;
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
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Reviews findReviewByReviewId(int reviewId) throws SQLException {
        String selectReview = "SELECT ReviewId, UserName, CreatedTime, Content, Rating FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                Reviews review = new Reviews(results.getInt("ReviewId"),
                                              results.getString("UserName"),
                                              new Date(results.getTimestamp("CreatedTime").getTime()),
                                              results.getString("Content"),
                                              results.getDouble("Rating"));
                return review;
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

    public List<Reviews> findReviewsByUserName(String userName) throws SQLException {
        List<Reviews> reviews = new ArrayList<>();
        String selectReviews = "SELECT ReviewId, UserName, CreatedTime, Content, Rating FROM Reviews WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while(results.next()) {
                Reviews review = new Reviews(results.getInt("ReviewId"),
                                              results.getString("UserName"),
                                              new Date(results.getTimestamp("CreatedTime").getTime()),
                                              results.getString("Content"),
                                              results.getDouble("Rating"));
                reviews.add(review);
            }
            return reviews;
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
    }

    public List<Reviews> findReviewsRatingAbove(double rating) throws SQLException {
        List<Reviews> reviews = new ArrayList<>();
        String selectReviews = "SELECT ReviewId, UserName, CreatedTime, Content, Rating FROM Reviews WHERE Rating > ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setDouble(1, rating);
            results = selectStmt.executeQuery();
            while(results.next()) {
                Reviews review = new Reviews(results.getInt("ReviewId"),
                                              results.getString("UserName"),
                                              new Date(results.getTimestamp("CreatedTime").getTime()),
                                              results.getString("Content"),
                                              results.getDouble("Rating"));
                reviews.add(review);
            }
            return reviews;
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
    }

    
    
   
    public Reviews updateReview(Reviews review, String newContent) throws SQLException {
        String updateReview = "UPDATE Reviews SET Content=? WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            updateStmt.setString(1, newContent);
            updateStmt.setInt(2, review.getReviewId());
            updateStmt.executeUpdate();

            review.setContent(newContent);
            return review;
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

    public Reviews delete(Reviews review) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
