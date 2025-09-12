package com.lucadevx.MedicalAppointmentSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.services.PatientService;


@RestController
@RequestMapping("api/patient")
public class PatientController {
	
	@Autowired
	private PatientService services;
	
	@PostMapping
	public Patient create(@RequestBody Patient patient) {
		
		return services.create(patient);
	}
	
	@GetMapping
	public Patient findById(@RequestBody Patient patient) {
		
		return services.findById(patient.getId());
		
	}
	
	
}
