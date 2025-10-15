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

import com.lucadevx.MedicalAppointmentSystem.dto.AppointmentDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.services.AppointmentService;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentDTO create(@RequestBody Appointment appointment) {
		
		return services.parseToDTO(services.create(appointment));
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentDTO findById(@PathVariable Long id) {
		Appointment appointment = services.findById(id);
		AppointmentDTO appointmentDTO = services.parseToDTO(appointment);
		
		return appointmentDTO;
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AppointmentDTO> findAll(){
		List<Appointment> appointments = services.findAll();
		
		List<AppointmentDTO> appointmentsDTO = appointments.stream()
				.map(appointment -> services.parseToDTO(appointment))
				.collect(Collectors.toList());
		
		return appointmentsDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppointmentDTO update(@RequestBody Appointment appointment) {
		
		return services.parseToDTO(services.update(appointment));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
