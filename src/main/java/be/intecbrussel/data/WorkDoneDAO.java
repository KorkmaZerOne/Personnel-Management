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
}
