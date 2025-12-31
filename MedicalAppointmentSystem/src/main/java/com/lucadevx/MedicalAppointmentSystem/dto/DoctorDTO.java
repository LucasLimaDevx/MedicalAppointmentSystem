
package com.lucadevx.MedicalAppointmentSystem.dto;

public record DoctorDTO(
		Long id,
		String firstName, 
		String lastName, 
		String phone, 
		String email, 
		String crm, 
		String speciality,
		DepartmentDTO department
		) {

}
