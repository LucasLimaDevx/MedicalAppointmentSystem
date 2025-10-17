
package com.lucadevx.MedicalAppointmentSystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucadevx.MedicalAppointmentSystem.model.Department;

public record DoctorDTO(
		Long id,
		String firstName, 
		String lastName, 
		String phone, 
		String email, 
		String crm, 
		String speciality,
		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) Department department
		) {

}
