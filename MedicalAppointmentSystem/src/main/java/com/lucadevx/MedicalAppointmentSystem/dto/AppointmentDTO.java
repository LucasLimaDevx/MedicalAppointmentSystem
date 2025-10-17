package com.lucadevx.MedicalAppointmentSystem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;

public record AppointmentDTO(Long id, 
		@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime appointmentDateTime,
		String status, 
		PatientDTO patient, 
		@JsonProperty(access = Access.WRITE_ONLY) Department department,
		Doctor doctor) {

}
