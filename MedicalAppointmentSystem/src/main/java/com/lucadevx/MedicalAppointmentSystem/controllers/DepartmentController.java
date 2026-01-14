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

import com.lucadevx.MedicalAppointmentSystem.dto.request.DepartmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DepartmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.DepartmentService;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService services;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> create(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
		DepartmentResponseDTO departmentResponseDTO = services.create(departmentRequestDTO);
		return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
	}

	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		DepartmentResponseDTO departmentResponseDTO = services.findById(id);;
		
		return ResponseEntity.ok(departmentResponseDTO);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartmentResponseDTO>> findAll(){
		
		List<DepartmentResponseDTO> departmentResponseDTOs = services.findAll();
		return ResponseEntity.ok(departmentResponseDTOs);
	}
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> update(@RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable Long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		DepartmentResponseDTO departmentUpdateResponseDTO = services.update(departmentRequestDTO, id);
		return ResponseEntity.ok(departmentUpdateResponseDTO);
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
