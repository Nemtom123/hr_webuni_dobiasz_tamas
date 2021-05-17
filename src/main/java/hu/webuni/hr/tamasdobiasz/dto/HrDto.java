package hu.webuni.hr.tamasdobiasz.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class HrDto{
    private Long employeeId;
    @NotEmpty
    private String workName;
    @NotEmpty
    private String jobTitle;
    @Min(value = 1)
    private int salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfStartWork;

    private String companyName;

    public HrDto() {

    }

    public HrDto(@NotEmpty String jobTitle, double salary) {
		super();
		this.jobTitle = jobTitle;
		this.salary = (int)salary;
	}

	public HrDto(Long employeeId, String workName,String jobTitle, int salary, LocalDateTime dateOfStartWork) {
        this.employeeId = employeeId;
        this.workName = workName;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfStartWork = dateOfStartWork;
    }

    public HrDto(String workName){
        this.workName = workName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getWorkName() { return workName; }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @DateTimeFormat(pattern="dd-MMM-YYYY HH:mm:ss")
    public LocalDateTime getDateOfStartWork() {
        return dateOfStartWork;
    }

    public void setDateOfStartWork(LocalDateTime dateOfStartWork) {
        this.dateOfStartWork = dateOfStartWork;
    }

    @Override
    public String toString() {
        return "Employee [id=" + employeeId + ", workName=" + workName + ", title=" + jobTitle + ", salary=" + salary + ", dateOfStartWork="
                + getDateOfStartWork() + "]";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}
