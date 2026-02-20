package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.request.AppointmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.exception.AppointmentNotAvailableException;
import com.lucadevx.MedicalAppointmentSystem.exception.ObjectNotFoundException;
import com.lucadevx.MedicalAppointmentSystem.model.Appointment;
import com.lucadevx.MedicalAppointmentSystem.model.Department;
import com.lucadevx.MedicalAppointmentSystem.model.Doctor;
import com.lucadevx.MedicalAppointmentSystem.model.Patient;
import com.lucadevx.MedicalAppointmentSystem.model.enums.Status;
import com.lucadevx.MedicalAppointmentSystem.repository.AppointmentRepository;
import com.lucadevx.MedicalAppointmentSystem.repository.DepartmentRepository;
import com.lucadevx.MedicalAppointmentSystem.repository.DoctorRepository;
import com.lucadevx.MedicalAppointmentSystem.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository repository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private final static Logger logger = LoggerFactory.getLogger(DepartmentService.class.getName());
	
	public AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO) {
		logger.info("Starting the service's create method.");
		
		if(!isAppointmentAvailable(appointmentRequestDTO.appointmentDateTime())) {
			throw new AppointmentNotAvailableException(); 
		}
		
		Appointment appointment = parseToAppointment(appointmentRequestDTO);
		
		logger.debug("Fetching patient by Id from database.");
		Patient patientCurrent = patientRepository.findById(appointmentRequestDTO.patientId())
				.orElseThrow(()-> new ObjectNotFoundException("Object patient not found"));
		
		logger.debug("Fetching doctor by Id from database.");
		Doctor doctorCurrent = doctorRepository.findById(appointmentRequestDTO.doctorId())
				.orElseThrow(()-> new ObjectNotFoundException("Object doctor not found"));
		
		logger.debug("Fetching department by Id from database.");
		Department departmentCurrent = departmentRepository.findById(appointmentRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object department not found"));
		
		logger.info("Setting up data.");
		appointment.setDepartment(departmentCurrent);
		appointment.setDoctor(doctorCurrent);
		appointment.setPatient(patientCurrent);
		
		AppointmentResponseDTO appointmentResponseDTO = parseToDTO(repository.save(appointment));
		
		logger.info("Appointment saved.");
		logger.info("Returning AppointmentResponseDTO.");
		return appointmentResponseDTO;
	}
	
	public AppointmentResponseDTO findById(Long id) {
		logger.info("Starting the service's findById method.");
		
		AppointmentResponseDTO appointmentResponseDTO =  parseToDTO(repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object appointment not found")));
		
		logger.info("Returning appointmentResponseDTO.");
		return appointmentResponseDTO;
	}
	
	public List<AppointmentResponseDTO> findAll(){
		logger.info("Starting the service's findAll method.");
		
		logger.debug("Fetching all appointments from database.");
		List<Appointment> appointments = repository.findAll();
		
		logger.debug("Parsing all appointments to departmentsDTO.");
		List<AppointmentResponseDTO> appointmentsDTO = appointments.stream()
				.map(appointment -> parseToDTO(appointment))
				.collect(Collectors.toList());
		
		logger.info("Returning appointmentsResponseDTO.");
		return appointmentsDTO;
	}
	
	@Transactional
	public AppointmentResponseDTO update(AppointmentRequestDTO appointmentRequestDTO, Long id) {
		logger.info("Starting the service's update method.");
		
		logger.debug("Fetching appointment by Id from database.");
		Appointment appointmentCurrent = repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object appointment not found"));
		
		logger.debug("Fetching patient by Id from database.");
		Patient patientCurrent = patientRepository.findById(appointmentRequestDTO.patientId())
				.orElseThrow(()-> new ObjectNotFoundException("Object patient not found"));
		
		logger.debug("Fetching doctor by Id from database.");
		Doctor doctorCurrent = doctorRepository.findById(appointmentRequestDTO.doctorId())
				.orElseThrow(()-> new ObjectNotFoundException("Object doctor not found"));
		
		logger.debug("Fetching department by Id from database.");
		Department departmentCurrent = departmentRepository.findById(appointmentRequestDTO.departmentId())
				.orElseThrow(()-> new ObjectNotFoundException("Object department not found"));
		
		if(!(isAppointmentAvailable(appointmentRequestDTO.appointmentDateTime()) ||
				appointmentCurrent.getAppointmentDateTime().equals(appointmentCurrent.getAppointmentDateTime())) ) {
			
			throw new AppointmentNotAvailableException(); 
		}
		
		logger.info("Setting up data.");
		appointmentCurrent.setAppointmentDateTime(appointmentRequestDTO.appointmentDateTime());
		appointmentCurrent.setStatus(Status.valueOf(appointmentRequestDTO.status().toUpperCase()));
		appointmentCurrent.setDepartment(departmentCurrent);
		appointmentCurrent.setDoctor(doctorCurrent);
		appointmentCurrent.setPatient(patientCurrent);
		
		AppointmentResponseDTO appointmentResponseDTO = parseToDTO(repository.save(appointmentCurrent));
		
		logger.info("Updated and Saved");
		logger.info("Returning appointmentResponseDTO.");
		return appointmentResponseDTO;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
		logger.info("The appointment was deleted.");
	}
	
	public Appointment parseToAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		logger.debug("Parsing appointmentRequestDTO to appointment object.");
		Appointment appointment = new Appointment();
		
		appointment.setAppointmentDateTime(appointmentRequestDTO.appointmentDateTime());
		appointment.setStatus(Status.valueOf(appointmentRequestDTO.status().toUpperCase()));
		
		logger.debug("The appointmentResquestDTO was converted to appointment object.");
		return appointment;
	}

	@Transactional
	public AppointmentResponseDTO parseToDTO(Appointment appointment) {
		logger.debug("Parsing appointment object to appointmentResponseDTO.");
		
		PatientResponseDTO patientResponseDTO = PatientService.parseToDTO(appointment.getPatient());
		DoctorResponseDTO doctorResponseDTO = DoctorService.parseToDTO(appointment.getDoctor());
		
		AppointmentResponseDTO appointmentResponseDTO =  new AppointmentResponseDTO(
				appointment.getId(),
				appointment.getAppointmentDateTime().format(formatter),
				appointment.getStatus().name(),
				patientResponseDTO,
				doctorResponseDTO);
		
		logger.debug("The appointment object was converted to appointmentResponseDTO.");
		return appointmentResponseDTO;
	}
	
	public boolean isAppointmentAvailable(LocalDateTime appointmentDateTime) {
		logger.info("Checking if the appointment date is valid.");
		
		List<Appointment> appointmentsDB = repository.findAll();
		
		for(Appointment appointment : appointmentsDB) {
			
			if(appointment.getAppointmentDateTime().equals(appointmentDateTime)) {
				return false;
			}
			
		}
		
		return true;
	}
}
