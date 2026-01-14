package com.lucadevx.MedicalAppointmentSystem.exception;

public class AppointmentNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AppointmentNotAvailableException() {
		super("The date for consultation is not available");
	}

}
