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

@WebServlet("/admin/userUpdate")
public class UserUpdateServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/userUpdate.jsp").forward(req, resp);// перекинул на нужную jsp
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = getUser(req, resp);
        String password = req.getParameter("password");
        if (userService.updateUser(user, password)) {
            resp.sendRedirect("/admin/list?updatedLogin=" + user.getLogin());
        } else {
            resp.sendRedirect("/admin/list?wrongUpdate=" + user.getLogin());
        }
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("newPassword");
        String name = req.getParameter("newName");
        double amount = Double.parseDouble(req.getParameter("newAmount"));
        String role = req.getParameter("newRole");
        return new User(login, password, name, amount, role);
    }
}
