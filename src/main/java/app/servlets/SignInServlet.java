package app.servlets;

import app.entities.User;
import app.service.UserService;
import app.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class SignInServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     /*   String login = "admin";
        String password = "admin";
        String name = "Maxim";
        double amount = Double.parseDouble("7000000000");
        String role = "Admin";
        User admin = new User(login, password, name, amount, role);
        userService.addUser(admin);*/
        req.getRequestDispatcher("views/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String login = req.getParameter("login"); // получил параметр из формы
        String password = req.getParameter("password");
        User user = userService.getUserByLogin(login);
        if (user == null) {
            resp.sendRedirect("/index?wrongSignIn=" + login);
        } else if(userService.validateUser(login, password)) {
            // проверять роль
            if (user.getRole().equals("Admin")) {
                session.setAttribute("Admin", login);
                resp.sendRedirect( req.getContextPath() + "/admin/list");
            } else if (user.getRole().equals("User")){
                session.setAttribute("User", login);
                resp.sendRedirect(req.getContextPath() + "/user/room?userInRoom=" + login);
            }
        } else {
            resp.sendRedirect("/index?wrongPassword=");
        }
    }
}
