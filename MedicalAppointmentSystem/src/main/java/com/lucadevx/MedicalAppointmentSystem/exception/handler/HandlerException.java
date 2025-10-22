package com.lucadevx.MedicalAppointmentSystem.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lucadevx.MedicalAppointmentSystem.exception.ExceptionResponse;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllException(Exception exception , WebRequest request){
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ExceptionResponse> objectNotFound(ObjectNotFoundException exception , WebRequest request){
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
