package com.lucadevx.MedicalAppointmentSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.request.DoctorRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.DatabaseException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;
import com.lucadevx.MedicalAppointmentSystem.repository.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final static Logger logger = LoggerFactory.getLogger(PatientService.class.getName());
	
	public DoctorResponseDTO create(DoctorRequestDTO doctorRequestDTO) {
		logger.info("Starting the service's create method.");
		Doctor doctor = parseToDoctor(doctorRequestDTO);
		
		logger.debug("Fetching department by Id from database.");
		Department department = departmentRepository.findById(doctorRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.debug("Setting up department on doctor object.");
		doctor.setDepartment(department);
		
		DoctorResponseDTO doctorResponseDTO = parseToDTO(repository.save(doctor));
		
		logger.info("Doctor saved.");
		logger.info("Returning DoctorResponseDTO.");
		
		return doctorResponseDTO;
	}
	
	public DoctorResponseDTO findById(Long id) {
		logger.info("Starting the service's findById method.");
		logger.debug("Fetching doctor by Id from database.");
		
		DoctorResponseDTO doctorResponseDTO = parseToDTO(repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found")));
		
		logger.info("Returning DoctorResponseDTO.");
		return doctorResponseDTO;
	}
	
	public List<DoctorResponseDTO> findAll(){
		logger.info("Starting the service's findAll method.");
		
		logger.debug("Fetching all doctors from database.");
		List<Doctor> doctors = repository.findAll();
		
		logger.debug("Parsing all doctors to doctorsResponseDTO.");
		List<DoctorResponseDTO> doctorsResponseDTO = doctors.stream()
				.map(doctor -> parseToDTO(doctor))
				.collect(Collectors.toList());
		
		logger.info("Returning doctorsResponseDTO.");
		return doctorsResponseDTO;
		
	}
	
	@Transactional
	public DoctorResponseDTO update(DoctorRequestDTO doctorRequestDTO, Long id) {
		logger.info("Starting the service's update method.");
		
		logger.debug("Fetching doctor by Id from database.");
		Doctor doctorRepository = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.debug("Fetching department by Id from database.");
		Department department = departmentRepository.findById(doctorRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.info("Updating doctor object.");
		doctorRepository.setFirstName(doctorRequestDTO.firstName());
		doctorRepository.setLastName(doctorRequestDTO.lastName());
		doctorRepository.setEmail(doctorRequestDTO.email());
		doctorRepository.setPhone(doctorRequestDTO.phone());
		doctorRepository.setCrm(doctorRequestDTO.crm());
		doctorRepository.setSpeciality(doctorRequestDTO.speciality());
		doctorRepository.setDepartment(department);
		
		DoctorResponseDTO doctorResponseDTO = parseToDTO(repository.save(doctorRepository));
		
		logger.info("Updated and Saved");
		logger.info("Returning doctorResponseDTO.");
		return doctorResponseDTO;
	}
	
	
	public void delete(Long id) {
		logger.info("Starting the service's delete method.");
		logger.debug("Fetching doctor by Id from database.");
		Doctor doctor = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		logger.debug("Checking if the appointment list is empty.");
		if(!doctor.getAppointments().isEmpty()) {
			throw new DatabaseException();
		}
		
		repository.deleteById(id);
		logger.info("The doctor was deleted.");
	}
	
	public static Doctor parseToDoctor(DoctorRequestDTO doctorRequestDTO) {
		logger.debug("Parsing doctorRequestDTO to doctor object.");
		Doctor doctor = new Doctor();
		
		doctor.setFirstName(doctorRequestDTO.firstName());
		doctor.setLastName(doctorRequestDTO.lastName());
		doctor.setPhone(doctorRequestDTO.phone());
		doctor.setEmail(doctorRequestDTO.email());
		doctor.setCrm(doctorRequestDTO.crm());
		doctor.setSpeciality(doctorRequestDTO.speciality());
		
		logger.debug("The doctorRequestDTO was converted to doctor object.");
		
		return doctor;
	}
	
	public static DoctorResponseDTO parseToDTO(Doctor doctor) {
		logger.debug("Parsing doctor object to DoctorResponseDTO.");
		
		DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO(
				doctor.getId(),
				doctor.getFirstName(),
				doctor.getLastName(),
				doctor.getPhone(),
				doctor.getEmail(),
				doctor.getCrm(),
				doctor.getSpeciality());
		
		logger.debug("The doctor object was converted to doctorResponseDTO.");
		return doctorResponseDTO;
	}
}
