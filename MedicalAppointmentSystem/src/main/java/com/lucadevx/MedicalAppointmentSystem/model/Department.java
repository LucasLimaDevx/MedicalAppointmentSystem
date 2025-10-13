package com.lucadevx.MedicalAppointmentSystem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_DEPARTMENT")
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "department_name", nullable = false, length = 50)
	private String departmentName;
	
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private Set<Appointment> appointments = new HashSet<>();
	
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
	private Set<Doctor> doctors = new HashSet<>();
	
	public Department() {
	}
	
	public Department(Long id, String departmentName) {
	
		this.id = id;
		this.departmentName = departmentName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	
	
	
	
	
	
}
