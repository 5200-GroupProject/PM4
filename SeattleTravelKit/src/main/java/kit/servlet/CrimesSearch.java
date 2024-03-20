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
import kit.dal.CrimesDao;
import kit.model.Crimes;


@WebServlet("/findCrimes")
public class CrimesSearch extends HttpServlet {
	
	protected CrimesDao crimesDao;
	
	@Override
	public void init() throws ServletException {
		crimesDao = crimesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Crimes> crimes = new ArrayList<Crimes>();

        String zipCode = req.getParameter("zipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid zip code.");
        } else {
        	try {
            	crimes = crimesDao.findCrimesByZipCode(zipCode);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
          }
        	messages.put("success", "Displaying results for " + zipCode);
        	messages.put("previousZipCode", zipCode);
        }
        req.setAttribute("crimes", crimes);
        req.getRequestDispatcher("/CrimesSearch.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Crimes> crimes = new ArrayList<Crimes>();

        String zipCode = req.getParameter("zipCode");
        if (zipCode == null || zipCode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            crimes = crimesDao.findCrimesByZipCode(zipCode);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
          }
        	messages.put("success", "Displaying results for " + zipCode);
        }
        req.setAttribute("crimes", crimes);
        req.getRequestDispatcher("/CrimesSearch.jsp").forward(req, resp);
    }
}
