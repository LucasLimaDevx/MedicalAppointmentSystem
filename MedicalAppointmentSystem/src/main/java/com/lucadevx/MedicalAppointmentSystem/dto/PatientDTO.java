package com.lucadevx.MedicalAppointmentSystem.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;

public record PatientDTO(
		Long id, 
		String firstName, 
		String lastName, 
		String phone, 
		String email,
		@JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
		@JsonIgnore Set<Appointment> appointments) {

}
