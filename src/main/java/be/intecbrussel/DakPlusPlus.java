package be.intecbrussel;

import be.intecbrussel.data.TableFactory;
import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;
import be.intecbrussel.model.WorkDone;
import be.intecbrussel.services.EmployeeService;
import be.intecbrussel.services.ProjectService;
import be.intecbrussel.services.WorkDoneService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DakPlusPlus {
    public static void main(String[] args) throws SQLException {

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

    private static void userChoice(int mainChoice, int subChoice) throws SQLException {
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
                    System.out.println("First Name (Obligatory): ");
                    String firstName;
                    do {
                        firstName = scanner.nextLine();
                        if (!firstName.isEmpty()) {
                            employee.setFirstName(firstName);
                        } else {
                            System.out.println("Please insert a name!");
                            System.out.println("First Name (Obligatory): ");
                        }
                    } while (firstName.isEmpty());

                    System.out.println("Last Name (Obligatory): ");
                    String lastName;
                    do {
                        lastName = scanner.nextLine();
                        if (!lastName.isEmpty()) {
                            employee.setLastName(lastName);
                        } else {
                            System.out.println("Please insert a name!");
                            System.out.println("Last Name (Obligatory): ");
                        }
                    } while (lastName.isEmpty());
                    System.out.println("Phone: ");
                    employee.setPhoneNumber(scanner.nextLine());
                    System.out.println("Phone ICE: ");
                    employee.setPhoneNumberICE(scanner.nextLine());
                    LocalDate localDate;
                    System.out.println("Date of Birth (yyyy-MM-dd): ");
                    do {
                        String birthDate = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        localDate = LocalDate.parse(birthDate, formatter);
                        if (hasAgeRequirement(localDate)) {
                            employee.setDateOfBirth(localDate);
                        } else {
                            System.out.println("Employing children under 18 is legally a crime!");
                            System.out.println("Date of Birth (yyyy-MM-dd): ");
                        }
                    } while (!hasAgeRequirement(localDate));

                    System.out.println("Salary: ");
                    double salary;
                    do {
                        salary = scanner.nextDouble();
                        if (hasWageRequirement(salary)) {
                            employee.setSalary(salary);
                        } else {
                            System.out.println("Please pay the employee his salary before his sweat has dried up!");
                            System.out.println("Salary: ");
                        }
                    } while (!hasWageRequirement(salary));
                    boolean result = employeeService.addEmployee(employee);
                    System.out.println(result ? "EMPLOYEE WAS ADDED" : "Error");
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 5) {
                List<Employee> employees = null;
                try {
                    employees = employeeService.getAllEmployees();
                    employees.forEach(b -> System.out.println(b.toString()));
                    System.out.println("Write the employee ID which is you want to update");
                    int id;
                    Employee employee;
                    do {
                        id = scanner.nextInt();
                        employee = employeeService.getEmployeeById(id);
                        if (id == employee.getId()) {
                            System.out.println("Employee who is you want to update");
                            System.out.println(employee.toString());
                            String firstName;
                            do {
                                firstName = scanner.nextLine();
                                if (!firstName.isEmpty()) {
                                    employee.setFirstName(firstName);
                                } else {
                                    System.out.println("Please insert a name!");
                                    System.out.println("First Name (Obligatory): ");
                                }
                            } while (firstName.isEmpty());

                            System.out.println("Last Name (Obligatory): ");
                            String lastName;
                            do {
                                lastName = scanner.nextLine();
                                if (!lastName.isEmpty()) {
                                    employee.setLastName(lastName);
                                } else {
                                    System.out.println("Please insert a name!");
                                    System.out.println("Last Name (Obligatory): ");
                                }
                            } while (lastName.isEmpty());
                            System.out.println("Phone: ");
                            employee.setPhoneNumber(scanner.nextLine());
                            System.out.println("Phone ICE: ");
                            employee.setPhoneNumberICE(scanner.nextLine());
                            LocalDate localDate;
                            System.out.println("Date of Birth (yyyy-MM-dd): ");
                            do {
                                String birthDate = scanner.nextLine();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                localDate = LocalDate.parse(birthDate, formatter);
                                if (hasAgeRequirement(localDate)) {
                                    employee.setDateOfBirth(localDate);
                                } else {
                                    System.out.println("Employing children under 18 is legally a crime!");
                                    System.out.println("Date of Birth (yyyy-MM-dd): ");
                                }
                            } while (!hasAgeRequirement(localDate));
                            System.out.println("Salary: ");
                            double salary;
                            do {
                                salary = scanner.nextDouble();
                                if (hasWageRequirement(salary)) {
                                    employee.setSalary(salary);
                                } else {
                                    System.out.println("Please pay the employee his salary before his sweat has dried up!");
                                    System.out.println("Salary: ");
                                }
                            } while (!hasWageRequirement(salary));
                        } else {
                            System.out.println("!!EmployeeId you have searched is not exist");
                            System.out.println("Write the employee ID which is you want to update");
                        }
                    } while (id != employee.getId());
                    boolean result = employeeService.updateEmployee(employee);
                    System.out.println(result ? "UPDATE IS SUCCESS" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 6) {
                try {
                    int id;
                    int userDeleteChoice = 0;
                    Employee employee;
                    System.out.println("Write the employee ID which is you want to delete");
                    do {
                        id = scanner.nextInt();
                        employee = employeeService.getEmployeeById(id);
                        if (id != employee.getId()) {
                            System.out.println("!!EmployeeId you have searched is not exist");
                            System.out.println("Write the employee ID which is you want to delete");
                        } else {
                            System.out.println("Employee who is you want to delete");
                            System.out.println(employee.toString());
                            deleteMenu();
                            userDeleteChoice = scanner.nextInt();
                        }
                    } while (id != employee.getId());
                    boolean result = employeeService.deleteEmployee(id, userDeleteChoice);
                    System.out.println(result ? "Deleted" : "Not Deleted");
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 7) {
                showMenu();
            }
        }
        if (mainChoice == 2) {
            ProjectService projectService = new ProjectService();
            Scanner scanner = new Scanner(System.in);
            if (subChoice == 1) {
                List<Project> projects;
                try {
                    projects = projectService.getAllProjects();
                    projects.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 2) {
                List<Project> projects;
                try {
                    projects = projectService.getProjectsByStartDate();
                    projects.forEach(b -> System.out.println(b.toString()));
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...");
                    ignored.printStackTrace();
                }
            } else if (subChoice == 3) {
                try {
                    Project project = new Project();
                    System.out.println("explanation:");
                    project.setExplanation(scanner.nextLine());
                    LocalDate localStartDate;
                    LocalDate localEndDate;
                    System.out.println("Start Date (Today- yyyy-MM-dd): ");
                    do {
                        String startDate = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        localStartDate = LocalDate.parse(startDate, formatter);
                        if (LocalDate.now().isEqual(localStartDate)) {
                            project.setStartDate(localStartDate);
                        } else {
                            System.out.println("The new project has to be started at current day");
                            System.out.println("Start Date (Today- yyyy-MM-dd): ");
                        }
                    } while (!LocalDate.now().isEqual(localStartDate));

                    System.out.println("Price:");
                    int price;
                    do {
                        price = scanner.nextInt();
                        if (price > 0) {
                            project.setPrice(price);
                        } else {
                            System.out.println("Avoid the astronomical penalties, please input the real value!");
                            System.out.println("Price: ");
                        }
                    } while (price <= 0);

                    do {
                        System.out.println("End Date ( yyyy-MM-dd): ");
                        String endDate = scanner.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        localEndDate = LocalDate.parse(endDate, formatter);
                        if (localEndDate.isAfter(localStartDate)) {
                            project.setEndDate(localEndDate);
                        } else {
                            System.out.println("The project end date has to be started after at start day");
                        }
                    } while (!localEndDate.isAfter(localStartDate));
                    boolean result = projectService.addProject(project);
                    System.out.println(result ? "THE PROJECT WAS ADDED" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 4) {
                List<Project> projects;
                try {
                    projects = projectService.getAllProjects();
                    projects.forEach(b -> System.out.println(b.toString()));
                    int id;
                    int userDeleteChoice = 0;
                    Project project;
                    System.out.println("Write the Project ID which is you want to delete");
                    do {
                        id = scanner.nextInt();
                        project = projectService.getProjectById(id);
                        if (id != project.getId()) {
                            System.out.println("!!ProjectId you have searched is not exist");
                            System.out.println("Write the Project ID which is you want to delete");
                        } else {
                            System.out.println("Project which is you want to delete");
                            System.out.println(project.toString());
                            deleteMenu();
                            userDeleteChoice = scanner.nextInt();
                        }
                    } while (id != project.getId());
                    boolean result = projectService.deleteProject(id, userDeleteChoice);
                    System.out.println(result ? "Deleted" : "Not Deleted");
                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 5) {
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
                List <WorkDone> workDone = null;
                try {
                    System.out.println("Enter PROJECT ID for checking");
                    System.out.println("Project ID: ");
                    int projectId = scanner.nextInt();
                    workDone = workDoneService.getWorkDoneByProjectId(projectId);
                    System.out.println(workDone.toString());
                } catch (SQLException ignored) {
                    System.out.println(" Problems with db..." + ignored.getMessage());
                }
            } else if (subChoice == 3) {
                WorkDone workDone = new WorkDone();
                EmployeeService employeeService = new EmployeeService();
                ProjectService projectService = new ProjectService();
                List<Project> projects;
                Project project;
                List <Employee> employees;
                Employee employee ;
                employees = employeeService.getAllEmployees();
                employees.forEach(b -> System.out.println(b.toString()));
                int employeeId;

                System.out.println("ENTER EMPLOYEE ID WHO IS YOU WANT TO ADD TO WORKDONE: ");
                do {
                    employeeId = scanner.nextInt();
                    employee = employeeService.getEmployeeById(employeeId);
                    if (employeeId != employee.getId()) {
                        System.out.println("!!EmployeeId you have searched is not exist");
                        System.out.println("Write the employee ID which is you want to add to WorkDone: ");
                    } else {
                        System.out.println("Employee who is you want to add to WorkDone: ");
                        workDone.setEmployeeId(employeeId);
                        System.out.println(employee.toString());
                    }
                } while (employeeId != employee.getId());

                int projectId;
                projects = projectService.getAllProjects();
                projects.forEach(b -> System.out.println(b.toString()));
                System.out.println("ENTER PROJECT ID WHICH IS YOU WANT TO ADD TO WORKDONE");
                do {
                    projectId = scanner.nextInt();
                    project = projectService.getProjectById(projectId);
                    if (projectId != project.getId()) {
                        System.out.println("!!ProjectId you have searched is not exist");
                        System.out.println("Write the Project ID which is you want to add WorkDone");
                    } else {
                        System.out.println("Project which is you want to add to WorkDone");
                        workDone.setProjectId(projectId);
                        System.out.println(project.toString());
                    }
                } while (projectId != project.getId());

                LocalDate localWorkDoneStartDate;
                System.out.println("WorkDone Start Date (yyyy-MM-dd): ");
                do {
                    String workDoneStartDate = scanner.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    localWorkDoneStartDate = LocalDate.parse(workDoneStartDate, formatter);
                    if (localWorkDoneStartDate.isAfter(project.getStartDate())) {
                        workDone.setDate(localWorkDoneStartDate);
                    } else {
                        System.out.println("The WorkDone start date has to be started after at project start day");
                    }
                } while (!localWorkDoneStartDate.isAfter(project.getStartDate()));
                int workingHours = (int) ((ChronoUnit.DAYS.between(localWorkDoneStartDate, project.getEndDate())) / 30.00 * 22 * 8);
                workDone.setWorkingHours(workingHours);
                System.out.println("HoursWorked: " + workDone.getWorkingHours());
                System.out.println("Enter remarks for WorkDone: ");
                workDone.setRemarks(scanner.next());
                boolean result = workDoneService.addWorkDone(workDone);
                System.out.println(result ? "THE WORKDONE WAS ADDED" : "Error");

            } else if (subChoice == 4) {
                List<WorkDone> workDones = null;
                try {
                    workDones = workDoneService.getAllWorkDone();
                    workDones.forEach(b -> System.out.println(b.toString()));

                    workDones = new ArrayList<>();
                    WorkDone workDone = new WorkDone();
                    EmployeeService employeeService = new EmployeeService();
                    ProjectService projectService = new ProjectService();
                    Project project = new Project();
                    Employee employee = new Employee();

                    int projectId;

                    System.out.println("Enter PROJECT ID in order to update WorkDone: ");
                    //Employee employee;
                    //System.out.println("Write the employee ID which is you want to delete");

                    do {
                        projectId = scanner.nextInt();
                        //workDoneService.getWorkDoneByProjectId(projectId);
                        project = projectService.getProjectById(projectId);
                      //  workDones = workDoneService.getWorkDoneByProjectId(projectId);
                       // System.out.println("WORKDONE LIST BASED ON PROJECT ID: ");
                      //  workDones.forEach(b -> System.out.println(b.toString()));
                      //  System.out.println("Enter EMPLOYEE ID for current WorkDone: ");
                       // employeeId = scanner.nextInt();
                      //  workDone = workDoneService.getWorkDoneByTwoId(projectId, employeeId);
                       // System.out.println("WORKDONE BASED ON PROJECT AND EMPLOYEE ID: ");
                      //  System.out.println(workDone);
                        if (projectId != project.getId()) {
                            System.out.println(project.getId());
                            System.out.println("!!ProjectId you have searched is not exist");
                            System.out.println("Enter the Project ID: ");
                        } else {
                            System.out.println("WORKDONE LIST BASED ON PROJECT ID: ");
                            workDones = workDoneService.getWorkDoneByProjectId(projectId);
                            workDones.forEach(b -> System.out.println(b.toString()));
                            System.out.println("Enter EMPLOYEE ID for current WorkDone: ");
                        }
                    } while (projectId != project.getId());

                    int employeeId;
                    do {
                        employeeId = scanner.nextInt();
                        projectId = project.getId();
                        workDone = workDoneService.getWorkDoneByTwoId(projectId , employeeId);
                        System.out.println(workDone);
                        if (employeeId != employee.getId()) {
                            System.out.println(employee.getId());
                            System.out.println("!!Employee ID you have searched is not exist");
                            System.out.println("Enter EMPLOYEE ID for current WorkDone: ");
                        } else {
                            System.out.println("WORKDONE LIST BASED ON PROJECT ID AND EMPLOYEE ID: ");
                            workDone = workDoneService.getWorkDoneByTwoId(projectId , employeeId);
                            System.out.println(workDone.toString());
                            System.out.println("Enter NEW PROJECT ID in order to update WorkDone: ");
                        }
                    } while (employeeId != workDone.getEmployeeId());

                    do {
                        projectId = scanner.nextInt();
                        project = projectService.getProjectById(projectId);
                        System.out.println(project.toString());
                        if (projectId != project.getId()) {
                            System.out.println("!!Project ID you have searched is not exist");
                            System.out.println("Write the Project ID");
                        } else {
                            System.out.println("Project which is you want to choice for WorkDone: ");
                            workDone.setProjectId(projectId);
                            System.out.println(project.toString());
                            System.out.println("Enter NEW EMPLOYEE ID in order to update WorkDone: ");
                        }
                    } while (projectId != project.getId());

                    do {
                        employeeId = scanner.nextInt();
                        employee = employeeService.getEmployeeById(employeeId);
                        if (employeeId != employee.getId()) {
                            System.out.println("!!EmployeeId you have searched is not exist");
                            System.out.println("Write the employee ID which is you want to add to WorkDone: ");
                        } else {
                            System.out.println("Employee who is you want to add to WorkDone: ");
                            workDone.setEmployeeId(employeeId);
                            System.out.println(employee.toString());
                            System.out.println("WorkDone Start Date (yyyy-MM-dd): ");
                        }
                    } while (employeeId != employee.getId());

                    LocalDate localWorkDoneStartDate;
                    String workDoneStartDate;
                    System.out.println("WorkDone Start Date (yyyy-MM-dd): ");
                    do {
                        workDoneStartDate = scanner.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        localWorkDoneStartDate = LocalDate.parse(workDoneStartDate, formatter);
                        if (localWorkDoneStartDate.isAfter(project.getStartDate())) {
                            workDone.setDate(localWorkDoneStartDate);
                        } else {
                            System.out.println("The WorkDone start date has to be started after at project start day");
                        }
                    } while (!localWorkDoneStartDate.isAfter(project.getStartDate()));
                    int workingHours = (int) ((ChronoUnit.DAYS.between(project.getEndDate(), localWorkDoneStartDate)) / 30.00 * 22 * 8);
                    workDone.setWorkingHours(workingHours);
                    System.out.println("HoursWorked: " + workDone.getWorkingHours());
                    System.out.println("Enter remarks for WorkDone: ");
                    workDone.setRemarks(scanner.next());

                    boolean result = workDoneService.updateWorkDone(workDone);
                    System.out.println(result ? "THE WORKDONE WAS UPDATED" : "Error");

                } catch (SQLException ignored) {
                    System.out.println("Problems with db...: " + ignored.getMessage());
                    ignored.printStackTrace();
                }
            } else if (subChoice == 5) {
                System.out.println("Enter employee ID: ");
                int employeeId = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter project ID: ");
                int projectId = Integer.parseInt(scanner.nextLine());
                boolean result = workDoneService.deleteWorkDone(employeeId, projectId);
                System.out.println(result ? "THE WORKDONE WAS DELETED" : "Error");
            } else if (subChoice == 6) {
                showMenu();
            }
        }

    }

    private static void showMenu() {
        System.out.println("---MAIN MENU--");
        System.out.println("0. Exit");
        System.out.println("1. Employees");
        System.out.println("2. Projects");
        System.out.println("3. WorkDone");
    }

    private static void showSubMenu(int choice) {

        if (choice == 1) {
            System.out.println("---SUB MENU--");
            System.out.println("0. Exit");
            System.out.println("1. Show all employees");
            System.out.println("2. Show all employees filtered by last name");
            System.out.println("3. show employees with upcoming birthday");
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

    private static int requestInput(int lower, int upper) {
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

    private static void deleteMenu() {
        System.out.println("Are you sure to delete?");
        System.out.println("0. NO");
        System.out.println("1. YES");
    }

    private static boolean hasWageRequirement(double salary) {
        if (salary > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean hasAgeRequirement(LocalDate localDate) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(localDate, LocalDate.now());
        if (noOfDaysBetween > 6570) {
            return true;
        } else {
            return false;
        }

    }
}