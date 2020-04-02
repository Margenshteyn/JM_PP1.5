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


@WebServlet("/user/userAdd")
public class UserRegistration extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req, resp);
        if (user.getLogin() == null || user.getLogin().equals("")) {
            resp.sendRedirect("/user/userAdd?wrongLogin=" + user.getLogin());
        }
        else if (userService.addUser(user)) {
            resp.sendRedirect("/user/room?addUserLogin=" + user.getLogin());
        } else {
            resp.sendRedirect("/user/userAdd?wrongRequest=" + user.getLogin());
        }
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String am = req.getParameter("amount");
        if (am.equals("")) {
            am = "0";
        }
        double amount = Double.parseDouble(am);
        String role = "User";
        return new User(login, password, name, amount, role);
    }
}