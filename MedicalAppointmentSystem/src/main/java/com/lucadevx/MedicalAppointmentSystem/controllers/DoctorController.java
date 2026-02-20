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

import com.lucadevx.MedicalAppointmentSystem.dto.request.DoctorRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.services.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/doctor")
@Tag(name = "Doctor", description = "Endpoints for managing Doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService services;
	private final static Logger logger = LoggerFactory.getLogger(DoctorController.class.getName());
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Create a Doctor.",
			description = "To create a Doctor object, the departmentId field must be greater than 0.",
			tags = {"Doctor"},
			responses = {
					@ApiResponse(
							responseCode = "201", 
							content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<DoctorResponseDTO> create(@RequestBody DoctorRequestDTO doctorRequestDTO) {
		logger.info("Initialize create controller method.");
		
		DoctorResponseDTO doctorResponseDTO = services.create(doctorRequestDTO);
		
		logger.info("Finishing create controller method.");
		return new ResponseEntity<>(doctorResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Finds a Doctor.",
			description = "Find a Doctor specific by Id.",
			tags = {"Doctor"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
		logger.info("Initialize findById controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		DoctorResponseDTO doctorResponseDTO = services.findById(id);
		
		logger.info("Finishing findById controller method.");
		return ResponseEntity.ok(doctorResponseDTO);
		
	}
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Find All Doctor.",
			description = "Finds All Doctor.",
			tags = {"Doctor"},
			responses = {
					@ApiResponse(
							
							responseCode = "200", 
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))
							)
							
					),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<List<DoctorResponseDTO>> findAll(){
		logger.info("Initialize findAll controller method.");
		
		List<DoctorResponseDTO> doctorResponseDTOs = services.findAll();
	
		logger.info("Finishing findAll controller method.");
		return ResponseEntity.ok(doctorResponseDTOs);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Update a Doctor.",
			description = "To update a Doctor object, the id and departmentId fields must be greater than 0.",
			tags = {"Doctor"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	public ResponseEntity<DoctorResponseDTO> update(@RequestBody DoctorRequestDTO doctorRequestDTO, @PathVariable Long id) {
		logger.info("Initialize update controller method.");
		logger.debug("Validating identifier.");
		
		if(id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		DoctorResponseDTO doctorUpdateResponseDTO = services.update(doctorRequestDTO, id);
		
		logger.info("Finishing update controller method.");
		return ResponseEntity.ok(doctorUpdateResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(
			summary = "Delete a Doctor.",
			description = "Delete a specific Doctor by their id.",
			tags = {"Doctor"},
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
