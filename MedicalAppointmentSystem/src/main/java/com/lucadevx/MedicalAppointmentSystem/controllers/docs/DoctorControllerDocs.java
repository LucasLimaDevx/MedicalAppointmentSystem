package com.lucadevx.MedicalAppointmentSystem.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.lucadevx.MedicalAppointmentSystem.dto.request.DoctorRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface DoctorControllerDocs {

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
	ResponseEntity<DoctorResponseDTO> create(DoctorRequestDTO doctorRequestDTO);

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
	ResponseEntity<DoctorResponseDTO> findById(Long id);

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
	ResponseEntity<List<DoctorResponseDTO>> findAll();

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
	ResponseEntity<DoctorResponseDTO> update(DoctorRequestDTO doctorRequestDTO, Long id);

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
	ResponseEntity<Void> delete(Long id);

}