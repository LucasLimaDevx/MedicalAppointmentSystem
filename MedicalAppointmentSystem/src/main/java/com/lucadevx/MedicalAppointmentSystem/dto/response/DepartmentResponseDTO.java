package com.lucadevx.MedicalAppointmentSystem.dto.response;

import java.util.Set;

public record DepartmentResponseDTO(
		Long id, 
		String departmentName, 
		Set<AppointmentResponseDTO> appointments, 
	    Set<DoctorResponseDTO> doctors) {

}
