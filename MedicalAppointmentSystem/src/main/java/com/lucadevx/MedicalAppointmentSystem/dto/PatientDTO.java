package com.lucadevx.MedicalAppointmentSystem.dto;

import java.util.Set;

import com.lucadevx.MedicalAppointmentSystem.model.Appointment;

public record PatientDTO(
		Long id,
		String firstName,
		String lastName,
		String phone,
		String email,
		String birthDate,
		Set<Appointment> appointments
		
		) {

}
