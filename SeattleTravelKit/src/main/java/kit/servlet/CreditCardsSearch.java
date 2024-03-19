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
import kit.dal.CreditCardsDao;
import kit.model.CreditCards;


@WebServlet("/findCreditCards")
public class CreditCardsSearch extends HttpServlet {
	
	protected CreditCardsDao creditCardsDao;
	
	@Override
	public void init() throws ServletException {
		creditCardsDao = creditCardsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CreditCards> creditCards = new ArrayList<CreditCards>();

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            	creditCards = creditCardsDao.findCardsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
          }
        	messages.put("success", "Displaying results for " + userName);
        	messages.put("previousFirstName", userName);
        }
        req.setAttribute("cards", creditCards);
        req.getRequestDispatcher("/CreditCardsSearch.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CreditCards> creditCards = new ArrayList<CreditCards>();

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            creditCards = creditCardsDao.findCardsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("cards", creditCards);
        req.getRequestDispatcher("/CreditCardsSearch.jsp").forward(req, resp);
    }
}
