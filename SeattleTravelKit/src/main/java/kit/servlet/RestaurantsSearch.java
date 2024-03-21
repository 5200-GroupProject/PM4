package kit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kit.dal.RestaurantsDao;
import kit.model.Restaurants;



@WebServlet("/findRestaurants")
public class RestaurantsSearch extends HttpServlet {
	
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
		restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        

        String zipCode = req.getParameter("ZipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		restaurants = restaurantsDao.findRestaurantByZipCode(Integer.valueOf(zipCode));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + zipCode);
        	messages.put("previousZipCode", zipCode);
        }
        req.setAttribute("restaurants", restaurants);
        
        req.getRequestDispatcher("/RestaurantsSearch.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        
        String zipCode = req.getParameter("ZipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid zipcode.");
        } else {
        	try {
        		restaurants = restaurantsDao.findRestaurantByZipCode(Integer.valueOf(zipCode));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + zipCode);
        }
        req.setAttribute("restaurants", restaurants);
        
        req.getRequestDispatcher("/RestaurantsSearch.jsp").forward(req, resp);
    }
}
