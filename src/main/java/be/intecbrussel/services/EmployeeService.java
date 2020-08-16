package be.intecbrussel.services;

import be.intecbrussel.data.EmployeeDAO;
import be.intecbrussel.model.Employee;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> getAllEmployees() throws SQLException {
       List<Employee> employees = employeeDAO.getAllEmployees();
        return employees;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getEmployeesByName(String name) throws SQLException {
        return employeeDAO.getEmployeesByName(name);
    }

    public List<Employee> getEmployeesByBirthDate() throws SQLException {
       return employeeDAO.getEmployeesByBirthDate();
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        return employeeDAO.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id , int userDeleteChoice) throws SQLException {
        return employeeDAO.deleteEmployee(id , userDeleteChoice);
    }
}