package kit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kit.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kit.dal.CrimesDao;

@WebServlet("/safestZipCodes")
public class SafestZipCodes extends HttpServlet {
    
    protected CrimesDao crimesDao;
    
    @Override
    public void init() throws ServletException {
        crimesDao = CrimesDao.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<CityCrimeStats> safestZipCodes;
        
        try {
            safestZipCodes = crimesDao.getTop10CitiesWithLeastCrimes();
            messages.put("success", "Displaying the top 10 safest zip codes.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.setAttribute("safestZipCodes", safestZipCodes);
        
        req.getRequestDispatcher("/SafestZipCodes.jsp").forward(req, resp);
    }
    

}