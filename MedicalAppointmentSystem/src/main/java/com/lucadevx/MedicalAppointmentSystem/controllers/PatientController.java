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
	private PatientService patientServices;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO create(@RequestBody PatientDTO patientDTO) {
		Patient patient = patientServices.parseToPatient(patientDTO);
		
		
		return patientServices.parseToDTO(patientServices.create(patient));
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		Patient patient = patientServices.findById(id);
		
		return patientServices.parseToDTO(patient);
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDTO> findAll(){
		List<Patient> patients = patientServices.findAll();
		
		List<PatientDTO> patientsDTO = patients.stream()
				.map(patient -> patientServices.parseToDTO(patient))
				.collect(Collectors.toList());
		
		return patientsDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PatientDTO update(@RequestBody PatientDTO patientDTO){
	
		if(patientDTO.id() == null || patientDTO.id() <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		Patient patient = patientServices.parseToPatient(patientDTO);
		
		
		return patientServices.parseToDTO(patientServices.update(patient));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		patientServices.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
