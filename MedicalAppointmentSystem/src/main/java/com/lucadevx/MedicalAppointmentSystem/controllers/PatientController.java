package com.lucadevx.MedicalAppointmentSystem.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.services.PatientService;


@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient create(@RequestBody Patient patient) {
		
		return services.create(patient);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient findById(@PathVariable Long id) {
		
		return services.findById(id);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Patient> findAll(){
		return services.findAll();
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient update(@RequestBody Patient patient) {
		
		return services.update(patient);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		services.delete(id);
	}
	
}
