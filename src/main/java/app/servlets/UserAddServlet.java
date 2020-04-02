package app.servlets;

import app.entities.User;
import app.service.UserService;
import app.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/userAdd")
public class UserAddServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = getUser(req, resp);
        if (user.getLogin() == null || user.getLogin().equals("")) {
            getServletContext().getRequestDispatcher("/admin/list?wrongLogin=");
        } else if (userService.addUser(user)) {
            getServletContext().getRequestDispatcher("/admin/list?addUserLogin=" + user.getLogin()).forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/admin/list?wrongRequest=" + user.getLogin()).forward(req, resp);
        }
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String am = req.getParameter("amount");
        if (am.equals("")) {
            am = "0";
        }
        double amount = Double.parseDouble(am);
        String role = req.getParameter("role");
        if (login.trim().length() == 0) {
            getServletContext().getRequestDispatcher("/admin/list?wrongData=").forward(req, resp);
            // getServletContext().getRequestDispatcher("/views/list.jsp").forward(req, resp);// перекинул на нужную jsp
        }
        return new User(login, password, name, amount, role);
    }
}
