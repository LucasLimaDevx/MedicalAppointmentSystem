package com.lucadevx.MedicalAppointmentSystem.dto.response;

public record PatientResponseDTO(
		Long id, 
		String firstName, 
		String lastName, 
		String phone, 
		String email,
		String birthDate) {

}
