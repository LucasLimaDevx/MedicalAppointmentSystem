package com.lucadevx.MedicalAppointmentSystem.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;

public record AppointmentResponseDTO(@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime appointmentDateTime,
		String status, 
		PatientResponseDTO patient, 
		Doctor doctor) {

}
