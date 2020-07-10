package be.intecbrussel.model;

public class WorkDone {

    private int employeeId;
    private int projectId;
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setWarnings(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "WorkDone{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", date=" + date +
                ", workingHours=" + workingHours +
                ", warnings='" + remarks + '\'' +
                '}';
    }
}