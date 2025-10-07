package com.lucadevx.MedicalAppointmentSystem.controllers;


import java.util.List;

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

import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.services.AppointmentService;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment create(@RequestBody Appointment appointment) {
		
		return services.create(appointment);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment findById(@PathVariable Long id) {
		
		return services.findById(id);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> findAll(){
		return services.findAll();
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment update(@RequestBody Appointment appointment) {
		
		return services.update(appointment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
