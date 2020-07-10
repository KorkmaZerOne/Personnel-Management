package be.intecbrussel.services;

import be.intecbrussel.data.WorkDoneDAO;
import be.intecbrussel.model.Employee;
import be.intecbrussel.model.WorkDone;

import java.sql.SQLException;
import java.util.List;

public class WorkDoneService {
    private WorkDoneDAO workDoneDAO = new WorkDoneDAO();

    public List<WorkDone> getAllWorkDone() throws SQLException {
        List<WorkDone> workDone = workDoneDAO.getAllWorkDone();
        return workDone;
    }

    public List<WorkDone> getWorkDoneByEmployee(int employeeId, int projectId) throws SQLException {
        List<WorkDone> workDone = workDoneDAO.getWorkDoneByEmployee(employeeId, projectId);
        return workDone;
    }

    public WorkDone getWorkDoneByProject(int employeeId, int projectId) throws SQLException {
        WorkDone workDone = workDoneDAO.getWorkDoneByProject(employeeId, projectId);
        return workDone;
    }

    public boolean addWorkDone (WorkDone workDone){
            return workDoneDAO.addWorkDone(workDone);
        }

    public boolean updateWorkDone (WorkDone workDone) throws SQLException {
            return workDoneDAO.updateWorkDone(workDone);
        }

    public boolean deleteWorkDone(int employeeId, int projectId) {
            return workDoneDAO.deleteWorkDone(employeeId , projectId);
        }
}
