package be.intecbrussel.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableFactory {

    public void createEmployeeTable() {
        String sqlEmployee = "CREATE TABLE IF NOT EXISTS Employees\n" +
                "(\n" +
                "   EmployeeId int PRIMARY KEY NOT NULL,\n" +
                "   FirstName varchar(30),\n" +
                "   LastName varchar(30),\n" +
                "   PhoneNumber varchar(11),\n" +
                "   PhoneNumberICE varchar(11),\n" +
                "   BirthDate date,\n" +
                "   Salary int\n" +
                ")";
        try {
            Connection conn = ConnectionPort.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlEmployee);
            System.out.println("Employees table was created");
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on table creation" + e.getMessage());
        }

    }
    public void createProjectsTable() {
        String sqlProjects = "CREATE TABLE IF NOT EXISTS Projects\n" +
                "(\n" +
                "   ProjectId int PRIMARY KEY NOT NULL,\n" +
                "   Explanation varchar(200),\n" +
                "   StartDate date,\n" +
                "   Price int,\n" +
                "   EndDate date\n" +
                ")";
        try {
            Connection conn = ConnectionPort.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlProjects);
            System.out.println("Projects table was created");
        } catch (SQLException e) {
            System.out.println("An error has occurred on table creation" + e.getMessage());
        }
    }
    public void createWorkDoneTable () {
            String sqlWorkDone = "CREATE TABLE IF NOT EXISTS WorkDone\n" +
                    "(\n" +
                    "   FOREIGN KEY (EmployeeId) REFERENCES Employees (EmployeeId),\n" +
                    "   FOREIGN KEY (ProjectId) REFERENCES Projects (ProjectId),\n" +
                    "   Date date,\n" +
                    "   Remarks varchar(100),\n" +
                    "   HoursWorked int\n" +
                    ")";
            try {
                Connection conn = ConnectionPort.getConnection();
                Statement statement = conn.createStatement();
                statement.executeUpdate(sqlWorkDone);
                System.out.println("Projects table was created");
            } catch (SQLException e) {
                System.out.println("An error has occurred on table creation" + e.getMessage());
            }
        }
    }