package com.lucadevx.MedicalAppointmentSystem.dto;

public record PatientDTO(
		Long id,
		String firstName,
		String lastName,
		String phone,
		String email,
		String birthDate
		) {

}
