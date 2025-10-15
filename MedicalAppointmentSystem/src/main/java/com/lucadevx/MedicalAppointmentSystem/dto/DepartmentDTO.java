package com.lucadevx.MedicalAppointmentSystem.dto;

import java.util.Set;

import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;

public record DepartmentDTO(
		Long id, 
		String departmentName, 
		Set<Appointment> appointments, 
		Set<Doctor> doctos) {

}
