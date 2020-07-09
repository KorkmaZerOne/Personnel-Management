package be.intecbrussel.data;

import be.intecbrussel.model.Project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            project.setStartDate(rs.getDate("StartDate"));
            project.setExplanation(rs.getString("Explanation"));
            project.setPrice(rs.getDouble("Price"));
            project.setEndDate(rs.getDate("EndDate"));
            result.add(project);
        }
        return result;
    }
}