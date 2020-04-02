package app.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/users/*")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("User") == null) { // если не авторизован или не User (кто-то кто не User, например Admin)
            if (session != null && session.getAttribute("Admin") == null) { // если авторизован и не Admin
                servletRequest.getServletContext().getRequestDispatcher("/index").forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}
