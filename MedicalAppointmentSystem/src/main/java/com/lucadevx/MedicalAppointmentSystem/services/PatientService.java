package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final static Logger logger = LoggerFactory.getLogger(PatientService.class.getName());
	
	public PatientResponseDTO create(PatientRequestDTO patientRequestDTO) {
		logger.info("Starting the service's create method.");
		Patient patient = parseToPatient(patientRequestDTO);
		
		PatientResponseDTO patientResponseDTO = parseToDTO(repository.save(patient));
		
		logger.info("Patient saved.");
		logger.info("Returning patientResponseDTO.");
		return patientResponseDTO;
	}
	
	public PatientResponseDTO findById(Long id) {
		logger.info("Starting the service's findById method.");
		logger.debug("Fetching patient by Id from database.");
		
		PatientResponseDTO patientResponseDTO = parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));
		
		logger.info("Returning patientResponseDTO.");
		return patientResponseDTO;
		
	}
	
	public List<PatientResponseDTO> findAll(){
		logger.info("Starting the service's findAll method.");
		logger.debug("Fetching all patients from database.");
		
		List<Patient> patients= repository.findAll();
		
		logger.debug("Parsing all patients to patientDTO.");
		List<PatientResponseDTO> patientsResponseDTO = patients.stream()
				.map(patient -> parseToDTO(patient))
				.collect(Collectors.toList());
		
		logger.info("Returning patientsDTO.");
		return patientsResponseDTO;
	}
	
	public PatientResponseDTO update(PatientRequestDTO patientRequestDTO, Long id) {
		logger.info("Starting the service's update method.");
		logger.debug("Fetching patient by Id from database.");
		Patient patientRepository = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));

		logger.info("Updating data.");
		patientRepository.setFirstName(patientRequestDTO.firstName());
		patientRepository.setLastName(patientRequestDTO.lastName());
		patientRepository.setBirthDate(patientRequestDTO.birthDate());
		patientRepository.setEmail(patientRequestDTO.email());
		patientRepository.setPhone(patientRequestDTO.phone());
		
		PatientResponseDTO patientResponseDTO = parseToDTO(repository.save(patientRepository));
		
		logger.info("Updated and Saved");
		logger.info("Returning patientResponseDTO.");
		
		return patientResponseDTO;
	}
	
	
	public void delete(Long id) {
		logger.info("Starting the service's delete method.");
		logger.debug("Fetching patient by Id from database.");
		Patient patient = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.debug("Checking if the appointment list is empty.");
		if(!patient.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		
		logger.info("The patient was deleted.");
		repository.deleteById(id);
	}
	
	public static Patient parseToPatient(PatientRequestDTO patientRequestDTO) {
		logger.debug("Parsing patientRequestDTO to patient object.");
		Patient patient = new Patient();
		
		patient.setFirstName(patientRequestDTO.firstName());
		patient.setLastName(patientRequestDTO.lastName());
		patient.setEmail(patientRequestDTO.email());
		patient.setPhone(patientRequestDTO.phone());
		patient.setBirthDate(patientRequestDTO.birthDate());
		
		logger.debug("The patientRequestDTO was converted to patient object.");
		return patient;
	}
	
	
	public static PatientResponseDTO parseToDTO(Patient patient) {
		logger.debug("Parsing patient object to patientResponseDTO.");
		PatientResponseDTO patientResponseDTO = new PatientResponseDTO(
					patient.getId(),
					patient.getFirstName(),
					patient.getLastName(), 
					patient.getEmail(), 
					patient.getPhone(), 
					patient.getBirthDate().format(formatter));
		
		logger.debug("The patient object was converted to patientResponseDTO.");
		return patientResponseDTO;
	}
	
	

}
