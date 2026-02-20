package com.lucadevx.MedicalAppointmentSystem.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.lucadevx.MedicalAppointmentSystem.dto.request.DepartmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DepartmentResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface DepartmentControllerDocs {

	@Operation(
			summary = "Create a Department.",
			description = "Create a Department.",
			tags = {"Department"},
			responses = {
					@ApiResponse(
							responseCode = "201", 
							content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<DepartmentResponseDTO> create(DepartmentRequestDTO departmentRequestDTO);

	@Operation(
			summary = "Finds a Department.",
			description = "Find a specific Department by Id.",
			tags = {"Department"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<DepartmentResponseDTO> findById(Long id);

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
	ResponseEntity<List<DepartmentResponseDTO>> findAll();

	@Operation(
			summary = "Update a Department.",
			description = "Update a information from specific Department.",
			tags = {"Department"},
			responses = {
					@ApiResponse(
							responseCode = "200", 
							content = @Content(schema = @Schema(implementation = DepartmentResponseDTO.class))),
					@ApiResponse(responseCode = "204", content = @Content),
					@ApiResponse(responseCode = "400", content = @Content),
					@ApiResponse(responseCode = "401", content = @Content),
					@ApiResponse(responseCode = "404", content = @Content),
					@ApiResponse(responseCode = "500", content = @Content)
			}
			
	)
	ResponseEntity<DepartmentResponseDTO> update(DepartmentRequestDTO departmentRequestDTO, Long id);

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
	ResponseEntity<Void> delete(Long id);

}