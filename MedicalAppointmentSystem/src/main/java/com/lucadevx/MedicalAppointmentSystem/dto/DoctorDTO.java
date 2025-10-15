package com.lucadevx.MedicalAppointmentSystem.dto;

import java.util.Set;

import com.lucadevx.MedicalAppointmentSystem.model.Appointment;

public record DoctorDTO(
		Long id,
		String firstName, 
		String lastName, 
		String phone, 
		String email, 
		String crm, 
		String speciality,
		Set<Appointment> appointments
		) {

}
