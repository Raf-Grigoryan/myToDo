package org.example.mytodo.servlet;

import org.example.mytodo.model.ToDo;
import org.example.mytodo.model.User;
import org.example.mytodo.service.TodoService;
import org.example.mytodo.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addTodo")
public class AddTodoServlet extends HttpServlet {
    private final TodoService todoService = new TodoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String createdDateStr = request.getParameter("created_date");
        Date created_date;
        if (createdDateStr == null) {
            created_date = DateUtil.parseWebDate(createdDateStr);
        } else {
            created_date = new Date();
        }


        StringBuilder errorMsg = new StringBuilder();
        if (title == null || title.trim().isEmpty()) {
            errorMsg.append("title cannot be empty <br>");
        }


        if (!errorMsg.isEmpty()) {
            request.getSession().setAttribute("errorMsg", errorMsg.toString());
        } else {
            ToDo toDo = ToDo.builder()
                    .title(title)
                    .createDate(created_date)
                    .user(user)
                    .build();
            todoService.addTodo(toDo);
            response.sendRedirect("/home");
        }
    }
}
