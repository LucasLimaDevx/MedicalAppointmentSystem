package com.lucadevx.MedicalAppointmentSystem.dto.request;

public record DoctorRequestDTO(
		String firstName, 
		String lastName, 
		String phone, 
		String email, 
		String crm, 
		String speciality,
		Long departmentId) {}
