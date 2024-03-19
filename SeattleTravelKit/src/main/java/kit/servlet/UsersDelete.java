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


@WebServlet("/userDelete")
public class UsersDelete extends HttpServlet {
	
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

        messages.put("title", "Delete User");
        req.getRequestDispatcher("/UsersDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {

        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
	        Users user = new Users(userName);
	        try {
	        	user = usersDao.deleteUser(user);
		        if (user == null) {
		            messages.put("title", "Successfully deleted " + userName);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + userName);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/UsersDelete.jsp").forward(req, resp);
    }
}
