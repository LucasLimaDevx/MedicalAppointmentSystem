package com.lucadevx.MedicalAppointmentSystem.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.lucadevx.MedicalAppointmentSystem.dto.request.AppointmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface AppointmentControllerDocs {
	
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
	ResponseEntity<AppointmentResponseDTO> create(AppointmentRequestDTO appointmenRequestDTO);
	
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
	ResponseEntity<AppointmentResponseDTO> findById(Long id);

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
	ResponseEntity<List<AppointmentResponseDTO>> findAll();

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
	ResponseEntity<AppointmentResponseDTO> update(AppointmentRequestDTO appointmentRequestDTO, Long id);

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
	ResponseEntity<Void> delete(Long id);

}