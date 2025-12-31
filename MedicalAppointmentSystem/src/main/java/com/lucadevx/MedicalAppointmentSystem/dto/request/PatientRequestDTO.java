package com.lucadevx.MedicalAppointmentSystem.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PatientRequestDTO(
		String firstName, 
		String lastName, 
		String phone, 
		String email,
		@JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate) {

}
