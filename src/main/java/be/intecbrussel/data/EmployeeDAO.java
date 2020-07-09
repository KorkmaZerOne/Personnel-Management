package be.intecbrussel.data;

import be.intecbrussel.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() throws SQLException {
    // 1 create connection to db
        Connection conn= ConnectionPort.getConnection();
    // 2 send query
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Employees");
    // 3 parse query result
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
    // return result
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
        // return result
        return employees;
    }

    public List<Employee> getEmployeesByName(String firstName , String lastName) throws SQLException {
        Connection connection = ConnectionPort.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees WHERE FirstName LIKE ? OR LastName LIKE ?");
        statement.setString(1, firstName);
        statement.setString(2, lastName);

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
        return result;
    }

    public List<Employee> getEmployeesByBirthDate() throws SQLException {
        Connection connection = ConnectionPort.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "select * from Employees E where DATEDIFF(SYSDATE(), E.BirthDate)%365<=7");

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
        return result;
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        try {
        Connection connection = ConnectionPort.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Employees (\n" +
                        "EmployeeId,\n" +
                        "FirstName,\n" +
                        "LastName,\n" +
                        "PhoneNumber,\n" +
                        "PhoneNumberICE,\n" +
                        "BirthDate,\n" +
                        "Salary) \n" +
                        "VALUES (\n" +
                        employee.getId()+",\n" +
                        "'"+employee.getFirstName()+"',\n" +
                        "'"+employee.getLastName()+"',\n" +
                        "'"+employee.getPhoneNumber()+"',\n" +
                        "'"+employee.getPhoneNumberICE()+"',\n" +
                        "'"+employee.getDateOfBirth()+"',\n" +
                        employee.getSalary()+")"
                );

            statement.execute();
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation" + e.getMessage());
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
            System.out.println("An error has occurred on Table Creation" + e.getMessage());
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
            System.out.println("An error has occurred on Table Creation" + e.getMessage());
            return false;
        }
        return true;
    }
}