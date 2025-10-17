package com.lucadevx.MedicalAppointmentSystem.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lucadevx.MedicalAppointmentSystem.model.enums.Status;

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
	
	
	@Column(name = "appointment_date_time", nullable = false, length = 100)
	private LocalDateTime appointmentDateTime;
	
	@Column(name = "status_appointment", nullable = false)
	private Integer status;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "patient_id")
	@ManyToOne
	private Patient patient;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "department_id")
	@ManyToOne
	private Department department;
	
	@JsonProperty(access = Access.READ_WRITE)
	@JoinColumn(name = "doctor_id")
	@ManyToOne
	private Doctor doctor;
	
	public Appointment() {
	
	}
	
	public Appointment(LocalDateTime appointmentDateTime, Status status, Patient Patient, Department department) {
		
		
		this.appointmentDateTime = appointmentDateTime;
		setStatus(status);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public Status getStatus() {
		return Status.fromCode(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCode();
	}

	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	
}
	
