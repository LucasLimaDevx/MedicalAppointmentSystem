package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository repository;
	
	public Patient create(Patient patient) {
		
		return repository.save(patient);
	}
	
	public Patient findById(Long id) {
		
		return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Object not found"));
	}
	
	public List<Patient> findAll(){
		return repository.findAll();
	}

}
