package org.example.mytodo.servlet;

import org.example.mytodo.model.User;
import org.example.mytodo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.findUserByEmailAndPassword(email, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("loginMsg", "Email or password incorrect");
            resp.sendRedirect("/login");
        }
    }
}
