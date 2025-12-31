package com.lucadevx.MedicalAppointmentSystem.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PatientDTO(
		Long id, 
		String firstName, 
		String lastName, 
		String phone, 
		String email,
		@JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate) {

}
