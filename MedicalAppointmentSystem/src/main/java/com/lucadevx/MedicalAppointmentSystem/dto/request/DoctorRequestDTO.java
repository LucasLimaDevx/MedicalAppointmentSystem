package com.lucadevx.MedicalAppointmentSystem.dto.request;

import com.lucadevx.MedicalAppointmentSystem.model.Department;

public record DoctorRequestDTO(Long id,
		String firstName, 
		String lastName, 
		String phone, 
		String email, 
		String crm, 
		String speciality,
		Department department) {

}
