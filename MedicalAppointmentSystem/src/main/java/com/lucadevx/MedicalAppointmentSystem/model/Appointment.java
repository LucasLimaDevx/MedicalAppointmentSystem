package com.lucadevx.MedicalAppointmentSystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_APPOINTMENT")
public class Appointment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "appointment_date_time", nullable = true, length = 100)
	private String appointmentDateTime;
	
	@Column(name = "status_appointment", nullable = true)
	private String status;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "patient_id")
	@ManyToOne
	private Patient patient;
	
	@JsonProperty(access = Access.READ_WRITE)
	@JoinColumn(name = "department_id")
	@ManyToOne
	private Department department;
	
	public Appointment() {
	
	}
	
	public Appointment(String appointmentDateTime, String status) {
		
		
		this.appointmentDateTime = appointmentDateTime;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}


	public String getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(String appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	
}
