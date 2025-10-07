package com.lucadevx.MedicalAppointmentSystem.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_APPOINTMENT")
public class Appointment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "appointment_date_time", nullable = true, length = 10)
	private String appointmentDateTime;
	
	@Column(name = "status_appointment", nullable = true)
	private String status;
	
	
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
	
	
	
	
	
}
