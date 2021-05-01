package hu.webuni.hr.tamasdobiasz.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Entity
public class Employee {

	public String dates;


	@Id
	@GeneratedValue
	private Long employeeId;
	private String name;
	private String jobTitle;
	private int salary;
	private LocalDateTime dateOfStartWork;

	@ManyToOne
	private Company company;

	@ManyToOne
	private Position position;

	public Employee() {
	}


	public Employee(Long employeeId, String name,String jobTitle, int salary, LocalDateTime dateOfStartWork) {
		this.employeeId = employeeId;
		this.name = name;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.dateOfStartWork = dateOfStartWork;
	}

	public Employee(String name){
		this.name = name;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDateTime getDateOfStartWork() {
		return dateOfStartWork;
	}

	public void setDateOfStartWork(LocalDateTime dateOfStartWork) {
		this.dateOfStartWork = dateOfStartWork;
	}

	@Override
	public String toString() {
		return "Employee [id=" + employeeId + ", name=" + name + ", title=" + jobTitle + ", salary=" + salary + ", dateOfStartWork="
				+ dateOfStartWork + "]";
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


}
