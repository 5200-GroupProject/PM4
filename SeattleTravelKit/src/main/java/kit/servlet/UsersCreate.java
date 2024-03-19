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


@WebServlet("/userCreate")
public class UsersCreate extends HttpServlet {
	
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

        req.getRequestDispatcher("/UsersCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
					String email = req.getParameter("email");
					String password = req.getParameter("password");
		        	String firstName = req.getParameter("firstname");
		        	String lastName = req.getParameter("lastname");
					long phone = Integer.valueOf(req.getParameter("phone"));

					Users user = new Users(userName, email, password, firstName, lastName, phone);
					try {
						user = usersDao.create(user);
						messages.put("success", "Successfully created " + userName);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
        req.getRequestDispatcher("/UsersCreate.jsp").forward(req, resp);
    }
}
