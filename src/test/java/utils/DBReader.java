package utils;

import utils.database.Projects;
import utils.database.Tasks;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.database.Queries.*;
import static utils.EnvProperties.DB_URL;
import static utils.EnvProperties.DB_USER_NAME;
import static utils.EnvProperties.DB_USER_PASSWORD;

public class DBReader {
    public static List<Tasks> getAllTasks(){
        List<Tasks> tasksList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_TASKS_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tasks data = new Tasks (resultSet.getString("id"), resultSet.getString("title"));
                tasksList.add(data);
                System.out.println("The Task id is "+resultSet.getInt("id")+" The Task title is "
                        +resultSet.getString("title"));
            }

        }catch(SQLException exception){
            throw new RuntimeException(String.format("Please check connection string "+"URL [%s]"
                    +" name [%s]"+" pass [%s]", DB_URL, DB_USER_NAME, DB_USER_PASSWORD));
        }
        return tasksList;
    }
    @SneakyThrows
    public static String getTaskIdFromDBByTitle(String title){
        String taskId=null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_TASK_BY_TITLE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                taskId = resultSet.getString("id");
            }

        }catch(SQLException exception){
            throw exception;
        }
        return taskId;
    }

    @SneakyThrows
    public static Tasks getTaskFromDBById(String id){
        Tasks taskInfo = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_TASK_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                taskInfo = new Tasks (resultSet.getString("id"), resultSet.getString("title"));
            }

        }catch(SQLException exception){
            throw exception;
        }
        return taskInfo;
    }

    @SneakyThrows
    public static String getCommentIdFromDBByUserTaskComment(String taskId, String userId, String comment){
        String commentId = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_COMMENT_ID);
            preparedStatement.setString(1, taskId);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, comment);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                commentId = resultSet.getString("id");
            }

        }catch(SQLException exception){
            throw exception;
        }
        return commentId;
    }
    @SneakyThrows
    public static String getProjectIdFromDBByName(String name){
        String projectId = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_PROJECT_ID);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                projectId = resultSet.getString("id");
            }

        }catch(SQLException exception){
            throw exception;
        }
        return projectId;
    }

    @SneakyThrows
    public static Projects getProjectFromDBByName(String name){
        Projects projectInfo = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_PROJECT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                projectInfo = new Projects (resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("owner_id"));
            }

        }catch(SQLException exception){
            throw exception;
        }
        return projectInfo;
    }
}
