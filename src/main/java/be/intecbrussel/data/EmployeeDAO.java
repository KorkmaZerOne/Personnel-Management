package be.intecbrussel.data;

import be.intecbrussel.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() throws SQLException {

        Connection conn = ConnectionPort.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Employees");

        List<Employee> result = new ArrayList<>();
        while (rs.next()) {
            Employee employees = new Employee();
            employees.setId(rs.getInt("EmployeeId"));
            employees.setFirstName(rs.getString("FirstName"));
            employees.setLastName(rs.getString("LastName"));
            employees.setPhoneNumber(rs.getString("PhoneNumber"));
            employees.setPhoneNumberICE(rs.getString("PhoneNumberICE"));
            employees.setDateOfBirth(rs.getDate("BirthDate").toLocalDate());
            employees.setSalary(rs.getDouble("Salary"));
            result.add(employees);
        }
        return result;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees WHERE EmployeeId = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Employee employees = new Employee();
            while (rs.next()) {
                employees.setId(rs.getInt("EmployeeId"));
                employees.setFirstName(rs.getString("FirstName"));
                employees.setLastName(rs.getString("LastName"));
                employees.setPhoneNumber(rs.getString("PhoneNumber"));
                employees.setPhoneNumberICE(rs.getString("PhoneNumberICE"));
                employees.setDateOfBirth(rs.getDate("BirthDate").toLocalDate());
                employees.setSalary(rs.getDouble("Salary"));
            }
        return employees;
    }

    public List<Employee> getEmployeesByName(String name) throws SQLException {
        Connection connection = ConnectionPort.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees WHERE FirstName LIKE ? OR LastName LIKE ?");
        statement.setString(1, name);
        statement.setString(2, name);
        ResultSet rs = statement.executeQuery();

        List<Employee> result = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setFirstName(rs.getString("FirstName"));
            employee.setLastName(rs.getString("LastName"));
            result.add(employee);
        }

        if (result.isEmpty()) {
            System.out.println("Employee you have searched is not exist");
        } else {
            return result;
        }
        return result;
    }
        public List<Employee> getEmployeesByBirthDate () throws SQLException {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from Employees E where " +
                    "DATE_ADD(BirthDate, INTERVAL YEAR(CURDATE())-YEAR(BirthDate) + " +
                    "IF(DAYOFYEAR(CURDATE()) > DAYOFYEAR(BirthDate) ,1,0) YEAR) " +
                    "BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)");
            ResultSet rs = statement.executeQuery();

            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setPhoneNumberICE(rs.getString("PhoneNumberICE"));
                employee.setDateOfBirth(rs.getDate("BirthDate").toLocalDate());
                employee.setSalary(rs.getDouble("Salary"));
                result.add(employee);
            }
            if (result.isEmpty()){
                System.out.println("There is no one you may celebrate birthday");
            } else {
                System.out.println("Celebrate the employee birthday");
            }
            return result;
        }

    public boolean addEmployee(Employee employee) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Employees VALUES"
                        + "('" + employee.getId()
                        + "' , '" + employee.getFirstName()
                        + "' , '" + employee.getLastName()
                        + "' , '" + employee.getPhoneNumber()
                        + "' , '" + employee.getPhoneNumberICE()
                        + "' , '" + employee.getDateOfBirth()
                        + "' , '" + employee.getSalary()
                        + "' )"
            );
            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + "INSERT OBLIGATE FIELDS "+ e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Employees SET " +
                            "EmployeeId='" + employee.getId()+"',"+
                            "FirstName='" +employee.getFirstName()+"',"+
                            "LastName='" +employee.getLastName()+"',"+
                            "PhoneNumber='" +employee.getPhoneNumber()+"',"+
                            "PhoneNumberICE='" +employee.getPhoneNumberICE()+"',"+
                            "BirthDate='" +employee.getDateOfBirth()+"',"+
                            "Salary=" +employee.getSalary()+
                            " where EmployeeId = "+employee.getId()
            );
            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteEmployee(int id , int userDeleteChoice) throws SQLException {
        try {
            if ( userDeleteChoice == 1) {
                Connection connection = ConnectionPort.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Employees WHERE EmployeeId = ?"
                );
                statement.setInt(1, id);
                statement.execute();
                return true;
            } else {
                return false;
            }
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
    }
}