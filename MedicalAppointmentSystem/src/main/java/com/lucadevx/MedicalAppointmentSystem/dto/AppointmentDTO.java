package com.lucadevx.MedicalAppointmentSystem.dto;

import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;

public record AppointmentDTO(Long id,
		String appointmentDateTime, 
		String status, 
		Patient patient, 
		Doctor doctor) {

}
