package com.lucadevx.MedicalAppointmentSystem.controllers;


import java.util.List;

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

import com.lucadevx.MedicalAppointmentSystem.dto.request.AppointmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.AppointmentService;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO appointmenRequestDTO) {
		AppointmentResponseDTO appointmentResponseDTO = services.create(appointmenRequestDTO);
		return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
				
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
	
		AppointmentResponseDTO appointmentResponseDTO = services.findById(id);
		return ResponseEntity.ok(appointmentResponseDTO);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentResponseDTO>> findAll(){
		
		List<AppointmentResponseDTO> appointmentResponseDTOs = services.findAll();
		return ResponseEntity.ok(appointmentResponseDTOs);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> update(@RequestBody AppointmentRequestDTO appointmentRequestDTO, @PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		AppointmentResponseDTO appointmentUpdateResponseDTO = services.update(appointmentRequestDTO, id);
		return ResponseEntity.ok(appointmentUpdateResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
