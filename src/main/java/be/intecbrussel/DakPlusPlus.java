package be.intecbrussel;

import be.intecbrussel.data.TableFactory;
import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;
import be.intecbrussel.model.WorkDone;
import be.intecbrussel.services.EmployeeService;
import be.intecbrussel.services.ProjectService;
import be.intecbrussel.services.WorkDoneService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DakPlusPlus {
    public static void main(String[] args) {

        TableFactory table = new TableFactory();
        table.createEmployeeTable();
        table.createProjectsTable();
        table.createWorkDoneTable();

        int mainChoice;
        int subChoice = -1;

        do {
            showMenu();
            mainChoice = requestInput(0, 3);

            if (mainChoice != 0) {
                showSubMenu(mainChoice);
                subChoice = requestInput(0, 7);

                userChoice(mainChoice, subChoice);
            }
        } while (mainChoice != 0 && subChoice != 0);
    }

    private static void userChoice(int mainChoice, int subChoice) {
        if (mainChoice == 1) {
            EmployeeService employeeService = new EmployeeService();
            Scanner scanner = new Scanner(System.in);
            if (subChoice == 1) {
                List<Employee> employees = null;
                try {
                    employees = employeeService.getAllEmployees();
                    employees.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 2) {
                List<Employee> employees = null;
                try {
                    System.out.println("Enter employee name or last name for checking");
                    String name = scanner.nextLine();
                    employees = employeeService.getEmployeesByName(name);
                    employees.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 3) {
                List<Employee> employees = null;
                try {
                    employees = employeeService.getEmployeesByBirthDate();
                    employees.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 4) {
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
                    System.out.println(result ? "Success" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 5) {
                try {
                    System.out.println("Write the employee ID which is you want to update");
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
                    System.out.println(result ? "Success" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 6) {
                try {
                    System.out.println("Write the employee ID which is you want to delete");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean result = employeeService.deleteEmployee(id);
                    System.out.println(result ? "Deleted" : "Error");
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 7){
                showMenu();
            }
        }
        if (mainChoice == 2) {
            ProjectService projectService = new ProjectService();
            Scanner scanner = new Scanner(System.in);
            if (subChoice == 1) {
                List<Project> projects = null;
                try {
                    projects = projectService.getAllProjects();
                    projects.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 2) {
                List<Project> projects = null;
                try {
                    System.out.println("Enter today's date: ");
                    String date = scanner.nextLine();
                    projects = projectService.getProjectsByStartDate(date);
                    projects.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 3) {
                try {
                    Project project = new Project();
                    System.out.println("ID: ");
                    project.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Start Date (yyyy.mm.dd): ");
                    String startDate = scanner.nextLine();
                    project.setStartDate(startDate);
                    System.out.println("explanation: ");
                    project.setExplanation(scanner.nextLine());
                    System.out.println("Price: ");
                    project.setPrice(scanner.nextInt());
                    System.out.println("End Date (yyyy.mm.dd): ");
                    String endDate = scanner.nextLine();
                    project.setEndDate(endDate);
                    boolean result = projectService.addProject(project);
                    System.out.println(result ? "Success" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 4) {
                try {
                    System.out.println("Write the project ID which is you want delete");
                    int id = Integer.parseInt(scanner.nextLine());
                    boolean result = projectService.deleteProject(id);
                    System.out.println(result ? "Deleted" : "Error");
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 5){
                showMenu();
            }
        }
        if (mainChoice == 3) {
            WorkDoneService workDoneService = new WorkDoneService();
            Scanner scanner = new Scanner(System.in);
            if (subChoice == 1) {
                List<WorkDone> workDone = null;
                try {
                    workDone = workDoneService.getAllWorkDone();
                    workDone.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println(" Problems with db..." + ignored.getMessage());
                }
            } else if (subChoice == 2) {
                List<WorkDone> workDone = null;
                try {
                    System.out.println("Enter employee ID and project ID for checking");
                    System.out.println("Employee ID: ");
                    int employeeId = scanner.nextInt();
                    System.out.println("Project ID: ");
                    int projectId = scanner.nextInt();
                    workDone = workDoneService.getWorkDoneByEmployee(employeeId , projectId);
                    workDone.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println(" Problems with db..." + ignored.getMessage());
                }
            } else if (subChoice == 3) {
                WorkDone workDone = new WorkDone();
                System.out.println("Enter employee ID: ");
                workDone.setProjectId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter project ID: ");
                workDone.setProjectId(Integer.parseInt(scanner.nextLine()));

                boolean result = workDoneService.addWorkDone(workDone);
                System.out.println(result ? "Success" : "Error");

            } else if (subChoice == 4) {
                try {
                    System.out.println("Enter employee ID: ");
                    int employeeId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter project ID: ");
                    int projectId = Integer.parseInt(scanner.nextLine());
                    WorkDone workDone = workDoneService.getWorkDoneByProject(employeeId , projectId);
                    System.out.println("Date (yyyy.mm.dd): ");
                    workDone.setDate(scanner.nextLine());
                    System.out.println("HoursWorked: ");
                    workDone.setWorkingHours(scanner.nextInt());
                    System.out.println("Remarks: ");
                    workDone.setWarnings(scanner.nextLine());
                    boolean result = workDoneService.updateWorkDone(workDone);
                    System.out.println(result ? "Success" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            }else if (subChoice == 5) {
                System.out.println("Enter employee ID: ");
                int employeeId = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter project ID: ");
                int projectId = Integer.parseInt(scanner.nextLine());
                boolean result = workDoneService.deleteWorkDone(employeeId ,projectId);
                System.out.println(result ? "Deleted" : "Error");
            } else if (subChoice == 6) {
                showMenu();
            }
        }
    }
                private static void showMenu () {
                    System.out.println("---MAIN MENU--");
                    System.out.println("0. Exit");
                    System.out.println("1. Employees");
                    System.out.println("2. Projects");
                    System.out.println("3. WorkDone");
                }

                private static void showSubMenu ( int choice){

                    if (choice == 1) {
                        System.out.println("---SUB MENU--");
                        System.out.println("0. Exit");
                        System.out.println("1. Show all employees");
                        System.out.println("2. Show all employees filtered by last name");
                        System.out.println("3. Show employees by birth date");
                        System.out.println("4. Create a new employee?");
                        System.out.println("5. Edit a employee?");
                        System.out.println("6. Delete a employee?");
                        System.out.println("7. Main menu");
                    }
                    if (choice == 2) {
                        System.out.println("---SUB MENU--");
                        System.out.println("0. Exit");
                        System.out.println("1. Show ongoing projects");
                        System.out.println("2. Show projects starting today");
                        System.out.println("3. Create a new project");
                        System.out.println("4. Delete a project");
                        System.out.println("5. Main menu");
                    }
                    if (choice == 3) {
                        System.out.println("---SUB MENU--");
                        System.out.println("0. Exit");
                        System.out.println("1. Show all work list");
                        System.out.println("2. Show work list filtered by employee");
                        System.out.println("3. Create a new work list");
                        System.out.println("4. Edit a work list");
                        System.out.println("5. Delete a work list");
                        System.out.println("6. Main menu");
                    }
                }

                private static int requestInput ( int lower, int upper){
                    Scanner scanner = new Scanner(System.in);
                    int input = -1;
                    do {
                        try {
                            System.out.println("<Select a menu>");
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