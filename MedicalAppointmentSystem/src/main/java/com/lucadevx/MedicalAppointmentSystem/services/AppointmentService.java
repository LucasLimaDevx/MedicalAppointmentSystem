package com.lucadevx.MedicalAppointmentSystem.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucadevx.MedicalAppointmentSystem.dto.AppointmentDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.request.AppointmentRequestDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.AppointmentResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.DoctorResponseDTO;
import com.lucadevx.MedicalAppointmentSystem.dto.response.PatientResponseDTO;
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

	public AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO) {
		Appointment appointment = parseToAppointment(appointmentRequestDTO);
		
		return parseToDTO(repository.save(appointment));
	}
	
	public AppointmentResponseDTO findById(Long id) {
		
		return parseToDTO(repository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException("Object not found")));
	}
	
	public List<AppointmentResponseDTO> findAll(){
		List<Appointment> appointments = repository.findAll();
		
		List<AppointmentResponseDTO> appointmentsDTO = appointments.stream()
				.map(appointment -> parseToDTO(appointment))
				.collect(Collectors.toList());
		
		return appointmentsDTO;
	}
	
	@Transactional
	public AppointmentResponseDTO update(AppointmentDTO appointmentDTO) {
		Appointment appointmentCurrent = repository.findById(appointmentDTO.id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Patient patientCurrent = patientRepository.findById(appointmentDTO.patient().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Doctor doctorCurrent = doctorRepository.findById(appointmentDTO.doctor().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Department departmentCurrent = departmentRepository.findById(appointmentDTO.department().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
				
		appointmentCurrent.setAppointmentDateTime(appointmentDTO.appointmentDateTime());
		appointmentCurrent.setStatus(Status.valueOf(appointmentDTO.status().toUpperCase()));
		appointmentCurrent.setDepartment(departmentCurrent);
		appointmentCurrent.setDoctor(doctorCurrent);
		appointmentCurrent.setPatient(patientCurrent);
		
		return parseToDTO(repository.save(appointmentCurrent));
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public Appointment parseToAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		
		Appointment appointment = new Appointment();
		Patient patientCurrent = patientRepository.findById(appointmentRequestDTO.patient().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Doctor doctorCurrent = doctorRepository.findById(appointmentRequestDTO.doctor().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		Department departmentCurrent = departmentRepository.findById(appointmentRequestDTO.department().id())
				.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		
		appointment.setAppointmentDateTime(appointmentRequestDTO.appointmentDateTime());
		appointment.setStatus(Status.valueOf(appointmentRequestDTO.status().toUpperCase()));
		appointment.setDepartment(departmentCurrent);
		appointment.setDoctor(doctorCurrent);
		appointment.setPatient(patientCurrent);
	
		return appointment;
	}

	public AppointmentResponseDTO parseToDTO(Appointment appointment) {
	
		PatientResponseDTO patientResponseDTO = PatientService.parseToDTO(appointment.getPatient());
		DoctorResponseDTO doctorResponseDTO = DoctorService.parseToDTO(appointment.getDoctor());
		
		return new AppointmentResponseDTO(
				appointment.getId(),
				appointment.getAppointmentDateTime().format(formatter),
				appointment.getStatus().name(),
				patientResponseDTO,
				doctorResponseDTO);
	}
}
