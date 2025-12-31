package com.lucadevx.MedicalAppointmentSystem.dto.response;

public record AppointmentResponseDTO(
		Long id,
		String appointmentDateTime,
		String status, 
		PatientResponseDTO patient, 
		DoctorResponseDTO doctor) {

}
