package be.intecbrussel.services;

import be.intecbrussel.data.ProjectDAO;
import be.intecbrussel.model.Employee;
import be.intecbrussel.model.Project;
import be.intecbrussel.model.WorkDone;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private ProjectDAO projectDAO = new ProjectDAO();

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = projectDAO.getAllProjects();
        return projects;
    }

    public Project getProjectById(int id) throws SQLException {
        return projectDAO.getProjectById(id);
    }

    public List<Project> getProjectsByStartDate() throws SQLException {
        return projectDAO.getProjectsByStartDate();
    }

    public List<Project> getAllWorkDoneByProfitability() throws SQLException {
        return projectDAO.getAllWorkDoneByProfitability();
    }

    public boolean addProject(Project project) throws SQLException {
        return projectDAO.addProject(project);
    }

    public boolean deleteProject(int id, int userDeleteChoice) throws SQLException {
        return projectDAO.deleteProject(id, userDeleteChoice);
    }
}
