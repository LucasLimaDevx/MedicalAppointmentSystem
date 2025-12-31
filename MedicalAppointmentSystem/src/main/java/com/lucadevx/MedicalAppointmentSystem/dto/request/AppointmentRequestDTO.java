package com.lucadevx.MedicalAppointmentSystem.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucadevx.MedicalAppointmentSystem.dto.DepartmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.DoctorDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;

public record AppointmentRequestDTO(
		@JsonFormat(pattern="dd/MM/yyyy HH:mm") LocalDateTime appointmentDateTime,
		String status, 
		PatientDTO patient, 
		DepartmentDTO department,
		DoctorDTO doctor) {

}
