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

import com.lucadevx.MedicalAppointmentSystem.dto.request.AppointmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/appointment")
@Tag(name = "Appointment", description = "Endpoints for managing Appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService services;
	private final static Logger logger = LoggerFactory.getLogger(AppointmentController.class.getName());
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Create a Appointment.",
			description = "To create a Appointment object, the patientId, departmentId and doctorId fields must be greater than 0."
					+ " The status field only accepts the values ​​AGENDADO, REALIZADO, CANCELADO, and ADIADO.",
			tags = {"Appointment"},
			responses = {
					@ApiResponse(
							responseCode = "201", 
							content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO appointmenRequestDTO) {
		logger.info("Initialize create controller method.");
		
		AppointmentResponseDTO appointmentResponseDTO = services.create(appointmenRequestDTO);
		
		logger.info("Finishing create controller method.");
		return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
				
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Finds a Appointment.",
			description = "Find a Appointment specific by Id.",
			tags = {"Appointment"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
		logger.info("Initialize findById controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
	
		AppointmentResponseDTO appointmentResponseDTO = services.findById(id);
		
		logger.info("Finishing findById controller method.");
		return ResponseEntity.ok(appointmentResponseDTO);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Find All Appointment.",
			description = "Finds All Appointment.",
			tags = {"Appointment"},
			responses = {
					@ApiResponse(
							
							responseCode = "200", 
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = AppointmentResponseDTO.class))
							)
							
					),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<List<AppointmentResponseDTO>> findAll(){
		logger.info("Initialize findAll controller method.");
		
		List<AppointmentResponseDTO> appointmentResponseDTOs = services.findAll();
		
		logger.info("Finishing findAll controller method.");
		return ResponseEntity.ok(appointmentResponseDTOs);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Update a Appointment.",
			description = "To update a Appointment id, object, the patientId, departmentId and doctorId fields must be greater than 0."
					+ " The status field only accepts the values ​​AGENDADO, REALIZADO, CANCELADO, and ADIADO.",
			tags = {"Appointment"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<AppointmentResponseDTO> update(@RequestBody AppointmentRequestDTO appointmentRequestDTO, @PathVariable Long id) {
		logger.info("Initialize update controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		AppointmentResponseDTO appointmentUpdateResponseDTO = services.update(appointmentRequestDTO, id);
		
		logger.info("Finishing update controller method.");
		return ResponseEntity.ok(appointmentUpdateResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Delete a Appointment.",
			description = "Delete a specific Appointment by their id.",
			tags = {"Appointment"},
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
