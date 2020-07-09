package be.intecbrussel;

import be.intecbrussel.data.TableFactory;
import be.intecbrussel.model.Employee;
import be.intecbrussel.services.EmployeeService;

import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DakPlusPlus {
    public static void main(String[] args) {

        TableFactory table = new TableFactory();
        table.createEmployeeTable();

        int mainChoice;
        int subChoice = -1;

        do {
            showMenu();
            mainChoice = requestInput(0, 3);

            if (mainChoice != 0) {
                showSubMenu(mainChoice);
                subChoice = requestInput(0, 6);

                userChoice(mainChoice , subChoice);
            }
        } while (mainChoice != 0 && subChoice != 0);
    }

    private static void userChoice(int mainChoice, int subChoice) {
        if (mainChoice == 1) {
            EmployeeService employeeService = new EmployeeService();
            Scanner scanner = new Scanner(System.in);
            if ( subChoice == 1) {
                //show all employees
                List<Employee> employees = null;
                try {
                    employees = employeeService.getAllEmployees();
                    employees.forEach(b-> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            }else if(subChoice==2){
                List<Employee> employees = null;
                try {
                    System.out.println("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Last Name: ");
                    String lastName =scanner.nextLine();
                    employees = employeeService.getEmployeesByName(firstName,lastName);
                    employees.forEach(b-> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            }else if(subChoice==3){
                List<Employee> employees = null;
                try {
                    employees = employeeService.getEmployeesByBirthDate();
                    employees.forEach(b-> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            }
            else if(subChoice==4){
                try {
                    Employee employee = new Employee();
                    System.out.println("ID: ");
                    employee.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("First Name: ");
                    employee.setFirstName(scanner.nextLine());
                    System.out.println("Last Name: ");
                    employee.setLastName(scanner.nextLine());
                    System.out.println("Phone: ");
                    employee.setPhoneNumber(scanner.nextLine());
                    System.out.println("Phone ICE: ");
                    employee.setPhoneNumberICE(scanner.nextLine());
                    System.out.println("Date of Birth (yyyy.mm.dd): ");
                    String date = scanner.nextLine();
                    employee.setDateOfBirth(date);
                    System.out.println("Salary: ");
                    employee.setSalary(scanner.nextInt());
                    boolean result = employeeService.addEmployee(employee);
                    System.out.println(result?"Success":"Error");

                }catch (SQLException ignored) {
                    System.out.println("Problems with db...: "+ ignored.getMessage());
                    ignored.printStackTrace();
                }
            }else if(subChoice==5){
                try {
                    System.out.println("Write the ID");
                    int id = Integer.parseInt(scanner.nextLine());
                    Employee employee = employeeService.getEmployeeById(id);

                    System.out.println("First Name: ");
                    employee.setFirstName(scanner.nextLine());
                    System.out.println("Last Name: ");
                    employee.setLastName(scanner.nextLine());
                    System.out.println("Phone: ");
                    employee.setPhoneNumber(scanner.nextLine());
                    System.out.println("Phone ICE: ");
                    employee.setPhoneNumberICE(scanner.nextLine());
                    System.out.println("Date of Birth (yyyy.mm.dd): ");
                    String date = scanner.nextLine();
                    employee.setDateOfBirth(date);
                    System.out.println("Salary: ");
                    employee.setSalary(scanner.nextInt());
                    boolean result = employeeService.updateEmployee(employee);
                    System.out.println(result?"Success":"Error");

                }catch (SQLException ignored) {
                System.out.println("Problems with db...: "+ ignored.getMessage());
                ignored.printStackTrace();
            }
            }else if(subChoice==6){
                try {
                System.out.println("Write the ID");
                int id= Integer.parseInt(scanner.nextLine());
                boolean result = employeeService.deleteEmployee(id);
                System.out.println(result?"Deleted":"Error");
                }catch (SQLException ignored) {
                    System.out.println("Problems with db...: "+ ignored.getMessage());
                    ignored.printStackTrace();
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Employees");
        System.out.println("2. Projects");
        System.out.println("3. WorkDone");
    }

    private static void showSubMenu(int choice) {

        if (choice == 1) {
            System.out.println("0. Exit");
            System.out.println("1. Show all employees");
            System.out.println("2. Show all employees filtered by last name");
            System.out.println("3. Show employees by birth date");
            System.out.println("4. Create a new employee?");
            System.out.println("5. Edit a employee?");
            System.out.println("6. Delete a employee?");
        }
        if (choice == 2) {
            System.out.println("0. Exit");
            System.out.println("1. Show all projects");
            System.out.println("2. Show all projects filtered by profitability");
            System.out.println("3. Show all projects filtered by start date");
            System.out.println("4. Show all projects filtered by end date");
            System.out.println("5. Create a new project?");
        }
        if (choice == 3) {
            System.out.println("0. Exit");
            System.out.println("1. Show all work list");
            System.out.println("2. Show all work list filtered by employee");
            System.out.println("3. Create a new work list");
            System.out.println("4. Edit a work list");
            System.out.println("5. Delete a work list");
        }
    }

    private static int requestInput(int lower, int upper) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        do {
            try {
                System.out.println("Make a choice");
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                input = -1;
            }
            scanner.nextLine();
            if (input < lower || input > upper)
                System.out.println("Please input a valid number");
        } while (input < lower || input > upper);
        return input;
    }
}