package be.intecbrussel.data;

import java.sql.Connection;
import java.sql.ResultSet;
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
            System.out.println("Table Created");
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation" + e.getMessage());

        }

    }
    public void createProjectTable() {
        String sqlProject = "CREATE TABLE IF NOT EXISTS Projects (" +
                "'ProjectId' INT(5) PRIMARY KEY ," +
                "'Explanation' VARCHAR(100) NOT NULL ," +
                "'StartDate' DATE(10) NOT NULL ," +
                "'Price' INT(8) ," +
                "'EndDate' DATE(10)";
        try {
            Connection conn = ConnectionPort.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlProject);
            System.out.println("Table Created");
        } catch (SQLException e) {
            System.out.println("An error has occurred on Table Creation");
            }
        }
        public void createWorkDoneTable () {
            String sqlWorkDone = "CREATE TABLE IF NOT EXISTS WorkDone (" +
                    "FOREIGN KEY (EmployeeId) REFERENCES Employees (EmployeeId) ," +
                    "FOREIGN KEY (ProjectId) REFERENCES Projects (ProjectId) ," +
                    "'Date' DATE(10) NOT NULL ," +
                    "'HoursWorked' INT(8) ," +
                    "'Remarks' VARCHAR(100)";
            try {
                Connection conn = ConnectionPort.getConnection();
                Statement statement = conn.createStatement();
                statement.executeQuery(sqlWorkDone);
            } catch (SQLException e) {
                System.out.println("An error has occurred on Table Creation");
            }
        }
    }