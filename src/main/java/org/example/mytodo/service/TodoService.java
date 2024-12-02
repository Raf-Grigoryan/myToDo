package org.example.mytodo.service;

import org.example.mytodo.db.DBConnectionProvider;
import org.example.mytodo.model.Status;
import org.example.mytodo.model.ToDo;
import org.example.mytodo.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserService userService = new UserService();

    public void addTodo(ToDo toDo) {
        try {
            String sql = "INSERT INTO todo(title,created_date,user_id,status) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setString(2, DateUtil.formatDate(toDo.getCreateDate()));
            preparedStatement.setInt(3, toDo.getUser().getId());
            preparedStatement.setString(4, Status.NEW.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeStatusToDoneByToDoId(int id, int userId) {
        try {
            String sql = "UPDATE todo SET status = ?, finish_date = NOW() WHERE id = ? AND user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Status.DONE.name());
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTodoByIdAndUserId(int id, int user_id) {
        try {
            String sql = "DELETE FROM todo WHERE id = ? AND user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ToDo> getTodoByUserId(int userId) {
        List<ToDo> toDos = new ArrayList<>();
        try {
            String sql = "SELECT * from todo WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ToDo todo = ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .user(userService.findUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status"))).build();

                String createdDateStr = resultSet.getString("created_date");
                if (createdDateStr != null && !createdDateStr.isEmpty()) {
                    todo.setCreateDate(DateUtil.parseDate(createdDateStr));
                }

                String finishDateStr = resultSet.getString("finish_date");
                if (finishDateStr != null && !finishDateStr.isEmpty()) {
                    todo.setFinishedDate(DateUtil.parseDate(finishDateStr));
                }

                toDos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }


}
