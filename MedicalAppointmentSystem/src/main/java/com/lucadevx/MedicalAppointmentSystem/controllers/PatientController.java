package com.lucadevx.MedicalAppointmentSystem.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.services.PatientService;


@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO create(@RequestBody PatientDTO patientDTO) {
		Patient patient = services.fromDTO(patientDTO);
		Patient patientSaved = services.create(patient);
		
		
		return new PatientDTO(
				patientSaved.getId(),
				patientSaved.getFirstName(),
				patientSaved.getLastName(), 
				patientSaved.getPhone(), 
				patientSaved.getEmail(), 
				patientSaved.getBirthDate());
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO findById(@PathVariable Long id) {
		
		Patient patient = services.findById(id);
		
		return new PatientDTO(patient.getId(),
				   patient.getFirstName(), 
				   patient.getLastName(), 
				   patient.getPhone(), 
				   patient.getEmail(), 
				   patient.getBirthDate());
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDTO> findAll(){
		List<Patient> patients = services.findAll();
		
		List<PatientDTO> patientsDTO = patients.stream().map(patient -> 
		new PatientDTO(patient.getId(),
					   patient.getFirstName(), 
					   patient.getLastName(), 
					   patient.getPhone(), 
					   patient.getEmail(), 
					   patient.getBirthDate()))
				.collect(Collectors.toList());
		
		return patientsDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO update(@RequestBody PatientDTO patientDTO) {
		Patient patient = services.fromDTO(patientDTO);
		patient.setId(patientDTO.id());
		
		Patient patientSaved = services.update(patient);
		
		return new PatientDTO(
				patientSaved.getId(),
				patientSaved.getFirstName(),
				patientSaved.getLastName(), 
				patientSaved.getPhone(), 
				patientSaved.getEmail(), 
				patientSaved.getBirthDate());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
