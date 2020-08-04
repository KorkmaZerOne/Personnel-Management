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
            employees.setDateOfBirth(rs.getString("BirthDate"));
            employees.setSalary(rs.getInt("Salary"));
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
            employees.setDateOfBirth(rs.getString("BirthDate"));
            employees.setSalary(rs.getInt("Salary"));
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
            PreparedStatement statement = connection.prepareStatement("select * from Employees E where DATEDIFF(SYSDATE(), E.BirthDate)%365<=7");
            ResultSet rs = statement.executeQuery();

            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setPhoneNumberICE(rs.getString("PhoneNumberICE"));
                employee.setDateOfBirth(rs.getString("BirthDate"));
                employee.setSalary(rs.getInt("Salary"));
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
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "update Employees set \n" +
                            "FirstName=\n'" +employee.getFirstName()+"',"+
                            "LastName= \n'" +employee.getLastName()+"',"+
                            "PhoneNumber=\n'" +employee.getPhoneNumber()+"',"+
                            "PhoneNumberICE=\n'" +employee.getPhoneNumberICE()+"',"+
                            "BirthDate=\n'" +employee.getDateOfBirth()+"',"+
                            "Salary=\n" +employee.getSalary()+
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

    public boolean deleteEmployee(int id) throws SQLException {
        try {
            Connection connection = ConnectionPort.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "delete from Employees where EmployeeId = ?"
            );
            statement.setInt(1, id);
            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table..." + e.getMessage());
            return false;
        }
        return true;
    }
}