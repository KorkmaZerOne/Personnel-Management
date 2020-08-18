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
            workDone.setDate(rs.getDate("Date").toLocalDate());
            workDone.setWorkingHours(rs.getInt("HoursWorked"));
            workDone.setRemarks(rs.getString("Remarks"));
            result.add(workDone);
        }
        return result;
    }

    public List <WorkDone> getWorkDoneByProjectId(int projectId) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM WorkDone WHERE ProjectId = ?");
        statement.setInt(1, projectId);
        ResultSet rs = statement.executeQuery();

        List <WorkDone> result = new ArrayList<>();
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

    public WorkDone getWorkDoneByTwoId(int projectId , int employeeId) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM WorkDone WHERE ProjectId =" +  projectId + "AND EmployeeId =" + employeeId
        );
       // statement.setInt(1, employeeId);
        //statement.setInt(2, projectId);
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
                    "UPDATE WorkDone SET \n" +
                            "EmployeeId=\n'" + workDone.getEmployeeId() + "'," +
                            "ProjectId= \n'" + workDone.getProjectId() + "'," +
                            "Date=\n'" + workDone.getDate() + "'," +
                            "HoursWorked=\n'" + workDone.getWorkingHours() + "'," +
                            "Remarks=\n'" + workDone.getRemarks() +
                            " WHERE EmployeeId = " + workDone.getEmployeeId() +
                            "AND ProjectId = " + workDone.getProjectId()
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteWorkDone(int projectId , int employeeId) {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM WorkDone WHERE ProjectId =" +  projectId + "AND EmployeeId =" + employeeId
            );
            //statement.setInt(1, employeeId);
            //statement.setInt(2, projectId);

            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }
}



