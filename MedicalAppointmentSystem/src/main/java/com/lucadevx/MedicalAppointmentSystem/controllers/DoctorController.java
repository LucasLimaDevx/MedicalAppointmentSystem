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

import com.lucadevx.MedicalAppointmentSystem.dto.DoctorDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.services.DoctorService;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDTO create(@RequestBody DoctorDTO doctorDTO) {
		Doctor doctor = services.parseToDoctor(doctorDTO);
		
		return services.parseToDTO(services.create(doctor));
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDTO findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		Doctor doctor = services.findById(id);
		
		return services.parseToDTO(doctor);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DoctorDTO> findAll(){
		List<Doctor> doctors = services.findAll();
		List<DoctorDTO> doctorsDTO = doctors.stream().map(doctor -> services.parseToDTO(doctor)).collect(Collectors.toList());
		
		return doctorsDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorDTO update(@RequestBody DoctorDTO doctorDTO) {
		if(doctorDTO.id() == null || doctorDTO.id() <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		Doctor doctor = services.parseToDoctor(doctorDTO);
		
		return services.parseToDTO(services.update(doctor));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
