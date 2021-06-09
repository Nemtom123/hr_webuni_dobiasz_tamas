package hu.webuni.logistics.dobiasz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	private LocalDateTime plannedTime;

	public Milestone() {
	}

	public Milestone(long id, Address address, LocalDateTime plannedTime) {
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public Section getSection() {
		return getSection();
	}
}