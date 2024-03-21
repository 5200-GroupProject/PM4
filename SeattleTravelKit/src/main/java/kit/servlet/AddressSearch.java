package kit.servlet;

import kit.dal.AddressDao;
import kit.model.Address;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findAddressByName")
public class AddressSearch extends HttpServlet {

    protected AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        addressDao = AddressDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        Address address = null;

        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid username.");
        } else {
            try {
                address = addressDao.findAddressByName(userName);
                if (address == null) {
                    messages.put("success", "Address not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("address", address);
        req.getRequestDispatcher("/AddressSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
