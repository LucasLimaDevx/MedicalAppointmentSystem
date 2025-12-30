package com.lucadevx.MedicalAppointmentSystem.dto.response;

public record DoctorResponseDTO(
	Long id,
	String firstName, 
	String lastName, 
	String phone, 
	String email, 
	String crm, 
	String speciality) {

}
