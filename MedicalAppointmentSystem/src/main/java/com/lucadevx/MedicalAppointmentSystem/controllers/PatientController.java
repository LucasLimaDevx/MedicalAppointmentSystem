package com.lucadevx.MedicalAppointmentSystem.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.lucadevx.MedicalAppointmentSystem.dto.request.PatientRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.PatientService;



@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientServices;
	private final static Logger logger = LoggerFactory.getLogger(PatientController.class.getName());
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO patientRequestDTO) {
		logger.info("Initialize create controller method.");
		
		PatientResponseDTO patientResponseDTO = patientServices.create(patientRequestDTO);
		
		logger.info("Finishing create controller method.");
		return new ResponseEntity<>(patientResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponseDTO> findById(@PathVariable Long id) {
		logger.info("Initialize findById controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		PatientResponseDTO patientResponseDTO = patientServices.findById(id);
		
		logger.info("Finishing findById controller method.");
		return ResponseEntity.ok(patientResponseDTO);
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PatientResponseDTO>> findAll(){
		logger.info("Initialize findAll controller method.");
		
		List<PatientResponseDTO> patientDTOs = patientServices.findAll();
		
		logger.info("Finishing findAll controller method.");
		return ResponseEntity.ok(patientDTOs);
	
	}
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponseDTO> update(@RequestBody PatientRequestDTO patientRequestDTO, @PathVariable Long id){
		logger.info("Initialize update controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		PatientResponseDTO patientUpdateResponseDTO = patientServices.update(patientRequestDTO, id);
		
		logger.info("Finishing update controller method.");
		return ResponseEntity.ok(patientUpdateResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("Initialize delete controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		patientServices.delete(id);
		
		logger.info("Finishing delete controller method.");
		return ResponseEntity.noContent().build();
		
	}
	
}
