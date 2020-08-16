package be.intecbrussel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WorkDone {

    private int employeeId;
    private int projectId;
    private LocalDate date;
    private int workingHours;
    private String remarks;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWorkingHours() {
        /*Project project = new Project();
        WorkDone workDone = new WorkDone();
        int workingHours = (int) (ChronoUnit.DAYS.between(workDone.getDate() , project.getEndDate())/30 * 22 * 8);
        */return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "WorkDone{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", date=" + date +
                ", workingHours=" + workingHours +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}