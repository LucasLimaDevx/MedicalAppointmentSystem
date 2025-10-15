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

import com.lucadevx.MedicalAppointmentSystem.dto.DepartmentDTO;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.services.DepartmentService;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DepartmentDTO create(@RequestBody Department department) {
	
		return services.parseToDTO(services.create(department));
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DepartmentDTO findById(@PathVariable Long id) {
		Department department = services.findById(id);
		return services.parseToDTO(department);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DepartmentDTO> findAll(){
		List<Department> departments = services.findAll();
		List<DepartmentDTO> departmentsDTO = departments.stream()
				.map(department -> services.parseToDTO(department)).collect(Collectors.toList());
		
		return departmentsDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DepartmentDTO update(@RequestBody Department department) {
	
		return services.parseToDTO(services.update(department));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		services.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
