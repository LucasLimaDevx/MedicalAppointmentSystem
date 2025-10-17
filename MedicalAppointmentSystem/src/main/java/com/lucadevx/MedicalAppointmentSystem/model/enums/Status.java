package com.lucadevx.MedicalAppointmentSystem.model.enums;

public enum Status {
	
	AGENDADO(0),
	REALIZADO(1),
	CANCELADO(2),
	ADIADO(3);

	private final int code;
	
	private Status(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
    public static Status fromCode(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
	
}
