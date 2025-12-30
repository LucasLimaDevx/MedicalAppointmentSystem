package com.lucadevx.MedicalAppointmentSystem.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;

public record AppointmentRequestDTO(
		@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime appointmentDateTime,
		String status, 
		PatientDTO patient, 
		Department department,
		Doctor doctor) {

}
