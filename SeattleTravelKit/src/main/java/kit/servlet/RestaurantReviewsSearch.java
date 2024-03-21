package kit.servlet;

import kit.dal.RestaurantReviewsDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/findRestaurantReviews")
public class RestaurantReviewsSearch extends HttpServlet {

    protected RestaurantReviewsDao restaurantReviewsDao;

    @Override
    public void init() throws ServletException {
        restaurantReviewsDao = RestaurantReviewsDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Integer> restaurantIds;

        String foodQualityRatingStr = req.getParameter("foodQualityRating");
        if (foodQualityRatingStr == null || foodQualityRatingStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid food quality rating.");
        } else {
            try {
                double foodQualityRating = Double.parseDouble(foodQualityRatingStr);
                restaurantIds = restaurantReviewsDao.findRestaurantsByFoodQualityAbove(foodQualityRating);
                if (restaurantIds.isEmpty()) {
                    messages.put("success", "No restaurants found with food quality rating above: " + foodQualityRating);
                } else {
                    messages.put("success", "Displaying restaurants with food quality rating above: " + foodQualityRating);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            req.setAttribute("restaurantIds", restaurantIds);
        }
        req.getRequestDispatcher("/RestaurantReviewsSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
