package be.intecbrussel.data;

import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;
import be.intecbrussel.model.WorkDone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDoneDAO {

    public List<WorkDone> getAllWorkDone() throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM WorkDone");

        List<WorkDone> result = new ArrayList<>();
        while (rs.next()) {
            WorkDone workDone = new WorkDone();
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getDate("Date").toLocalDate());
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setRemarks(rs.getString("Remarks"));
            result.add(workDone);
        }
        return result;
    }

    public List<WorkDone> getWorkDoneByProjectId(int projectId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WorkDone WHERE ProjectId = ?");
        statement.setInt(1, projectId);
        ResultSet rs = statement.executeQuery();

        List<WorkDone> result = new ArrayList<>();
        while (rs.next()) {
            WorkDone workDone = new WorkDone();
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getDate("Date").toLocalDate());
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setRemarks(rs.getString("Remarks"));
            result.add(workDone);
        }
        return result;
    }

    public WorkDone getWorkDoneByTwoId(int projectId, int employeeId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM WorkDone WHERE ProjectId = ? AND EmployeeId = ?");
        statement.setInt(1, projectId);
        statement.setInt(2, employeeId);
        ResultSet rs = statement.executeQuery();

        WorkDone workDone = new WorkDone();
        while (rs.next()) {
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getDate("Date").toLocalDate());
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setRemarks(rs.getString("Remarks"));
        }
        return workDone;
    }

    public List<WorkDone> getTopThreeEmployee() throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * "
                        + "FROM WorkDone "
                        + "ORDER BY HoursWorked DESC "
                        + "LIMIT 3");

        List<WorkDone> result = new ArrayList<>();
        while (rs.next()) {
            WorkDone workDone = new WorkDone();
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getDate("Date").toLocalDate());
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setRemarks(rs.getString("Remarks"));
            result.add(workDone);
        }
        return result;
    }

    public boolean addWorkDone(WorkDone workDone) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO WorkDone VALUES"
                            + "('" + workDone.getEmployeeId()
                            + "' , '" + workDone.getProjectId()
                            + "' , '" + workDone.getDate()
                            + "' , '" + workDone.getWorkingHours()
                            + "' , '" + workDone.getRemarks()
                            + "' )"
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;

    }

    public boolean updateWorkDone(WorkDone workDone, int updatedProjectId, int updatedEmployeeId) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE WorkDone SET \n" +
                            "EmployeeId=\n'" + workDone.getEmployeeId() + "'," +
                            "ProjectId= \n'" + workDone.getProjectId() + "'," +
                            "Date=\n'" + workDone.getDate() + "'," +
                            "HoursWorked=\n'" + workDone.getWorkingHours() + "'," +
                            "Remarks=\n'" + workDone.getRemarks() +
                            "' WHERE EmployeeId = " + updatedEmployeeId +
                            " AND ProjectId = " + updatedProjectId
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteWorkDone(int projectId, int employeeId, int userDeleteChoice) {
        try {
            if (userDeleteChoice == 1) {
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM WorkDone WHERE ProjectId = ? AND EmployeeId = ?"
                );
                statement.setInt(1, projectId);
                statement.setInt(2, employeeId);
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



