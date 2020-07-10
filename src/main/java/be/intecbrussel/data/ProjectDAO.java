package be.intecbrussel.data;

import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public List<Project> getAllProjects() throws SQLException {

        Connection connection = ConnectionPort.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Projects");

        List<Project> result = new ArrayList<>();
        while (rs.next()) {
            Project project = new Project();
            project.setStartDate(rs.getString("StartDate"));
            project.setExplanation(rs.getString("Explanation"));
            project.setPrice(rs.getInt("Price"));
            project.setEndDate(rs.getString("EndDate"));
            result.add(project);
        }
        return result;
    }

    public boolean addProject(Project project) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Projects (\n" +
                            "ProjectId,\n" +
                            "Explanation,\n" +
                            "StartDate,\n" +
                            "Price,\n" +
                            "EndDate) \n" +
                            "VALUES (\n" +
                            project.getId()+",\n" +
                            "'"+project.getExplanation()+"',\n" +
                            "'"+project.getStartDate()+"',\n" +
                            "'"+project.getPrice()+"',\n" +
                            project.getEndDate()+")"
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteProject(int id) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "delete from Projects where ProjectId = ?"
            );
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }
}