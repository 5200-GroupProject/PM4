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

import kit.dal.HotelsDao;
import kit.model.Hotels;



@WebServlet("/findHotels")
public class HotelsSearch extends HttpServlet {
	
	protected HotelsDao hotelsDao;
	
	@Override
	public void init() throws ServletException {
		hotelsDao = HotelsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Hotels> hotels = new ArrayList<Hotels>();
        

        String zipCode = req.getParameter("ZipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            	hotels = hotelsDao.findHotelZipCode(Integer.valueOf(zipCode));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + zipCode);
        	messages.put("previousZipCode", zipCode);
        }
        req.setAttribute("hotels", hotels);
        
        req.getRequestDispatcher("/HotelsSearch.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Hotels> hotels = new ArrayList<Hotels>();
        
        String zipCode = req.getParameter("ZipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid zipcode.");
        } else {
        	try {
            	hotels = hotelsDao.findHotelZipCode(Integer.valueOf(zipCode));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + zipCode);
        }
        req.setAttribute("hotels", hotels);
        
        req.getRequestDispatcher("/HotelsSearch.jsp").forward(req, resp);
    }
}
