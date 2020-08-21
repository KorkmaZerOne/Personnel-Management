package be.intecbrussel.data;

import be.intecbrussel.model.Project;
import be.intecbrussel.model.WorkDone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public List<Project> getAllProjects() throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Projects");

        List<Project> result = new ArrayList<>();
        while (rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("ProjectId"));
            project.setExplanation(rs.getString("Explanation"));
            project.setStartDate(rs.getDate("StartDate").toLocalDate());
            project.setPrice(rs.getInt("Price"));
            project.setEndDate(rs.getDate("EndDate").toLocalDate());
            result.add(project);
        }
        return result;
    }

    public Project getProjectById(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Projects WHERE ProjectId = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Project project = new Project();
        while (rs.next()) {
            project.setId(rs.getInt("ProjectId"));
            project.setExplanation(rs.getString("Explanation"));
            project.setStartDate(rs.getDate("StartDate").toLocalDate());
            project.setPrice(rs.getInt("Price"));
            project.setEndDate(rs.getDate("EndDate").toLocalDate());
        }
        return project;
    }

    public List<Project> getProjectsByStartDate() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Projects WHERE StartDate LIKE CURRENT_DATE()");
        ResultSet rs = statement.executeQuery();

        List<Project> result = new ArrayList<>();
        while (rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("ProjectId"));
            project.setStartDate(rs.getDate("StartDate").toLocalDate());
            project.setExplanation(rs.getString("Explanation"));
            project.setPrice(rs.getInt("Price"));
            project.setEndDate(rs.getDate("EndDate").toLocalDate());
            result.add(project);
        }
        if (result.isEmpty()) {
            System.out.println("There is no projects start today");
        } else {
            System.out.println("The projects which is start today");
        }
        return result;
    }

    public List<Project> getRecentProjectByProfitability() throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT *, Projects.Price-Employees.Salary/22/8*WorkDone.HoursWorked AS ProjectProfit "
                + "FROM ((WorkDone "
                + "LEFT OUTER JOIN Projects ON WorkDone.ProjectId = Projects.ProjectId) "
                + "LEFT OUTER JOIN Employees ON WorkDone.EmployeeId = Employees.EmployeeId) "
                + "ORDER BY ProjectProfit DESC;");

        List<Project> result = new ArrayList<>();

        while (rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("ProjectId"));
            project.setProjectProfit(rs.getDouble("ProjectProfit"));
            result.add(project);
        }
        return result;
    }

    public boolean addProject(Project project) throws SQLException {
        try {
            Connection connection = ConnectionFactory.getConnection();
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

    public boolean deleteProject(int id, int userDeleteChoice) throws SQLException {
        try {
            if (userDeleteChoice == 1) {
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Projects WHERE ProjectId = ?"
                );
                statement.setInt(1, id);
                statement.execute();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
    }
}