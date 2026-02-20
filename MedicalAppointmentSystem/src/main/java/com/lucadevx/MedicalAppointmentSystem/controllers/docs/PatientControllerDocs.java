package com.lucadevx.MedicalAppointmentSystem.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.lucadevx.MedicalAppointmentSystem.dto.request.PatientRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PatientControllerDocs {

	@Operation(
			summary = "Create a Patient.",
			description = "Create a Patient.",
			tags = {"Patient"},
			responses = {
					@ApiResponse(
							responseCode = "201", 
							content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<PatientResponseDTO> create(PatientRequestDTO patientRequestDTO);

	@Operation(
			summary = "Finds a Patient.",
			description = "Find a specific Patient by Id.",
			tags = {"Patient"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<PatientResponseDTO> findById(Long id);
	
	@Operation(
			summary = "Find All Patient.",
			description = "Finds All Patient.",
			tags = {"Patient"},
			responses = {
					@ApiResponse(
							
							responseCode = "200", 
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									array = @ArraySchema(schema = @Schema(implementation = PatientResponseDTO.class))
							)
							
					),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<List<PatientResponseDTO>> findAll();

	@Operation(
			summary = "Update a Patient.",
			description = "Update a information from specific Patient.",
			tags = {"Patient"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<PatientResponseDTO> update(PatientRequestDTO patientRequestDTO, Long id);
	
	@Operation(
			summary = "Delete a Patient.",
			description = "Delete a specific Patient by their id.",
			tags = {"Patient"},
			responses = {
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<Void> delete(Long id);

}