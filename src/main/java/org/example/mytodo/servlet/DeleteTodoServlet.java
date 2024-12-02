package org.example.mytodo.servlet;

import org.example.mytodo.model.User;
import org.example.mytodo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTodo")
public class DeleteTodoServlet extends HttpServlet {
    private final TodoService todoService = new TodoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        todoService.deleteTodoByIdAndUserId(id, user.getId());
        resp.sendRedirect("/home");
    }
}
