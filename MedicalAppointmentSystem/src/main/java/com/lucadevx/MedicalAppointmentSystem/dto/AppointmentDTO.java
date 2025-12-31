package com.lucadevx.MedicalAppointmentSystem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AppointmentDTO(
		Long id, 
		@JsonFormat(pattern="dd/MM/yyyy HH:mm") LocalDateTime appointmentDateTime,
		String status, 
		PatientDTO patient, 
		DepartmentDTO department,
		DoctorDTO doctor) {

}
