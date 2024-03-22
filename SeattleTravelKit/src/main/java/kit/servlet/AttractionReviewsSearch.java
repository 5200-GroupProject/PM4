//package kit.servlet;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import kit.dal.AttractionReviewsDao;
//import kit.model.AttractionReviews;
//import kit.model.Hotels;
//
//
//@WebServlet("/findAttractionReviews")
//public class AttractionReviewsSearch extends HttpServlet {
//    
//    protected AttractionReviewsDao attractionReviewsDao;
//    
//    @Override
//    public void init() throws ServletException {
//        attractionReviewsDao = AttractionReviewsDao.getInstance();
//    }
//    
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        
//        // Retrieve and validate duration.
//        String duration = req.getParameter("duration");
//        
//        if (duration == null || duration.trim().isEmpty()) {
//            messages.put("title", "Please enter a duration to search.");
//        } else {
//            // Only search if duration is provided
//            try {
//                List<AttractionReviews> attractionReviews = attractionReviewsDao.findAttractionsByDurationAbove(duration);
//                req.setAttribute("attractionReviews", attractionReviews);
//                messages.put("title", "AttractionReviews with Duration above " + duration);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new IOException(e);
//            }
//        }
//        
//        req.setAttribute("messages", messages);
//        req.getRequestDispatcher("/AttractionReviewsSearch.jsp").forward(req, resp);
//    }
//    
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        doGet(req, resp);
//    }   
//}




package kit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kit.dal.AttractionReviewsDao;
import kit.model.AttractionReviews;

@WebServlet("/findAttractionReviews")
public class AttractionReviewsSearch extends HttpServlet {
    
    protected AttractionReviewsDao attractionReviewsDao;
    
    @Override
    public void init() throws ServletException {
        attractionReviewsDao = AttractionReviewsDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        
        // Retrieve and validate duration.
        String duration = req.getParameter("duration");
        if (duration == null || duration.trim().isEmpty() || (!duration.matches("\\d+ minutes") && !duration.matches("\\d+"))) {
            messages.put("error", "Please enter a valid duration in the format of 'number minutes', e.g., '55' or '55 minutes'.");
        } else {
            // Only search if duration is provided and valid
            try {
                List<AttractionReviews> attractionReviews = attractionReviewsDao.findAttractionsByDurationAbove(duration);
                req.setAttribute("attractionReviews", attractionReviews);
                messages.put("title", "AttractionReviews with Duration above " + duration);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            } catch (IllegalArgumentException e) {
                messages.put("error", e.getMessage());
            }
        }
        
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/AttractionReviewsSearch.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }   
}
