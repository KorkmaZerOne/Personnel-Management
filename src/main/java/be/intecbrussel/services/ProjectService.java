package be.intecbrussel.services;

import be.intecbrussel.data.ProjectDAO;
import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private ProjectDAO projectDAO = new ProjectDAO();

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = projectDAO.getAllProjects();
        return projects;
    }
    public List<Project> getProjectsByStartDate(String date) throws SQLException {
        System.out.println("The projects which is start today");
        return projectDAO.getProjectsByStartDate(date);
    }
    public boolean addProject(Project project) throws SQLException {
        return projectDAO.addProject(project);
    }

    public boolean deleteProject(int id) throws SQLException {
        return projectDAO.deleteProject(id);
    }
}
