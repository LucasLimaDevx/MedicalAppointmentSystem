package com.lucadevx.MedicalAppointmentSystem.dto;

import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;

public record AppointmentDTO(
		String appointmentDateTime, 
		String status, 
		Patient Patient, 
		Department department) {

}
