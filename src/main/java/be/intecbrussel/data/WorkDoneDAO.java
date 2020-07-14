package be.intecbrussel.data;

import be.intecbrussel.model.Employee;
import be.intecbrussel.model.WorkDone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDoneDAO {

    public List<WorkDone> getAllWorkDone() throws SQLException {

        Connection connection = ConnectionPort.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM WorkDone");

        List<WorkDone> result = new ArrayList<>();
        while (rs.next()) {
            WorkDone workDone = new WorkDone();
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getString("Date"));
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setWarnings(rs.getString("Warnings"));
            result.add(workDone);
        }
        return result;
    }

    public List<WorkDone> getWorkDoneByEmployee(int employeeId, int projectId) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM WorkDone WHERE EmployeeId = ? AND ProjectId = ?");

        List<WorkDone> result = new ArrayList<>();
        while (rs.next()) {
            WorkDone workDone = new WorkDone();
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getString("Date"));
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setWarnings(rs.getString("Warnings"));
            result.add(workDone);

        }
        return result;
    }

    public boolean addWorkDone(WorkDone workDone) {
        try {
            Connection connection = ConnectionPort.getConnection();
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

    public boolean updateWorkDone(WorkDone workDone) {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "update WorkDone set \n" +
                            "EmployeeId=\n'" + workDone.getEmployeeId() + "'," +
                            "ProjectId= \n'" + workDone.getProjectId() + "'," +
                            "Date=\n'" + workDone.getDate() + "'," +
                            "HoursWorked=\n'" + workDone.getWorkingHours() + "'," +
                            "Remarks=\n'" + workDone.getRemarks() +
                            " where EmployeeId = " + workDone.getEmployeeId()
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public WorkDone getWorkDoneByProject(int employeeId , int projectId) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WorkDone WHERE EmployeeId = ? AND ProjectId = ?");
        statement.setInt(1, employeeId);
        statement.setInt(2, projectId);
        ResultSet rs = statement.executeQuery();

        WorkDone workDone = new WorkDone();
        while (rs.next()) {
            workDone.setEmployeeId(rs.getInt("EmployeeId"));
            workDone.setProjectId(rs.getInt("ProjectId"));
            workDone.setDate(rs.getString("Date"));
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setWarnings(rs.getString("Warnings"));
        }
        return workDone;
    }

    public boolean deleteWorkDone(int employeeId, int projectId) {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "delete from WorkDone where EmployeeId = ? AND ProjectId = ?"
            );
            statement.setInt(1, employeeId);
            statement.setInt(2, projectId);

            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }
}



