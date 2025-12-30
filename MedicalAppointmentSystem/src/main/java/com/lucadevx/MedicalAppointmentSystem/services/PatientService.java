package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.PatientDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.request.PatientRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.DatabaseException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository repository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public Patient create(Patient patient) {
		
		return repository.save(patient);
	}
	
	public Patient findById(Long id) {
		return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
	}
	
	public List<PatientResponseDTO> findAll(){
		List<Patient> patients= repository.findAll();
		List<PatientResponseDTO> patientsResponseDTO = patients.stream()
				.map(patient -> parseToDTO(patient))
				.collect(Collectors.toList());
		return patientsResponseDTO;
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
		Patient patient = findById(id);
		
		if(!patient.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		repository.deleteById(id);
	}
	
	public Patient parseToPatient(PatientRequestDTO patientRequestDTO) {
		Patient patient = new Patient();
		
		patient.setId(patientRequestDTO.id());
		patient.setFirstName(patientRequestDTO.firstName());
		patient.setLastName(patientRequestDTO.lastName());
		patient.setEmail(patientRequestDTO.email());
		patient.setPhone(patientRequestDTO.phone());
		patient.setBirthDate(patientRequestDTO.birthDate());
		
		return patient;
	}
	
	public PatientResponseDTO parseToDTO(Patient patient) {
		
		return new PatientResponseDTO(
				patient.getId(),
				patient.getFirstName(),
				patient.getLastName(), 
				patient.getPhone(), 
				patient.getEmail(), 
				patient.getBirthDate().format(formatter));
	}
	
	

}
