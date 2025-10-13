package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository repository;
	
	public Appointment create(Appointment appointment) {

		
		return repository.save(appointment);
	}
	
	public Appointment findById(Long id) {
		
		
		
		return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Object not found"));
	}
	
	public List<Appointment> findAll(){
		return repository.findAll();
	}
	
	public Appointment update(Appointment appointment) {
		
		Appointment appointmentCurrent = findById(appointment.getId());
		
		appointmentCurrent.setAppointmentDateTime(appointment.getAppointmentDateTime());
		appointmentCurrent.setStatus(appointment.getStatus());
		appointmentCurrent.setDepartment(appointment.getDepartment());
		appointmentCurrent.setDoctor(appointment.getDoctor());
		
		return repository.save(appointmentCurrent);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
