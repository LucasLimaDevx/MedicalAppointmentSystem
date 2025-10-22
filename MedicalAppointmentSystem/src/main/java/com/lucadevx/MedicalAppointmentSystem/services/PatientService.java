package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
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
		return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
	}
	
	public List<Patient> findAll(){
		return repository.findAll();
	}
	
	public Patient update(Patient patient) {
		
		Patient patientRepository = findById(patient.getId());
		
		patientRepository.setFirstName(patient.getFirstName());
		patientRepository.setLastName(patient.getLastName());
		patientRepository.setBirthDate(patient.getBirthDate());
		patientRepository.setEmail(patient.getEmail());
		patientRepository.setPhone(patient.getPhone());
		
		return repository.save(patientRepository);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Patient parseToPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		
		patient.setId(patientDTO.id());
		patient.setFirstName(patientDTO.firstName());
		patient.setLastName(patientDTO.lastName());
		patient.setEmail(patientDTO.email());
		patient.setPhone(patientDTO.phone());
		patient.setBirthDate(patientDTO.birthDate());
		
		return patient;
	}
	
	public PatientDTO parseToDTO(Patient patient) {
		
		return new PatientDTO(
				patient.getId(),
				patient.getFirstName(),
				patient.getLastName(), 
				patient.getPhone(), 
				patient.getEmail(), 
				patient.getBirthDate(),
				patient.getAppointments());
	}
	
	

}
