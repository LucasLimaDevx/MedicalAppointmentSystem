package com.lucadevx.MedicalAppointmentSystem.dto.response;

public record PatientResponseDTO(
		Long id, 
		String firstName, 
		String lastName, 
		String email,
		String phone, 
		String birthDate) {

}
