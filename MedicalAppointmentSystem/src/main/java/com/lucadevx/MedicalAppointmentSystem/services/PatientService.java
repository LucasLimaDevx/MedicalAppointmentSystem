package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public PatientResponseDTO create(PatientRequestDTO patientRequestDTO) {
		Patient patient = parseToPatient(patientRequestDTO);
		
		return parseToDTO(repository.save(patient));
	}
	
	public PatientResponseDTO findById(Long id) {
		return parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));
		
	}
	
	public List<PatientResponseDTO> findAll(){
		List<Patient> patients= repository.findAll();
		List<PatientResponseDTO> patientsResponseDTO = patients.stream()
				.map(patient -> parseToDTO(patient))
				.collect(Collectors.toList());
		return patientsResponseDTO;
	}
	
	public PatientResponseDTO update(PatientRequestDTO patientRequestDTO, Long id) {
		
		Patient patientRepository = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		patientRepository.setFirstName(patientRequestDTO.firstName());
		patientRepository.setLastName(patientRequestDTO.lastName());
		patientRepository.setBirthDate(patientRequestDTO.birthDate());
		patientRepository.setEmail(patientRequestDTO.email());
		patientRepository.setPhone(patientRequestDTO.phone());
		
		return parseToDTO(repository.save(patientRepository));
	}
	
	
	public void delete(Long id) {
		Patient patient = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		if(!patient.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		repository.deleteById(id);
	}
	
	public static Patient parseToPatient(PatientRequestDTO patientRequestDTO) {
		Patient patient = new Patient();
		
		patient.setFirstName(patientRequestDTO.firstName());
		patient.setLastName(patientRequestDTO.lastName());
		patient.setEmail(patientRequestDTO.email());
		patient.setPhone(patientRequestDTO.phone());
		patient.setBirthDate(patientRequestDTO.birthDate());
		
		return patient;
	}
	
	
	public static PatientResponseDTO parseToDTO(Patient patient) {
		

			return new PatientResponseDTO(
					patient.getId(),
					patient.getFirstName(),
					patient.getLastName(), 
					patient.getEmail(), 
					patient.getPhone(), 
					patient.getBirthDate().format(formatter));
	}
	
	

}
