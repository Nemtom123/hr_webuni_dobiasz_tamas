package hu.webuni.hr.tamasdobiasz.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;


@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int salary;

	private LocalDateTime entryDate;

	@ManyToOne
	private Company company;

	@ManyToOne
	private Position position;

	public Employee() {
	}

	public Employee(Long id, String name, int salary, LocalDateTime entryDate) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
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

