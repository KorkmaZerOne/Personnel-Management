package be.intecbrussel.data;

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
            project.setExplanation(rs.getString("Explanation"));
            project.setStartDate(rs.getString("StartDate"));
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
                    "INSERT INTO Projects VALUES"
                            + "('" + project.getId()
                            + "' , '" + project.getExplanation()
                            + "' , '" + project.getStartDate()
                            + "' , '" + project.getPrice()
                            + "' , '" + project.getEndDate()
                            + "' )"
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

    public List<Project> getProjectsByStartDate() throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Projects WHERE StartDate LIKE SYSDATE()");
        ResultSet rs = statement.executeQuery();

        List<Project> result = new ArrayList<>();
        while (rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("Id"));
            project.setStartDate(rs.getString("StartDate"));
            project.setExplanation(rs.getString("Explanation"));
            project.setPrice(rs.getInt("Price"));
            project.setEndDate(rs.getString("EndDate"));
            result.add(project);
        }
        if (result.isEmpty()) {
            System.out.println("There is no projects start today");
        } else {
            System.out.println("The projects which is start today");
        }
        return result;
    }
}