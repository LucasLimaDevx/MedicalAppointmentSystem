package com.lucadevx.MedicalAppointmentSystem.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AppointmentRequestDTO(
		@JsonFormat(pattern="dd/MM/yyyy HH:mm") LocalDateTime appointmentDateTime,
		String status, 
		Long patientId, 
		Long departmentId,
		Long doctorId) {

}
