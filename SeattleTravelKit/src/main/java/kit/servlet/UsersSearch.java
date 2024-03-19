package kit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kit.dal.UsersDao;
import kit.model.Users;


@WebServlet("/findUsers")
public class UsersSearch extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = usersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = null;

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            	user = usersDao.findUserByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	messages.put("previousFirstName", userName);
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/UsersSearch.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = null;

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
            user = usersDao.findUserByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/UsersSearch.jsp").forward(req, resp);
    }
}
