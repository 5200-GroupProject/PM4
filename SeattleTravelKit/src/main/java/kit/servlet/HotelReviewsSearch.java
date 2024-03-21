package kit.servlet;

import kit.dal.HotelsReviewsDao;
import kit.model.HotelsReviews;

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


@WebServlet("/findHotelReviews")
public class HotelReviewsSearch extends HttpServlet {

	protected HotelsReviewsDao hotelsReviewsDao;

    @Override
    public void init() throws ServletException {
        hotelsReviewsDao = HotelsReviewsDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Integer> hotelIds = null;

        // Get the cleanliness rating from the request.
        String cleanlinessRatingStr = req.getParameter("cleanlinessRating");
        if (cleanlinessRatingStr == null || cleanlinessRatingStr.trim().isEmpty()) {
            messages.put("success", "Please enter a cleanliness rating.");
        } else {
            try {
                float cleanlinessRating = Float.parseFloat(cleanlinessRatingStr);
                hotelIds = hotelsReviewsDao.findHotelIdsByCleanlinessRatingAbove(cleanlinessRating);
                if (hotelIds.isEmpty()) {
                    messages.put("success", "No hotels found with cleanliness rating above " + cleanlinessRating);
                } else {
                    messages.put("success", hotelIds.size() + " hotels found with cleanliness rating above " + cleanlinessRating);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("hotelIds", hotelIds);
        req.getRequestDispatcher("/HotelReviewsSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}