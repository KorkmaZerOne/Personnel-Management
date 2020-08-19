package be.intecbrussel.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableFactory {

    public void createEmployeeTable() {
        String sqlEmployee = "CREATE TABLE IF NOT EXISTS Employees\n" +
                "(\n" +
                "   EmployeeId int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "   FirstName varchar(30) NOT NULL CHECK (FirstName <> ''), \n" +
                "   LastName varchar(30) NOT NULL CHECK (LastName <> ''),\n" +
                "   PhoneNumber varchar(11),\n" +
                "   PhoneNumberICE varchar(11),\n" +
                "   BirthDate date,\n" +
                "   Salary Double\n" +
                ")";

        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlEmployee);
            System.out.println("Employees table was created");
        } catch (SQLException e) {
            System.out.println("An error has occurred on table creation" + e.getMessage());
        }

    }

    public void createProjectsTable() {
        String sqlProjects = "CREATE TABLE IF NOT EXISTS Projects ("
                + " ProjectId int PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + " Explanation varchar(200), "
                + " StartDate date, "
                + " Price int, "
                + " EndDate date "
                + ")";
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlProjects);
            System.out.println("Projects table was created");
        } catch (SQLException e) {
            System.out.println("An error has occurred on table creation" + e.getMessage());
        }
    }

    public void createWorkDoneTable() {
        String sqlWorkDone = "CREATE TABLE IF NOT EXISTS WorkDone ("
                + " EmployeeId int ,"
                + " ProjectId int ,"
                + " Date date ,"
                + " HoursWorked int ,"
                + " Remarks varchar(100) "
                + ")";
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlWorkDone);
            System.out.println("WorkDone table was created");
        } catch (SQLException e) {
            System.out.println("An error has occurred on table creation" + e.getMessage());
        }
    }
}