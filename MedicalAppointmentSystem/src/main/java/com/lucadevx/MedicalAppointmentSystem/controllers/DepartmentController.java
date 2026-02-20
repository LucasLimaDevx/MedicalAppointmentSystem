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

import com.lucadevx.MedicalAppointmentSystem.controllers.docs.DepartmentControllerDocs;
import com.lucadevx.MedicalAppointmentSystem.dto.request.DepartmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DepartmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/department")
@Tag(name = "Department", description = "Endpoints for managing Department")
public class DepartmentController implements DepartmentControllerDocs {
	
	@Autowired
	private DepartmentService services;
	private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class.getName());
	
	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> create(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
		logger.info("Initialize create controller method.");
		
		DepartmentResponseDTO departmentResponseDTO = services.create(departmentRequestDTO);
		
		logger.info("Finishing create controller method.");
		return new ResponseEntity<>(departmentResponseDTO, HttpStatus.CREATED);
	}

	@Override
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable Long id) {
		logger.info("Initialize findById controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		DepartmentResponseDTO departmentResponseDTO = services.findById(id);;
		
		logger.info("Finishing findById controller method.");
		return ResponseEntity.ok(departmentResponseDTO);
		
	}
	
	@Override
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Find All Department.",
			description = "Finds All Department.",
			tags = {"Department"},
			responses = {
					@ApiResponse(
							
							responseCode = "200", 
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = DepartmentResponseDTO.class))
							)
							
					),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<List<DepartmentResponseDTO>> findAll(){
		logger.info("Initialize findAll controller method.");
		
		List<DepartmentResponseDTO> departmentResponseDTOs = services.findAll();
		
		logger.info("Finishing findAll controller method.");
		return ResponseEntity.ok(departmentResponseDTOs);
	}
	
	@Override
	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentResponseDTO> update(@RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable Long id) {
		logger.info("Initialize update controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		DepartmentResponseDTO departmentUpdateResponseDTO = services.update(departmentRequestDTO, id);
		
		logger.info("Finishing update controller method.");
		return ResponseEntity.ok(departmentUpdateResponseDTO);
	}
	
	@Override
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Delete a Department.",
			description = "Delete a specific Department by their id.",
			tags = {"Department"},
			responses = {
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("Initialize delete controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		services.delete(id);
		
		logger.info("Finishing delete controller method.");
		return ResponseEntity.noContent().build();
		
	}
	
}
