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

import kit.dal.AttractionsDao;
import kit.model.Attractions;


@WebServlet("/findAttractions")
public class AttractionsSearch extends HttpServlet{
	protected AttractionsDao attractionsDao;
	
	@Override
	public void init() throws ServletException {
		attractionsDao = attractionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        List<Attractions> attractions = new ArrayList<Attractions>();
	        
	        String ZipCode = req.getParameter("ZipCode");
	        
	        if (ZipCode == null || ZipCode.trim().isEmpty()) {
	            messages.put("success", "Please enter a valid zipcode.");
	        } else {
	        	try {
	            	attractions = attractionsDao.findAttractionByZipCode(Integer.valueOf(ZipCode));
	            } catch (SQLException e) {
	    			e.printStackTrace();
	    			throw new IOException(e);
	          }
	        	messages.put("success", "Displaying results for " + ZipCode);
	        	messages.put("previousZipCode", ZipCode);
	        	
	        }
	        req.setAttribute("attractions", attractions);
	        req.getRequestDispatcher("/AttractionsSearch.jsp").forward(req, resp);
	}
	

}
